package com.lzumetal.multithread.work;


import java.time.LocalTime;

/**
 * 售票窗口
 */
public class Window {

    private Integer id;

    private String name;

    public Window(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Window{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 售票
     *
     * @param ticket 出售的Ticket
     */
    public void sell(Ticket ticket) {
        System.out.println(LocalTime.now().toString() + " - " + name + " 卖出一张票：" + ticket);
    }

}
