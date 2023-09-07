package com.wxw.function.io.bio.single_thread_read;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @contract: 公众号：技术能量站
 * @desc:
 * @link:
 */
public class RedisClient02 {
    public static void main(String[] args) throws IOException {
        System.out.println("------redis client02 start");
        Socket socket = new Socket("127.0.0.1", 6379);
        System.out.println("------redis client02 connect success");

        // 读取数据
        OutputStream outputStream = socket.getOutputStream();
        while (true){
            Scanner scanner = new Scanner(System.in);
            String text = scanner.next();
            if (text.equalsIgnoreCase("quit")){
                break;
            }
            socket.getOutputStream().write(text.getBytes(StandardCharsets.UTF_8));
            System.out.println("redis client02 input keyword to finish....");
        }
        outputStream.close();
        socket.close();
    }
}
