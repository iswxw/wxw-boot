package com.wxw.common.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

/**
 * @author ：wxw.
 * @date ： 17:15 2020/11/30
 * @description：Netty 客户端
 * @version: v_0.0.1
 */
public class Netty_Server {

    public static void main(String[] args) throws InterruptedException {

        // 创建 BossGroup 和 WorkerGroup
        // 1. bossGroup 只处理连接请求
        // 2. 业务处理由 workerGroup 来完成
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 创建服务器端的启动对象
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 配置参数
            bootstrap
                    // 设置线程组
                    .group(bossGroup, workerGroup)
                    // 说明服务器端通道的实现类（便于 Netty 做反射处理）
                    .channel(NioServerSocketChannel.class)
                    // 设置等待连接的队列的容量（当客户端连接请求速率大
                    // 于 NioServerSocketChannel 接收速率的时候，会使用
                    // 该队列做缓冲）
                    // option()方法用于给服务端的 ServerSocketChannel
                    // 添加配置
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 设置连接保活
                    // childOption()方法用于给服务端 ServerSocketChannel
                    // 接收到的 SocketChannel 添加配置
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // handler()方法用于给 BossGroup 设置业务处理器
                    // childHandler()方法用于给 WorkerGroup 设置业务处理器
                    .childHandler(
                            // 创建一个通道初始化对象
                            new ChannelInitializer<SocketChannel>() {
                                // 向 Pipeline 添加业务处理器
                                @Override
                                protected void initChannel(
                                        SocketChannel socketChannel
                                ) throws Exception {
                                    socketChannel.pipeline().addLast(new NettyServerHandler());
                                    // 可以继续调用 socketChannel.pipeline().addLast()
                                    // 添加更多 Handler
                                }
                            }
                    );

            System.out.println("server is ready...");

            // 绑定端口，启动服务器，生成一个 channelFuture 对象，
            // ChannelFuture 涉及到 Netty 的异步模型，后面展开讲
            ChannelFuture channelFuture = bootstrap.bind(8080).sync();
            // 对通道关闭进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * 自定义一个 Handler，需要继承 Netty 规定好的某个 HandlerAdapter（规范）
     * InboundHandler 用于处理数据流入本端（服务端）的 IO 事件
     * InboundHandler 用于处理数据流出本端（服务端）的 IO 事件
     */
    static class NettyServerHandler extends ChannelInboundHandlerAdapter {
        /**
         * 当通道有数据可读时执行
         *
         * @param ctx 上下文对象，可以从中取得相关联的 Pipeline、Channel、客户端地址等
         * @param msg 客户端发送的数据
         * @throws Exception
         */
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg)
                throws Exception {
            // 接收客户端发来的数据
            System.out.println("client address: " + ctx.channel().remoteAddress());

            // ByteBuf 是 Netty 提供的类，比 NIO 的 ByteBuffer 性能更高
            ByteBuf byteBuf = (ByteBuf) msg;
            System.out.println("data from client: " + byteBuf.toString(CharsetUtil.UTF_8));
        }

        /**
         * 数据读取完毕后执行
         *
         * @param ctx 上下文对象
         * @throws Exception
         */
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx)
                throws Exception {
            // 发送响应给客户端
            ctx.writeAndFlush(
                    // Unpooled 类是 Netty 提供的专门操作缓冲区的工具
                    // 类，copiedBuffer 方法返回的 ByteBuf 对象类似于
                    // NIO 中的 ByteBuffer，但性能更高
                    Unpooled.copiedBuffer("hello client! i have got your data.", CharsetUtil.UTF_8)
            );
        }

        /**
         * 发生异常时执行
         *
         * @param ctx   上下文对象
         * @param cause 异常对象
         * @throws Exception
         */
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
                throws Exception {
            // 关闭与客户端的 Socket 连接
            ctx.channel().close();
        }
    }
}
