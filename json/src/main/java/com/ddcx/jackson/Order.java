package com.ddcx.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * Created by liaosi on 2017/12/6.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

    private long id;
    private String OrderNo;
    private Byte type;
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
