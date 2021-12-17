package com.lzumetal.multithread.work;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 * @date 2021-12-17
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


    public void sellTicket() {
        final int k = windows.size();
        for (int i = 0; i < k; i++) {
            final Window window = windows.get(i);
            new Thread(() -> {
                while (tickets.size() > 0) {
                    if (tickets.size() % k == window.getId()) {
                        synchronized (obj) {
                            if (tickets.size() > 0) {
                                Ticket ticket = tickets.get(0);
                                window.sell(ticket);
                                tickets.remove(0);
                            }
                        }
                        try {
                            TimeUnit.MILLISECONDS.sleep(100L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }).start();
        }
    }


    public int getRemainTicketCount() {
        return tickets.size();
    }
}
