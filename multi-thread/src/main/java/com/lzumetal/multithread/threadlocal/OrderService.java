package com.lzumetal.multithread.threadlocal;

public class OrderService {


    /**
     * 新增订单
     * @param order
     */
    public void addOrder(Order order) {
        Integer userId = EnvUtil.getUserIdContext().get();
        System.out.println(Thread.currentThread().getName() + "：新增订单：" + "用户id-->" + userId);
        order.setUserId(userId);
        System.out.println(Thread.currentThread().getName() + "：新增订单 --> " + order.toString());
    }


    /**
     * 更新库存
     * @param goodId
     * @param goodCount
     */
    public void updateStock(Integer goodId, Integer goodCount) {
        //虽然更新库存不需要关注userId，但是在这里也一样能够获取到
        System.out.println(Thread.currentThread().getName() + "：更新库存 --> goodId：" + goodId + ",goodCount：" + goodCount +
                ",用户id：" + EnvUtil.getUserIdContext().get());
    }


}
