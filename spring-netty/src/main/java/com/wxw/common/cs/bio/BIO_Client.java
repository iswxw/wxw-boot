package com.wxw.common.cs.bio;

import domain.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author ：wxw.
 * @date ： 15:15 2020/11/30
 * @description：客户端测试代码
 * @version: v_0.0.1
 */
public class BIO_Client {
    public static void main(String[] args) {
        BIO_Client client = new BIO_Client();
        Message message = new Message("客户端开始发送内容：123");
        client.send(message, "127.0.0.1", 6666);
        System.out.println("client send message:" + message.getContent());
    }

    public Object send(Message message, String host, int port) {
        //1. 创建Socket对象并且指定服务器的地址和端口号
        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            //2.通过输出流向服务器端发送请求信息
            objectOutputStream.writeObject(message);
            //3.通过输入流获取服务器响应的信息
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("occur exception:" + e);
        }
        return null;
    }
}
