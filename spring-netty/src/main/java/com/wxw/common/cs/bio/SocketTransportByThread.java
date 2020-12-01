package com.wxw.common.cs.bio;

import domain.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ Author ：wxw.
 * @ Date ： 11:00 2020/10/10
 * @ Description：Socket网络传输
 * @ Version:   v_0.0.1
 */
public class SocketTransportByThread {
    public static void main(String[] args) throws IOException {

        Thread server = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //1.服务端
                    ServerSocket server = new ServerSocket(8080);
                    Socket socket;
                    while ((socket = server.accept()) != null) {
                        try (final ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                             final ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
                            // 3，通过输入流读取客户端发送的请求信息
                            Message message = (Message) objectInputStream.readObject();
                            System.out.println("服务端：server receive message = " + message.getContent());
                            message.setContent("服务端真正响应的数据");
                            //4.通过输出流向客户端发送响应信息
                            objectOutputStream.writeObject(message);
                            objectOutputStream.flush();
                        } catch (IOException | ClassNotFoundException e) {
                            System.out.println("occur exception:" + e);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread client = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //1.客户端
                    Socket socket = new Socket("127.0.0.1", 8080);
                    Message message = new Message("客户端向服务端发的数据");
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    //2.通过输出流向服务器端发送请求信息
                    objectOutputStream.writeObject(message);
                    //3.通过输入流获取服务器响应的信息
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    System.out.println("客户端：server send message = " + objectInputStream.readObject());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        server.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        client.start();
    }
}