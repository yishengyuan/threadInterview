package com.daniel.多线程;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


/**
 * JDK8  多线程
 */
public class JDK8CompletableFutureTest {

    @Test
    public void testCompletableFuture() throws ExecutionException, InterruptedException {
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//
//            return 100;
//
//        });
//
//        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
//
//            return "abc";
//
//        });
//
//        CompletableFuture<String> f =  future.thenCombine(future2, (x,y) -> y + "-" + x);
//
//        System.out.println(f.get()); //abc-100


        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {

            return 100;

        });

        CompletableFuture<String> f =  future.thenCompose( i -> {

            return CompletableFuture.supplyAsync(() -> {

                return (i * 10) + "";

            });

        });

        System.out.println(f.get()); //1000

    }
}
