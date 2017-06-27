package com.example.factory.data.group;

import android.text.TextUtils;

import com.example.factory.data.helper.GroupHelper;
import com.example.factory.data.user.UserDispatcher;
import com.example.factory.model.card.GroupCard;
import com.example.factory.model.card.GroupMemberCard;
import com.example.factory.model.db.Group;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 * Created by douliu on 2017/6/27.
 */

public class GroupDispatcher implements GroupCenter {

    private static GroupDispatcher instance;

    //维护一个单线程池进行统一的线程调度
    private static final Executor executor = Executors.newSingleThreadExecutor();

    public static GroupDispatcher instance() {
        if (instance == null) {
            synchronized (GroupDispatcher.class) {
                if (instance == null) {
                    instance = new GroupDispatcher();
                }
            }
        }
        return instance;
    }

    private GroupDispatcher(){}


    @Override
    public void dispatch(GroupCard... cards) {
        if (cards == null || cards.length == 0) {
            return;
        }

        executor.execute();

    }

    @Override
    public void dispatch(GroupMemberCard... cards) {

    }


    static class GroupCardHandler implements Runnable {
        private final GroupCard[] mCards;

        GroupCardHandler(GroupCard[] cards) {
            mCards = cards;
        }

        @Override
        public void run() {
            for (GroupCard card : mCards) {
                if (card == null || TextUtils.isEmpty(card.getId())) {
                    continue;
                }

                Group group = GroupHelper.find(card.getId());
                if (group != null) {
                    Group build = card.build(group.getOwner());
                }
            }
        }
    }
}

