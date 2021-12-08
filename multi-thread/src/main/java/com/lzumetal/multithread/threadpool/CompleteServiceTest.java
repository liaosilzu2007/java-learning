package com.lzumetal.multithread.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author liaosi
 * @date 2021-11-10
 */
@Slf4j
public class CompleteServiceTest {

    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    static class TestCallable implements Callable<Integer> {

        private int index;

        TestCallable(int index) {
            this.index = index;
        }

        @Override
        public Integer call() throws Exception {
            // 当index为3的倍数时睡眠3s
            if (index % 3 == 0) {
                TimeUnit.SECONDS.sleep(3L);
            }
            return index;
        }
    }


    @Test
    public void futureListTest() throws ExecutionException, InterruptedException {
        List<Future<Integer>> futureList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            TestCallable callable = new TestCallable(i);
            Future<Integer> future = executor.submit(callable);
            futureList.add(future);
        }
        for (Future<Integer> future : futureList) {
            log.info("线程:{} 执行任务结束", future.get());
        }
    }


    @Test
    public void completionServiceTest() throws InterruptedException, ExecutionException {
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);
        int taskCount = 0;
        for (int i = 1; i <= 5; i++) {
            TestCallable callable = new TestCallable(i);
            completionService.submit(callable);
            taskCount++;
        }
        for (int i = 0; i < taskCount; i++) {
            log.info("线程:{} 执行任务结束", completionService.take().get());
        }
    }


}
