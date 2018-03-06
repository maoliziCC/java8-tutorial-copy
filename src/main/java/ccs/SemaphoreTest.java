package ccs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @package:ccs
 * @author: lizhang
 * @date: 2018-03-06 10:36
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(5);

        for(int i=0;i<10;i++){
            final int num = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println("正在执行任务" + num);
                        Thread.sleep((long)Math.random() * 1000);
                        System.out.println("任务" + num + "执行结束");
                        semaphore.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executor.shutdown();
    }

}