package com.atguigu.gulimall.search.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main...start...");

//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//            System.out.println("当前线程：" + Thread.currentThread().getId());
//            int i = 10 / 2;
//            System.out.println("运行结果：" + i);
//        }, executor);

        /**
         * 方法完成后的感知
         */
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("当前线程：" + Thread.currentThread().getId());
//            int i = 10 / 0;
//            System.out.println("运行结果：" + i);
//            return i;
//        }, executor).whenComplete((res,excption)->{
//            //可以得到异常信息，但是没法修改返回数据
//            System.out.println("异步任务成功完成了...结果是："+res+"；异常是："+excption);
//        }).exceptionally(throwable -> {
//            //可以感知异常，同时返回默认值
//            return 10;
//        });

        /**
         * 方法执行完成后的处理
         */
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("当前线程：" + Thread.currentThread().getId());
//            int i = 10 / 4;
//            System.out.println("运行结果：" + i);
//            return i;
//        }, executor).handle((res,throwable)->{
//            if(res!=null) {
//                return res*2;
//            }
//            if(throwable!=null) {
//                return 0;
//            }
//            return 0;
//        });

        /**
         * 线程串行化
         * 1) thenRun:不能获取到上一步的执行结果，无返回值
         * 2) thenAccept:能接收上一步的结果，但是无返回值
         * 3) thenApply:能接收上一步的结果，有返回值
         */
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 4;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).thenApplyAsync(res -> {
            System.out.println("任务2启动了..." + res);
            return "Hello " + res;
        }, executor);

        /**
         * 两个都完成，才执行任务3
         * runAfterBothAsync:不感知结果，自己无返回值
         * thenAcceptBothAsync:感知结果，自己无返回值
         * thenCombineAsync:感知结果，自己有返回值
         */
//        CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("任务1线程：" + Thread.currentThread().getId());
//            int i = 10 / 4;
//            System.out.println("任务1结束");
//            return i;
//        }, executor);
//        CompletableFuture<String> future02 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("任务2线程：" + Thread.currentThread().getId());
//            System.out.println("任务2结束");
//            return "hello";
//        }, executor);
////        future01.runAfterBothAsync(future02,()->{
////            System.out.println("任务3开始...");
////        },executor);
////        future01.thenAcceptBothAsync(future02,(f1,f2)->{
////            System.out.println("任务3开始...之前的结果："+f1+"-->"+f2);
////        },executor);
//        CompletableFuture<String> future = future01.thenCombineAsync(future02, (f1, f2) -> {
//            return f1 + "->" + f2 + "-> Haha";
//        }, executor);

        /**
         * 两个任务，只要有一个完成，就执行任务3
         * runAfterEitherAsync:不感知结果，自己无返回值
         * acceptEitherAsync:感知结果，自己无返回值
         * applyToEitherAsync:感知结果，自己有返回值
         */
//        CompletableFuture<Object> future01 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("任务1线程：" + Thread.currentThread().getId());
//            int i = 10 / 4;
//            System.out.println("任务1结束");
//            return i;
//        }, executor);
//        CompletableFuture<Object> future02 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("任务2线程：" + Thread.currentThread().getId());
//            try {
//                Thread.sleep(3000);
//                System.out.println("任务2结束");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "hello";
//        }, executor);
////        future01.runAfterEitherAsync(future02,()->{
////            System.out.println("任务3开始...");
////        },executor);
////        future01.acceptEitherAsync(future02,(res)->{
////            System.out.println("任务3开始...之前的结果："+res);
////        },executor);
//        CompletableFuture<String> future = future01.applyToEitherAsync(future02, (res) -> {
//            System.out.println("任务3开始...之前的结果：" + res);
//            return res.toString() + "->哈哈";
//        }, executor);


        CompletableFuture<String> futureImg = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("查询商品的图片信息");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello.jpg";
        }, executor);
        CompletableFuture<String> futureAttr = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("查询商品的属性信息");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "黑色+256GB";
        }, executor);
        CompletableFuture<String> futureDesc = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("查询商品介绍");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "华为";
        }, executor);
//        CompletableFuture<Void> allOf = CompletableFuture.allOf(futureImg, futureAttr, futureDesc);
//        allOf.get(); //等待所有结果完成
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(futureImg, futureAttr, futureDesc);
        anyOf.get(); //等待所有结果完成


        //System.out.println("返回结果："+future.get());

        System.out.println("main...end...");
    }
}
