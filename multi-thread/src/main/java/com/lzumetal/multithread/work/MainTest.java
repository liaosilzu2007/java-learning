package com.lzumetal.multithread.work;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;


public class MainTest {


    @Test(expected = IllegalArgumentException.class)
    public void testTicketOfficeCreate() {
        new TicketOffice(-1, 10);
        new TicketOffice(100, -1);

    }


    @Test
    public void testSellTicket() throws InterruptedException {
        TicketOffice ticketOffice = new TicketOffice(100, 3);
        ticketOffice.sellTicket();
        while (true) {
            int rtc = ticketOffice.getRemainTicketCount();
            Assert.assertTrue(rtc >= 0);
            if (rtc == 0) {
                break;
            }
            TimeUnit.MILLISECONDS.sleep(100L);
        }
    }


}
