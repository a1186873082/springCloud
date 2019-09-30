package com.example.user.util;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 实现线程池
 */
public class FixedSizeThreadPool {
    //创建阻塞队列
    BlockingQueue<Runnable> blockingQueue;

    //需要一个线程的集合
    private List<Thread> workets;

    //线程池初始化
    public FixedSizeThreadPool(int poolSize, int taskSize) throws Exception {
        //初始化有多少个线程
        if (poolSize <= 0 || taskSize <= 0) {
            throw new IllegalAccessException("非法参数");
        }
        this.blockingQueue = new LinkedBlockingQueue<>(taskSize);
        this.workets = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker(this);
            worker.start();
            workets.add(worker);
        }
    }

    //具体干活的线程
    public static class Worker extends Thread {
        private FixedSizeThreadPool pool;

        public Worker(FixedSizeThreadPool pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            while (this.pool.isWorking || this.pool.blockingQueue.size() > 0) {
                Runnable task = null;
                try {
                    if (this.pool.isWorking) {
                        task = this.pool.blockingQueue.take();
                    } else {
                        task = this.pool.blockingQueue.poll();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (task != null) {
                    task.run();
                    System.out.println("线程:" + Thread.currentThread().getName() + "正在运行");
                }

            }
        }
    }

    /**
     * 把任务提交到仓库中的方法
     */
    public boolean summitTask(Runnable task) {
        if (isWorking) {
            return this.blockingQueue.offer(task);
        }
        return false;
    }

    /**
     * 线程池关闭:
     * 1.仓库停止接受任务
     * 2.一旦仓库中还有任务，需要执行完毕
     * 3.拿任务时，就不该阻塞了
     * 4.一旦任务已经阻塞了。我们需要终端他
     */
    private volatile boolean isWorking = true;

    public void shutDown() {
        //执行关闭
        this.isWorking = false;
        workets.forEach(p -> {
            if (p.getState().equals(Thread.State.BLOCKED)) {
                p.interrupt();
            }
        });
    }

    public static void main(String[] args) {
        FixedSizeThreadPool pool = null;
        try {
            pool = new FixedSizeThreadPool(3, 6);
            for (int i = 0; i < 8; i++) {
                pool.summitTask(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("线程开始执行");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.shutDown();
        }

    }
}
