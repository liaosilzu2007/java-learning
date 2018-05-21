package com.lzumetal.multithread;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

public class MainTest {

    @Test
    public void test1() {
        ScheduledExecutorService scheduledThreadPool = null;
        try {
            scheduledThreadPool = Executors.newScheduledThreadPool(4);
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            System.out.println("submit a callable task to threadpool, current time:" + sdf.format(new Date()));
            ScheduledFuture<String> scheduledFuture = scheduledThreadPool.schedule(() -> {
                System.out.println("start to execute task, current time:" + sdf.format(new Date()));
                TimeUnit.SECONDS.sleep(3L);
                return "success";
            }, 2L, TimeUnit.SECONDS);
            System.out.println("result:" + scheduledFuture.get() + ", current time:" + sdf.format(new Date()));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            if (scheduledThreadPool != null) {
                System.out.println("close the schedule threadpool!");
                scheduledThreadPool.shutdown();
            }
        }
    }

    @Test
    public void test2(){

        ScheduledExecutorService scheduledThreadPool = null;
        try {
            scheduledThreadPool = Executors.newScheduledThreadPool(4);
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            ScheduledFuture<?> scheduledFuture = scheduledThreadPool.scheduleAtFixedRate(() -> {
                System.out.println(Thread.currentThread().getName() + " | start to execute task, current time :" + sdf.format(new Date()));
                try {
                    TimeUnit.SECONDS.sleep(2L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, 2L, 1L, TimeUnit.SECONDS);

            TimeUnit.SECONDS.sleep(15L);
            scheduledFuture.cancel(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (scheduledThreadPool != null) {
                System.out.println("close the schedule threadpool!");
                scheduledThreadPool.shutdown();
            }
        }
    }
}
