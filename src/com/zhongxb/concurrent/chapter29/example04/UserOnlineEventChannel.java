package com.zhongxb.concurrent.chapter29.example04;

import com.zhongxb.concurrent.chapter29.example02.Event;
import com.zhongxb.concurrent.chapter29.example03.AsyncChannel;

/**
 * 用户上线的Event，简单输出用户上线即可
 * @author Mr.zxb
 * @date 2018-11-07 17:33
 */
public class UserOnlineEventChannel extends AsyncChannel {

    @Override
    protected void handle(Event message) {
        UserOnlineEvent event = (UserOnlineEvent) message;
        System.out.println("The User [" + event.getUser().getName() + "] is online.");
    }
}
