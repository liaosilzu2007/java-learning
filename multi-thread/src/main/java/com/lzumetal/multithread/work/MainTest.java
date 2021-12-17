package com.lzumetal.multithread.work;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 * @date 2021-12-17
 */
public class MainTest {


    @Test
    public void testSellTicket() throws InterruptedException {
        TicketOffice ticketOffice = new TicketOffice(100, 3);
        ticketOffice.sellTicket();
        while (true) {
            int rtc = ticketOffice.getRemainTicketCount();
            if (rtc == 0) {
                break;
            }
            TimeUnit.MILLISECONDS.sleep(100L);
        }
    }


}
