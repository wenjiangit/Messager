package com.example.factory.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.igexin.sdk.PushConsts;

/**
 *
 * Created by wenjian on 2017/6/16.
 */

public class MessageReceiver extends BroadcastReceiver {

    private static final String TAG = "MessageReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }

        Bundle bundle = intent.getExtras();
        switch (bundle.getInt(PushConsts.CMD_ACTION)) {
            case PushConsts.GET_CLIENTID:{
                String clientId = bundle.getString(PushConsts.KEY_CLIENT_ID);
                Log.i(TAG, "GET_CLIENTID: " + clientId);
                onClientInit(clientId);
                break;
            }
            case PushConsts.GET_MSG_DATA:{
                byte[] bytes = bundle.getByteArray(PushConsts.KEY_CMD_MSG);
                if (bytes != null) {
                    String message = new String(bytes);
                    Log.i(TAG, "GET_MSG_DATA: " + message);
                    onMessageArrived(message);
                }
                break;
            }
            default:
                Log.i(TAG, "onReceive: " + bundle.toString());
                break;

        }
    }

    private void onMessageArrived(String message) {

    }

    private void onClientInit(String clientId) {

    }

}
