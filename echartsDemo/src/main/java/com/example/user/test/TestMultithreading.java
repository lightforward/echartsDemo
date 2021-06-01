package com.example.user.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class TestMultithreading {

    static int tickets = 10000000;

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
//        // 开始时间
//        long beginTime = System.currentTimeMillis();

//        Future submit = service.submit(new MyCallable());

        Runnable runnable = () -> {
            while (tickets > 0) {
                try {
                    if (tickets > 0) {
                        System.out.println(Thread.currentThread().getName() + (tickets--));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Future submit = service.submit(runnable);

//        // 结束时间
//        long endTime = System.currentTimeMillis();

        //关闭线程池
        service.shutdown();

    }
}


class MyCallable implements Callable {

    static int tickets = 10000000;

    static String string = "";

    @Override
    public Object call() throws Exception {
        while (tickets > 0) {
            synchronized (string) {
                try {
                    if (tickets > 0) {
                        System.out.println(Thread.currentThread().getName() + (tickets--));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}

