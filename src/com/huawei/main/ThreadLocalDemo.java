package com.huawei.main;

/*
ThreadLocal是线程本地变量，可以为多线程的并发问题提供一种解决方式
    多个线程去获取一个共享变量时，要求获取的是这个变量的初始值的副本、每个
    线程存储这个变量副本，对这个变量副本的改变不去影响变量本身。
 */
public class ThreadLocalDemo {
    private static ThreadLocal<Index> index = new ThreadLocal(){

        @Override
        protected Object initialValue() {
            return new Index();
        }
    };

    private static class Index{
        private int num;
        public void incr(){
            num++;
        }
    }

    public static void main(String[] args) {
        for (int i=0; i<5; i++){
            new Thread(()->{
                Index local = index.get();
                local.incr();
                System.out.println(Thread.currentThread().getName()+" "+index.get().num);
            },"thread_"+i).start();
        }
    }
}
