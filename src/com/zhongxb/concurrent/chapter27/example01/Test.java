package com.zhongxb.concurrent.chapter27.example01;

import com.zhongxb.concurrent.chapter27.example01.OrderService;
import com.zhongxb.concurrent.chapter27.example01.OrderServiceFactory;
import com.zhongxb.concurrent.chapter27.example01.OrderServiceImpl;

/**
 * @author Mr.zxb
 * @date 2018-11-01 16:53
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

        OrderService orderService = OrderServiceFactory.toActiveObject(new OrderServiceImpl());

        orderService.order("zhongxb", 13213123);

        // 立即返回
        System.out.println("Return immediately.");

        Thread.currentThread().join();
    }
}
