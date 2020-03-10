package com.nio;

import org.junit.Test;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * 一：通道(Channel)： 用于源节点与目标节点的连接 。在JAVA NIO中负责缓冲区中数据的传输。Channel本身不存储数据，因此需要配合缓冲区进行传输
 * 二：通道的主要实现类：
 * java.nio.Channnels  接口
 * |--FileChannel   文件通道
 * \--SocketChannel
 * \--ServerSocketChannel
 * |--DatagramChannel
 * 三：获取通道
 * 1：Java针对支持通道的类提供了getChannel方法
 * 本地IO
 * FileInputStream/FileOutputStream
 * RandomAccessFile
 * <p>
 * <p>
 * 网络IO
 * Socket
 * ServerSocket
 * DatagramSocket
 * 2:在JDK1.7中的NIO。2      针对各个通道提供了静态方法open()
 * 在JDK1.7中的NIO2 的Files工具类的newByteChannel()
 *
 * 四：通道之间的数据传输
 * transferForm()
 * transferTo()
 * 五：分散与聚集
 * 分散读取（Scattering Reads）:将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中
 *
 *
 * 六：字符集 Charset
 * 编码：字符串 -> 字节数组
 * 解码：  字节数组 ->字符串
 */
public class TestChannel {

    @Test
    public void test6() throws CharacterCodingException {
        Charset cs1 = Charset.forName("GBK");

        //获取编码器
        CharsetEncoder ce = cs1.newEncoder();

        //获取解码器
        CharsetDecoder cd = cs1.newDecoder();

        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("尚硅谷威武！");
        cBuf.flip();

        //编码
        ByteBuffer bBuf = ce.encode(cBuf);

        for (int i = 0; i < 12; i++) {
            System.out.println(bBuf.get());
        }

        //解码
        bBuf.flip();
        CharBuffer cBuf2 = cd.decode(bBuf);
        System.out.println(cBuf2.toString());

        System.out.println("------------------------------------------------------");

        Charset cs2 = Charset.forName("GBK");
        bBuf.flip();
        CharBuffer cBuf3 = cs2.decode(bBuf);
        System.out.println(cBuf3.toString());
    }


    @Test
    public void test5(){
        SortedMap<String, Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> entries = map.entrySet();
        for (Map.Entry<String, Charset> entry : entries) {
            System.out.println(entry.getKey()+"="+entry.getValue());
        }
    }

    @Test
    public void test4() throws IOException {
        RandomAccessFile  file = new RandomAccessFile("1.txt","rw");
        //获取通道
         FileChannel channel1 = file.getChannel();

         //分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(100);

        //3  分散读取

        ByteBuffer[] bufs = {buf1,buf2};
        channel1.read(bufs);

        for (ByteBuffer buf : bufs) {
            buf.flip();
        }

        System.out.println(new String(bufs[0].array(),0,bufs[0].limit()));
        System.out.println("===============================================");
        System.out.println(new String(bufs[1].array(),0,bufs[1].limit()));

        //聚集写入
        RandomAccessFile  file2 = new RandomAccessFile("2.txt","rw");
        FileChannel channel2 = file2.getChannel();
        channel2.write(bufs);

    }


    @Test
    public void test3() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);

    }



    //2：使用直接缓冲区完成文件的复制  （内存映射文件 ）
    //create：存在创建  不存在也创建    //create_new 存在就报错  //2127-1902-1777
    @Test
    public void test2() throws IOException {
        long start = System.currentTimeMillis();
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
        //内存映射文件
        MappedByteBuffer inMappedBuf  = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());

        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
        //直接对缓冲区进行数据的读写操作
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);
        inChannel.close();
        outChannel.close();
        long end = System.currentTimeMillis();
        System.out.println("耗费时间为"+(end-start));
    }


    //1:利用通道完成文件的复制 非直接缓冲区//10874
    @Test
    public void test1() {
        long start = System.currentTimeMillis();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("1.jpg");
            fos = new FileOutputStream("2.jpg");
            //获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            //完成数据的传输  分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            //将通道中的数据存入缓冲区
            while (inChannel.read(buf) != -1) {
                //将缓冲区中的数据写入通道
                buf.flip(); //切换到读取数据的模式
                outChannel.write(buf);
                buf.clear();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间为"+(end-start));
    }
}
