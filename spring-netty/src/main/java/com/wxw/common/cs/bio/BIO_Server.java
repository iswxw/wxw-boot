package com.wxw.common.cs.bio;

import domain.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ：wxw.
 * @date ： 15:10 2020/11/30
 * @description：BIO测试  服务端测试代码
 * @version: v_0.0.1
 */
public class BIO_Server {

    public static void main(String[] args) {

        BIO_Server bioServer = new BIO_Server();
        bioServer.start(6666);
    }

    public void start(int port) {
        // 1. 创建ServerSocket对象并绑定一个端口
        try (ServerSocket server = new ServerSocket(port);) {
            Socket socket;
            // 使用accept()方法监听客户端的请求，这个方法会一直阻塞到有一个连接建立
            while ((socket = server.accept()) != null) {
                try (final ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                     final ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
                    // 3，通过输入流读取客户端发送的请求信息
                    Message message = (Message) objectInputStream.readObject();
                    System.out.println("server receive message = " + message.getContent());
                    message.setContent("new content");
                    //4.通过输出流向客户端发送响应信息
                    objectOutputStream.writeObject(message);
                    objectOutputStream.flush();
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("occur exception:" + e);
                }
            }
        } catch (IOException e) {
            System.out.println("occur exception:" + e);
        }
    }
}
