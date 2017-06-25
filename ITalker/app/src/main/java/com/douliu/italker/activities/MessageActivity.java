package com.douliu.italker.activities;

import android.content.Context;
import android.content.Intent;

import com.douliu.italker.R;
import com.example.commom.app.BaseActivity;
import com.example.commom.factory.model.Author;

public class MessageActivity extends BaseActivity {

    private static final String EXTRA_AUTHOR = "extra_author";

    public static void show(Context context, Author author) {
        Intent intent = new Intent(context, MessageActivity.class);
        intent.putExtra(EXTRA_AUTHOR, author);
        context.startActivity(intent);
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_message;
    }
}
