package com.atguigu.gulimall.search.thread;

import java.util.concurrent.*;

public class ThreadTest {
    public static ExecutorService service = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main...start...");
        //继承Thread类
//        Thread01 thread01 = new Thread01();
//        thread01.start();
        //实现Runnable接口
//        Thread02 thread02 = new Thread02();
//        new Thread(thread02).start();
        //实现Callable接口+FutureTask
//        FutureTask<Integer> futureTask = new FutureTask<>(new Thread03());
//        new Thread(futureTask).start();
//        Integer integer=futureTask.get(); //获取线程的返回结果
//        System.out.println("线程的返回结果："+integer);
        //线程池（推荐）
//        service.execute(new Thread02());
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
                200,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        System.out.println("main...end...");
    }

    public static class Thread01 extends Thread {
        @Override
        public void run() {
            System.out.println("当前线程："+Thread.currentThread().getId());
            int i=10/2;
            System.out.println("运行结果："+i);
        }
    }

    public static class Thread02 implements Runnable {
        @Override
        public void run() {
            System.out.println("当前线程："+Thread.currentThread().getId());
            int i=10/2;
            System.out.println("运行结果："+i);
        }
    }

    public static class Thread03 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("当前线程："+Thread.currentThread().getId());
            int i=10/2;
            System.out.println("运行结果："+i);
            return i;
        }
    }
}
