package com.lzumetal.multithread.threadlocal;

import java.math.BigDecimal;

public class ThreadLocalTest {

    public static void main(String[] args) throws InterruptedException {
        Order order01 = new Order(1, 1, new BigDecimal(10), 1);
        new Thread(new OrderHandler(1, order01)).start();

        Order order02 = new Order(2, 2, new BigDecimal(20), 2);
        new Thread(new OrderHandler(2, order02)).start();
    }
}
