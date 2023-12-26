package com.shuosen.多线程;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Project <demo-project>
 * Created by jorgezhong on 2018/9/8 11:45.
 */
public class CompletableFutureDemo {

    /**
     * 创建CompletableFuture
     * - runAsync
     * - supplyAsync
     * - completedFuture
     * <p>
     * 异步计算启用的线程池是守护线程
     */
    @Test
    public void test1() {
        //1、异步计算：无返回值

        //默认线程池为：ForkJoinPool.commonPool()
        CompletableFuture.runAsync(() -> {
            // TODO: 2018/9/8 无返回异步计算
            System.out.println(Thread.currentThread().isDaemon());
        });

        //指定线程池，（到了jdk9CompletableFuture还拓展了延迟的线程池）
        CompletableFuture.runAsync(() -> {
            // TODO: 2018/9/8 无返回异步计算
        }, Executors.newFixedThreadPool(2));


        //2、异步计算：有返回值

        // 使用默认线程池
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "result1");
        //getNow指定异步计算抛出异常或结果返回null时替代的的值
        String result1 = future1.getNow(null);

        //  指定线程池
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "result2", Executors.newFixedThreadPool(2));
        //getNow指定异步计算抛出异常或结果返回null时替代的的值
        String result2 = future2.getNow(null);


        //3、初始化一个有结果无计算的CompletableFuture
        CompletableFuture<String> future = CompletableFuture.completedFuture("result");
        String now = future.getNow(null);
        System.out.println("now = " + now);
    }


    /**
     * 计算完成时需要对异常进行处理或者对结果进行处理
     * - whenComplete：同步处理包括异常
     * - thenApply：同步处理正常结果（前提是没有异常）
     * <p>
     * - whenCompleteAsync：异步处理包括异常
     * - thenApplyAsync：异步处理正常结果(前提是没有异常)
     * <p>
     * - exceptionally : 处理异常
     */
    @Test
    public void test2() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "result");

        //whenComplete方法收future的结果和异常，可灵活进行处理
        //1、同步处理

        //  无返回值：可处理异常
        future.whenComplete((result, throwable) -> System.out.println("result = " + result));

        //  有返回值：没有异常处理（前提）
        CompletableFuture<String> resultFuture1 = future.thenApply(result -> "result");
        String result1 = resultFuture1.getNow(null);


        //2、异步处理：

        //  无返回值： 默认线程池
        future.whenCompleteAsync((result, throwable) -> System.out.println("result = " + result));
        //  无返回值：指定线程池
        future.whenCompleteAsync((result, throwable) -> System.out.println("result = " + result), Executors.newFixedThreadPool(2));

        //  有返回值：默认线程池
        CompletableFuture<String> resultFuture2 = future.thenApplyAsync(result -> "result");
        String result2 = resultFuture2.getNow(null);

        //  有返回值：指定线程池
        CompletableFuture<String> resultFuture3 = future.thenApplyAsync(result -> "result", Executors.newFixedThreadPool(2));
        String result3 = resultFuture3.getNow(null);


        //3、处理异常,处理完之后返回一个结果
        CompletableFuture<String> exceptionallyFuture = future.whenCompleteAsync((result, throwable) -> System.out.println("result = " + 1 / 0))
                .exceptionally(throwable -> "发生异常了：" + throwable.getMessage());
        System.out.println(exceptionallyFuture.getNow(null));
    }


    /**
     * 异常处理还可以使用以下两个方法
     * - handle
     * - handleAsync
     * <p>
     * 备注：exceptionally同步和异步计算一起用如果出现异常会把异常抛出。用以上的方法可以拦截处理
     */
    @Test
    public void test3() {
        CompletableFuture<String> exceptionoHandle = CompletableFuture.completedFuture("produce msg")
                .thenApplyAsync(s -> "result" + 1 / 0);

        String handleResult1 = exceptionoHandle.handle((s, throwable) -> {
            if (throwable != null) {
                return throwable.getMessage();
            }
            return s;
        }).getNow(null);

        //指定线程池
        String handleResult2 = exceptionoHandle.handleAsync((s, throwable) -> {
            if (throwable != null) {
                return throwable.getMessage();
            }
            return s;
        }, Executors.newFixedThreadPool(2)).getNow(null);
    }

    /**
     * 生产--消费
     * - thenAccept：同步的
     * - thenAcceptAsync：异步的
     * <p>
     * 接受上一个处理结果，并实现一个Consumer,消费结果
     */
    @Test
    public void test4() {
        //同步的
        CompletableFuture.completedFuture("produce msg")
                .thenAccept(s -> System.out.println("sync consumed msg : " + s));

        //异步的
        //默认线程池
        CompletableFuture.completedFuture("produce msg")
                .thenAcceptAsync(s -> System.out.println("async consumed msg : " + s));
        //指定线程池
        CompletableFuture.completedFuture("produce msg")
                .thenAcceptAsync(s -> System.out.println("async consumed msg : " + s), Executors.newFixedThreadPool(2));
    }


    /**
     * 取消任务
     * - cancel
     */
    @Test
    public void test5() throws InterruptedException {
        CompletableFuture<String> message = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {

            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s + "result";

        });
        String now = message.getNow(null);
        System.out.println("now = " + now);

        //取消
        boolean cancel = message.cancel(true);
        System.out.println("cancel = " + cancel);

        //如果这里再去获取，会抛出异常，说明已经取消了
        //String now1 = message.getNow(null);
        Thread.sleep(1000);
    }


    /**
     * 两个异步计算
     * - applyToEither：有返回值,同步
     * - acceptEither：无返回值，同步
     * - applyToEitherAsync:有返回值，异步
     * -
     */
    @Test
    public void test6() {
        CompletableFuture<String> task1 = CompletableFuture.completedFuture("task1")
                .thenApply(s -> "task1的计算结果：s1 = " + s);

        //同步，有返回值
        //applyToEither第二个参数接收的值是task1计算的返回值
        CompletableFuture<String> result1 = task1.applyToEither(CompletableFuture.completedFuture("task2")
                .thenApply(s -> "task2的计算结果：s2 = " + s), s -> s);
        System.out.println("task2:" + result1.getNow(null));


        //同步，无返回值
        task1.acceptEither(CompletableFuture.completedFuture("task3")
                .thenApply(s -> "task3的计算结果：s3 = " + s), s -> System.out.println("task3：" + s));


        //异步有返回值，默认线程池，也可以指定
        CompletableFuture<String> result2 = task1.applyToEitherAsync(CompletableFuture.completedFuture("task4")
                .thenApply(s -> "task4的计算结果：s4 = " + s), s -> s);
        //由于是异步的，主线程跑的快一点，因此join()之后才能看到跑完的结果
        System.out.println("task4:" + result2.join());


        //异步无返回值,指定线程池,也可以使用默认线程池
        CompletableFuture<Void> task5 = task1.acceptEitherAsync(CompletableFuture.completedFuture("task5")
                .thenApply(s -> "task5的计算结果：s5 = " + s), s -> System.out.println("task5：" + s), Executors.newFixedThreadPool(2));
        task5.join();
    }

    /**
     * 组合计算结果
     * - runAfterBoth：都计算完之后执行一段代码
     * - thenAcceptBoth：都计算完之后把结果传入，并执行一段代码
     * <p>
     * - thenCombine：组合两个结果
     * - thenCompose：组合两个结果
     */
    @Test
    public void test7() {

        //runAfterBoth方式
        StringBuilder msg = new StringBuilder("jorgeZhong");
        CompletableFuture.completedFuture(msg)
                .thenApply(s -> s.append(" task1,"))
                .runAfterBoth(CompletableFuture.completedFuture(msg)
                        .thenApply(s -> s.append(" task2")), () -> System.out.println(msg));


        //thenAcceptBoth方式
        CompletableFuture.completedFuture("jorgeZhong")
                .thenApplyAsync(String::toLowerCase)
                .thenAcceptBoth(CompletableFuture.completedFuture("jorgeZhong")
                        .thenApplyAsync(String::toUpperCase), (s, s2) -> System.out
                        .println("s1:" + s + ", s2:" + s2));


        //thenCombine方式
        CompletableFuture<String> result1 = CompletableFuture.completedFuture("jorgeZhong")
                .thenApply(String::toLowerCase)
                .thenCombine(CompletableFuture.completedFuture("jorgeZhong")
                        .thenApply(String::toUpperCase), (s, s2) -> "s1:" + s + ", s2:" + s2);

        System.out.println("result1:" + result1.getNow(null));

        //异步
        CompletableFuture<String> result11 = CompletableFuture.completedFuture("jorgeZhong")
                .thenApply(String::toLowerCase)
                .thenCombineAsync(CompletableFuture.completedFuture("jorgeZhong")
                        .thenApplyAsync(String::toUpperCase), (s, s2) -> "s1:" + s + ", s2:" + s2);
        System.out.println("result11:" + result11.join());


        //thenCompose方式
        CompletableFuture<String> result2 = CompletableFuture.completedFuture("jorgeZhong")
                .thenApply(String::toLowerCase)
                .thenCompose(s -> CompletableFuture.completedFuture("jorgeZhong")
                        .thenApply(String::toUpperCase)
                        .thenApply(s1 -> "s:" + s + ", s1:" + s1));
        System.out.println("result2:" + result2.getNow(null));

        //异步
        CompletableFuture<String> result22 = CompletableFuture.completedFuture("jorgeZhong")
                .thenApply(String::toLowerCase)
                .thenComposeAsync(s -> CompletableFuture.completedFuture("jorgeZhong")
                        .thenApplyAsync(String::toUpperCase)
                        .thenApplyAsync(s1 -> "s:" + s + ", s1:" + s1));

        System.out.println("result22:" + result22.join());
    }


    /**
     * 多个CompletableFuture策略
     * - anyOf：接受一个CompletableFuture数组，任意一个任务执行完返回。都会触发该CompletableFuture
     * - whenComplete：计算执行完之后执行实现的一段代码，将上一个结果和异常作为参数传入
     */
    @Test
    public void test8() throws InterruptedException {

        List<String> messages = Arrays.asList("a", "b", "c");
        CompletableFuture.anyOf(messages.stream()
                .map(o -> CompletableFuture.completedFuture(o).thenApplyAsync(s -> {

                    try {
                        Thread.sleep(new Random().ints(99, 300).findFirst().getAsInt());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return s.toUpperCase();
                }))
                .toArray(CompletableFuture[]::new))
                .whenComplete((res, throwable) -> {
                    if (throwable == null) {
                        System.out.println(res.toString());
                    }
                });
        Thread.sleep(1000);
    }


    /**
     * 多个CompletableFuture策略
     * - allOf：接受一个CompletableFuture数组，所有任务返回后，创建一个CompletableFuture
     */
    @Test
    public void test9() {

        List<String> messages = Arrays.asList("a", "b", "c");
        CompletableFuture[] cfs = messages.stream()
                .map(s -> CompletableFuture.completedFuture(s).thenApplyAsync(String::toUpperCase))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(cfs)
                .whenCompleteAsync((aVoid, throwable) -> Arrays.stream(cfs).forEach(completableFuture -> System.out
                        .println(completableFuture.getNow(null))));
    }
}
