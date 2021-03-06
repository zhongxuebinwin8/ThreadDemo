package com.zhongxb.concurrent.chapter18;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 线程安全的累加器,通过synchronized代码块控制线程安全
 *
 * @author Mr.zxb
 * @date 2018-10-30 10:56
 */
public class IntegerAccumulatorThreadSafe {

    private int init;

    public IntegerAccumulatorThreadSafe(int init) {
        this.init = init;
    }

    public int add(int i) {
        this.init += i;
        return this.init;
    }

    public int getValue() {
        return this.init;
    }

    public static void slowly() {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // 定义累加器，并且将初始值设置为0
        IntegerAccumulatorThreadSafe accumulator = new IntegerAccumulatorThreadSafe(0);

        // 定义3个线程分别启动
        IntStream.range(0, 3).forEach(i -> new Thread(() -> {
            int inc = 0;
            while (true) {
                int oldValue, result;
                synchronized (IntegerAccumulatorThreadSafe.class) {
                    // 首先获得old value
                    oldValue = accumulator.getValue();
                    // 然后调用add方法
                    result = accumulator.add(inc);
                }
                System.out.println(oldValue + "+" + inc + "=" + result);
                // 经过验证，不合理输出错误信息
                if (oldValue + inc != result) {
                    System.err.println("Error:" + oldValue + "+" + inc + "=" + result);
                }
                inc++;
                slowly();
            }
        }).start());
    }
}
