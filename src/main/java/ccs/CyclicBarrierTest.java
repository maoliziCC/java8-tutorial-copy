package ccs;

import java.util.concurrent.CyclicBarrier;

/**
 * @package:ccs
 * @author: lizhang
 * @date: 2018-03-06 10:35
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        int count = 3;
        CyclicBarrier cb = new CyclicBarrier(count, new Runnable() {
            @Override
            public void run() {
                //此处所有线程都调用了await方法之后，会走到这里
                System.out.println("所有线程操作完成之后都调用了await方法");
            }
        });

        for(int i=0;i<count;i++){
            new WriteLogHandler(cb).start();
        }
    }

    static class WriteLogHandler extends Thread{

        private CyclicBarrier cb = null;

        public WriteLogHandler(CyclicBarrier cb) {
            this.cb = cb;
        }

        @Override
        public void run() {
            try {
                System.out.println("线程：" + Thread.currentThread().getName() + "开始写日志");
                Thread.sleep(2000);
                System.out.println("线程：" + Thread.currentThread().getName() + "写日志结束，等待其他线程");
                cb.await();

                System.out.println("所有线程写日志数据结束，继续其他操作");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}