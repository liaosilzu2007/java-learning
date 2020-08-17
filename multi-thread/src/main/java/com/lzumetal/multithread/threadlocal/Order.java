package com.lzumetal.multithread.threadlocal;


import lombok.ToString;

import java.math.BigDecimal;

@ToString
public class Order {

    private Integer id;     //订单id
    private Integer goodId; //商品id
    private BigDecimal totalPrice;  //订单总价
    private int goodCount;  //订单的商品数量
    private Integer userId; //用户id

    public Order(Integer id, Integer goodId, BigDecimal totalPrice, int goodCount) {
        this.id = id;
        this.goodId = goodId;
        this.totalPrice = totalPrice;
        this.goodCount = goodCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public int getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(int goodCount) {
        this.goodCount = goodCount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }



}
