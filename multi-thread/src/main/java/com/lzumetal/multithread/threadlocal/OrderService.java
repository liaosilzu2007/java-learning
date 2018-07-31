package com.lzumetal.multithread.threadlocal;

public class OrderService {


    /**
     * 新增订单
     *
     * @param order
     */
    public void addOrder(Order order) {
        Integer userId = EnvUtil.getUserIdContext().get();
        System.out.println(Thread.currentThread().getName() + "新增订单服务中获取用户id-->" + userId);
    }


    /**
     * 更新库存
     *
     * @param goodId
     * @param goodCount
     */
    public void updateStock(Integer goodId, Integer goodCount) {
        //虽然更新库存不需要关注userId，但是在这里也一样能够获取到
        Integer userId = EnvUtil.getUserIdContext().get();
        System.out.println(Thread.currentThread().getName() + "在更新库存服务中获取用户id-->" + userId);
    }


}
