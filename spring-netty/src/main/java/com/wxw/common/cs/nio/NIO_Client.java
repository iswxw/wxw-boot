package com.wxw.common.cs.nio;


import domain.Message;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * NIO 客户端
 */
public class NIO_Client {
    public static void main(String[] args) throws IOException {
        NIO_Client client = new NIO_Client();
        Message message = new Message("客户端开始发送内容：123");
        client.send(message, "127.0.0.1", 8080);
        System.out.println("client send message:" + message.getContent());
    }

    public void send(Message message,String host,Integer port) throws IOException {
        /**
         * 链接服务器端
         */
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(host,port));

        //向服务器端发送数据
        //从命令行获取数据，获取键盘的输入
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            //获取这一行数据
            String request =  scanner.nextLine();
            request = request + ":" + message;
            //如果有数据，则发送，且数据不为空
            if(request != null && request.length() > 0){
                socketChannel.write(Charset.forName("UTF-8").encode(request));
            }
        }
    }
}
