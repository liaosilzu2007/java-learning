package com.lzumetal.multithread.work;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 售票商店
 * <p>
 * 一个售票商店有多个售票窗口（Window）
 */
public class TicketOffice {

    private final Object obj = new Object();
    private volatile List<Ticket> tickets;
    private List<Window> windows;


    public TicketOffice(int ticketCount, int windowCount) {
        if (ticketCount <= 0 || windowCount <= 0) {
            throw new IllegalArgumentException();
        }
        tickets = new LinkedList<>();
        for (int i = 0; i < ticketCount; i++) {
            Ticket ticket = new Ticket();
            ticket.setId((long) (i + 1));
            tickets.add(ticket);
        }
        windows = new ArrayList<>();
        for (int i = 0; i < windowCount; i++) {
            Window window = new Window(i, "Window " + (i + 1));
            windows.add(window);
        }
    }


    /**
     * 商店售票
     */
    public void sellTicket() {
        final int k = windows.size();
        for (int i = 0; i < k; i++) {
            final Window window = windows.get(i);
            new Thread(() -> {
                while (tickets.size() > 0) {
                    if (tickets.size() % k == window.getId()) {
                        try {
                            synchronized (obj) {    //轻量级锁
                                if (tickets.size() > 0) {
                                    Ticket ticket = tickets.get(0);
                                    tickets.remove(0);  //先移除，再调sell方法
                                    window.sell(ticket);
                                }
                            }
                            TimeUnit.MILLISECONDS.sleep(100L);
                        } catch (Throwable t) {
                            t.printStackTrace();
                        }
                    }
                }

            }).start();
        }
    }


    /**
     * 剩余票的数量
     *
     * @return
     */
    public int getRemainTicketCount() {
        return tickets.size();
    }
}
