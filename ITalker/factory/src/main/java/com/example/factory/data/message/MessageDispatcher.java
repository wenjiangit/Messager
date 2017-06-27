package com.example.factory.data.message;


import com.example.factory.data.user.UserDispatcher;
import com.example.factory.model.card.MessageCard;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 * Created by douliu on 2017/6/27.
 */

public class MessageDispatcher implements MessageCenter{

    private static MessageDispatcher instance;

    //维护一个单线程池进行统一的线程调度
    private static final Executor executor = Executors.newSingleThreadExecutor();

    public static MessageDispatcher instance() {
        if (instance == null) {
            synchronized (MessageDispatcher.class) {
                if (instance == null) {
                    instance = new MessageDispatcher();
                }
            }
        }
        return instance;
    }

    private MessageDispatcher(){}


    @Override
    public void dispatch(MessageCard... cards) {

    }
}
