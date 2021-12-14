package com.lzumetal.nio.selector;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 类描述：NIO服务端
 * 创建人：liaosi
 * 创建时间：2017年12月08日
 */
@Slf4j
public class ServerSoketChannelTest {

    private static final int PORT = 9090;
    private static final int BUFFER_SIZE = 1024;
    private ServerSocketChannel serverSocketChannel;
    private ByteBuffer byteBuffer;
    private Selector selector;

    public ServerSoketChannelTest(Selector selector) {
        try {
            //在创建一个新的服务器对象时，对服务器的属性进行初始化
            this.selector = selector;
            init();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }

    private void init() throws IOException {
        //创建ServerSoketChannel，设置为非阻塞，并绑定端口
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().setReuseAddress(true);
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(PORT));
        log.info(serverSocketChannel + "============listener on port:" + PORT);


        //将通道注册到选择器上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //创建一个直接缓冲区
        byteBuffer = ByteBuffer.allocateDirect(BUFFER_SIZE);
        byteBuffer.order(ByteOrder.BIG_ENDIAN);

    }

    private void listener() throws IOException, InterruptedException {

        while (true) {

            //获取已经就绪的通道
            int select = selector.select();
            //如果已经没有已经就绪的通道，每隔1s查询一次
            if (select == 0) {
                TimeUnit.SECONDS.sleep(1);
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                //如果某个key对应的通道接收就绪
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverChannel.accept();
                    registerSocketChannel(selector, socketChannel, SelectionKey.OP_READ);

                    //向客户端发送数据
                    replyClient(socketChannel);
                }

                //如果某个key对应的通道读就绪
                if (selectionKey.isReadable()) {
                    readDataFromSocket(selectionKey);
                }
                keyIterator.remove();
            }

        }
    }

    private void registerSocketChannel(Selector selector, SocketChannel channel, int opt) throws IOException {
        if (channel == null) {
            return;
        }
        channel.configureBlocking(false);
        channel.register(selector, opt);
    }

    private void replyClient(SocketChannel channel) throws IOException {
        byteBuffer.clear();
        byteBuffer.put("Hello client! I'm the Server".getBytes());
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            channel.write(byteBuffer);
        }
    }

    private void readDataFromSocket(SelectionKey selectionKey) throws IOException {
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        int count;
        byteBuffer.clear();
        while ((count = channel.read(byteBuffer)) > 0) {
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                System.out.println("receive from client:" + StandardCharsets.UTF_8.decode(byteBuffer));
            }
            byteBuffer.clear();
        }

        if (count < 0) {
            channel.close();
        }

    }


    public static void main(String[] args) throws IOException, InterruptedException {
        //创建Selector
        Selector selector = Selector.open();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        new ServerSoketChannelTest(selector).listener();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "server-" + i).start();
            TimeUnit.SECONDS.sleep(1);

        }
    }
}
