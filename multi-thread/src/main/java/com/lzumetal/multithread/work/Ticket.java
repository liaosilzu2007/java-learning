package com.lzumetal.multithread.work;

/**
 * @author liaosi
 * @date 2021-12-17
 */
public class Ticket {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                '}';
    }
}
