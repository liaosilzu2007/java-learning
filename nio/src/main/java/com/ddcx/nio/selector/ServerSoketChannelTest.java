package com.ddcx.nio.selector;

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
public class ServerSoketChannelTest {

    private static final int PORT = 9090;
    private static final int BUFFER_SIZE = 1024;
    private ServerSocketChannel serverSocketChannel;
    private ByteBuffer byteBuffer;
    private Selector selector;
    private int remoteClientNum = 0;

    public ServerSoketChannelTest() {
        try {
            //在创建一个新的服务器对象时，对服务器的属性进行初始化
            init();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }

    private void init() throws IOException {
        //创建ServerSoketChannel，设置为非阻塞，并绑定端口
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(PORT));
        System.out.println("listener on port:" + PORT);

        //创建Selector
        selector = Selector.open();

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
                    remoteClientNum++;
                    System.out.println("remote client number ----> " + remoteClientNum);

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


    public static void main(String[] args) {
        try {
            new ServerSoketChannelTest().listener();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
