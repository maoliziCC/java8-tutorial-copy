package ccs;

import java.util.concurrent.CountDownLatch;

/**
 * https://www.cnblogs.com/dolphin0520/p/3920397.html
 * @package:ccs
 * @author: lizhang
 * @date: 2018-03-06 10:05
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws Exception {
        final CountDownLatch cdl = new CountDownLatch(2);
        new Thread(){
            public void run() {
                try {
                    System.out.println("等待老爸回家...");
                    Thread.sleep(5000);
                    cdl.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            };
        }.start();

        new Thread(){
            public void run() {
                try {
                    System.out.println("等待老妈回家...");
                    Thread.sleep(5000);
                    cdl.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();

        cdl.await();
        System.out.println("老爸老妈回来了...");
        System.out.println("晚宴开始了...");
    }

}