package com.wxw.common.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * 多人聊天室服务端
 */
public class NIO_Server {

    public static void main(String[] args) throws IOException {
        NIO_Server nioServer = new NIO_Server();
        nioServer.start();
    }

    /**
     * 创建服务端的链接
     */
    public  void start() throws IOException {
        //1.创建selector
        Selector selector = Selector.open();
        //2.通过serverSocketChannel创建channel通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //3.为channel通道绑定监听地址
        serverSocketChannel.bind(new InetSocketAddress(8080));
        //4.设置channel为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //5.将channel注册到selector上，监听链接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //6.循环等待新链接的接入
        while (true){
            //获取selector的数量
            int readyChannels = selector.select();

            //如果这个数量等于0表示没有准备好的，则继续循环，不执行下面的代码
            if (readyChannels == 0) continue;

            //如果有可用的数量，selector会将所有的channel放入到slectionKey的set集合中，从中获取
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            //然后通过迭代器的方式将每个链接展示出来
            Iterator iterator = selectionKeys.iterator();
            //如果存在，则继续
            while (iterator.hasNext()){
                //获取selectionKey实例
                SelectionKey selectionKey = (SelectionKey) iterator.next();

                //移除selectionKey
                iterator.remove();

                //7.根据不同的就绪状态，去处理不同的业务逻辑

                //如果是接入事件
                if(selectionKey.isAcceptable()){
                    acceptHandler(serverSocketChannel,selector);
                }
                //如果是可读事件
                if(selectionKey.isReadable()){
                    readHandler(selectionKey,selector);
                }
            }
        }
    }

    /**
     * 接入事件
     */
    private  void acceptHandler(ServerSocketChannel serverSocketChannel,Selector selector) throws IOException {
        //如果是接入事件，创建socketChannel
        SocketChannel socketChannel = serverSocketChannel.accept();
        //将socketChannel设置为非阻塞模式
        socketChannel.configureBlocking(false);
        //将channel注册到selector上，监听可读事件
        socketChannel.register(selector,SelectionKey.OP_READ);

        //回复客户端信息
        socketChannel.write(Charset.forName("UTF-8").encode("您与聊天室其他人都不是朋友关系，请注意隐私安全"));
    }

    /**
     * 可读事件
     */
    private void readHandler(SelectionKey selectionKey,Selector selector) throws IOException {
        //要从selectionKey中获取到已经就绪的channel
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        //创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //循环读取客户端信息
        String request = "";
        while (socketChannel.read(byteBuffer) > 0){
            //将bufer转成读模式
            byteBuffer.flip();
            //读取buffer中的内容
            request += Charset.forName("UTF-8").decode(byteBuffer);
        }
        /**
         * 将channel再次注册到selector 上，监听他的可读事件
         */
        socketChannel.register(selector,SelectionKey.OP_READ);
        //将消息广播给其他变量
        if(request.length() > 0){
            System.out.println(request);
        }
    }
}