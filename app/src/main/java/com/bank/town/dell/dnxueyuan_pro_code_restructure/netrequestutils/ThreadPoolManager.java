package com.bank.town.dell.dnxueyuan_pro_code_restructure.netrequestutils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by dell on 2019/3/17.
 */

public class ThreadPoolManager {
    //请求队列
    // 无限容量   柱塞式队列
    private LinkedBlockingQueue<Runnable> qunune = new LinkedBlockingQueue<>();

    //请求处理中心

    /**
     * 线程池
     * */
    private ThreadPoolExecutor threadPoolExecutor;
    private static final ThreadPoolManager ourInstance = new ThreadPoolManager();
    public static ThreadPoolManager getInstance(){
        return ourInstance;
    }
    /**
     * 100 并发 请求
     * */
    private ThreadPoolManager() {
        threadPoolExecutor = new ThreadPoolExecutor(4,20,20, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4), rejectedExecutionHandler);
        // 开启传送带
        threadPoolExecutor.execute(runnable);

    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                Runnable runnable = null;
                try {
                    //当队列里面没有请求时 一直阻塞在这里
                   runnable = qunune.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(runnable == null) {
                    //执行请求
                    threadPoolExecutor.execute(runnable);

                }

            }
        }
    };

    /**
     * 添加一个请求
     * @params runnable
     * */
    public void excute(Runnable runnable) {
        if(runnable != null) {
            qunune.add(runnable);
        }
    }

    private RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                qunune.put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

}
