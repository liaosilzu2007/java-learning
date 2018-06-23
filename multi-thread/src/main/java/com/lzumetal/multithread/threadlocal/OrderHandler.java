package com.lzumetal.multithread.threadlocal;

public class OrderHandler implements Runnable {

    private static OrderService orderService = new OrderService();

    private Integer userId;
    private Order order;

    public OrderHandler(Integer userId, Order order) {
        this.userId = userId;
        this.order = order;
    }

    @Override
    public void run() {
        EnvUtil.getUserIdContext().set(userId);
        orderService.addOrder(order);
        orderService.updateStock(order.getGoodId(), order.getGoodCount());
    }
}
