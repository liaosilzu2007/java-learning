package com.lzumetal.multithread.semaphore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author liaosi
 * @date 2020-08-17
 */
@Slf4j
public class SemaphoreTest {


    private ExecutorService threadPool = Executors.newFixedThreadPool(10);


    @Test
    public void test() {
        //信号量，只允许3个线程同时操作
        Semaphore semaphore = new Semaphore(3);
        List<Future> futureList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            final Customer customer = new Customer(i + 1);
            Future<?> future = threadPool.submit(() -> {
                try {
                    semaphore.acquire();//会阻塞直到获取许可
                    log.info("{}进入了餐馆", customer);
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    log.info("{}离开了餐馆", customer);
                    semaphore.release();    //释放
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            futureList.add(future);
        }
        for (Future future : futureList) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }


    @Getter
    @Setter
    @ToString
    class Customer {

        private Integer id;

        Customer(Integer id) {
            this.id = id;
        }
    }
}
