package com.lzumetal.multithread.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 * @date 2021-11-11
 */
@Slf4j
public class OrderDelay implements Delayed {

    private String orderId;

    //超时的时间点（ms时间戳）
    private long timeoutStamp;

    public OrderDelay(String orderId, long timeoutMs) {
        this.orderId = orderId;
        this.timeoutStamp = timeoutMs + System.currentTimeMillis();
    }

    /**
     * 对于给定的TimeUnit，距离你自定义的超时截止时间点还有多久
     *
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(timeoutStamp - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    /**
     * 比较两个对象谁更快要到延迟的时间截止点。
     * <p>
     * 因为Delayed对象是可以放到DelayQueue队列里面的，但放到的时候不是依次放入队列，而是通过compareTo来判断放入的位置，
     * 这样从队列取元素的时候，总是拉取到最快要达到时间截止点的数据。
     *
     * @param other
     * @return
     */
    @Override
    public int compareTo(Delayed other) {
        if (other == this) {
            return 0;
        }
        if (!(other instanceof OrderDelay)) {
            throw new IllegalArgumentException();
        }
        OrderDelay t = (OrderDelay) other;
        long d = (getDelay(TimeUnit.MILLISECONDS) - t.getDelay(TimeUnit.MILLISECONDS));
        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
    }


    public void dealTimeOutOrder() {
        log.info("处理超时的订单|订单号={}", orderId);
    }

}
