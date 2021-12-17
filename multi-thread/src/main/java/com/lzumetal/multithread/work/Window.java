package com.lzumetal.multithread.work;


/**
 * @author liaosi
 * @date 2021-12-17
 */
public class Window {

    private String name;

    public Window(String name) {
        this.name = name;
    }

    public void sell(Ticket ticket) {
        System.out.println(name + "卖出一张票：" + ticket);
    }

}
