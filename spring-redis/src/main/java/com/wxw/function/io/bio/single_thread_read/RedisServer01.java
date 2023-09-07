package com.wxw.function.io.bio.single_thread_read;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

/**
 * @contract: 公众号：技术能量站
 * @desc:
 * @link:
 */
public class RedisServer01 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6379);
        while (true) {
            System.out.println("redis启动----等待连接");
            Socket socket = serverSocket.accept();  // 阻塞 等待客户端连接
            System.out.println("redis连接----连接成功:" + UUID.randomUUID());

            // 读取客户端数据
            InputStream inputStream = socket.getInputStream();
            int length = -1;
            byte[] bytes = new byte[1024];
            System.out.println("redis读取----等待读取数据");
            while ((length = inputStream.read(bytes)) != -1) // 阻塞 等待客户端发送数据
            {
                System.out.println("成功读取：" + new String(bytes, 0, length));
                System.out.println("redis读取----完成：" + UUID.randomUUID());
            }
            inputStream.close();
            socket.close();
        }
    }
}

