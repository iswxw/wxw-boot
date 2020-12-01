package com.wxw.common.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author ：wxw.
 * @date ： 15:28 2020/12/1
 * @description：NettyServer 实现服务端拆包 https://www.cnblogs.com/coding-diary/p/11650686.html
 * @version: v_0.0.1
 */ // Server 主程序
public class UpPackNettyServer {
    public static void main(String[] args) throws InterruptedException {
        // accept 处理链接的线程池
        NioEventLoopGroup acceptGroup = new NioEventLoopGroup();
        // read io 处理数据的线程池
        NioEventLoopGroup readGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(acceptGroup,readGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
                            // 增加解码器
                            pipeline.addLast(new XDecoder());
                            // 打印出内容
                            pipeline.addLast(new XHandler());
                        }
                    });
            System.out.println("启动成功，端口 7777");
            serverBootstrap.bind(7777).sync().channel().closeFuture().sync();
        } finally {
            acceptGroup.shutdownGracefully();
            readGroup.shutdownGracefully();
        }
    }
}

// 解码器
class XDecoder extends ByteToMessageDecoder {

    static final int PACKET_SIZE = 220;

    // 用来临时保留没有处理过的请求报文
    ByteBuf tempMsg = Unpooled.buffer();

    /**
     * @param ctx
     * @param in 请求的数据
     * @param out 将粘在一起的报文拆分后的结果保留起来
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println(Thread.currentThread() + "收到了一次数据包，长度是：" + in.readableBytes());

        // 合并报文
        ByteBuf message = null;
        int tmpMsgSize = tempMsg.readableBytes();
        // 如果暂存有上一次余下的请求报文，则合并
        if (tmpMsgSize > 0) {
            message = Unpooled.buffer();
            message.writeBytes(tempMsg);
            message.writeBytes(in);
            System.out.println("合并：上一数据包余下的长度为：" + tmpMsgSize + ",合并后长度为:" + message.readableBytes());
        } else {
            message = in;
        }

        int size = message.readableBytes();
        int counter = size / PACKET_SIZE;
        for (int i = 0; i < counter; i++) {
            byte[] request = new byte[PACKET_SIZE];
            // 每次从总的消息中读取220个字节的数据
            message.readBytes(request);

            // 将拆分后的结果放入out列表中，交由后面的业务逻辑去处理
            out.add(Unpooled.copiedBuffer(request));
        }

        // 多余的报文存起来
        // 第一个报文： i+  暂存
        // 第二个报文： 1 与第一次
        size = message.readableBytes();
        if (size != 0) {
            System.out.println("多余的数据长度：" + size);
            // 剩下来的数据放到tempMsg暂存
            tempMsg.clear();
            tempMsg.writeBytes(message.readBytes(size));
        }
    }
}

// 处理器
class XHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] content = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(content);
        System.out.println(Thread.currentThread() + ": 最终打印" + new String(content));
        ((ByteBuf) msg).release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
