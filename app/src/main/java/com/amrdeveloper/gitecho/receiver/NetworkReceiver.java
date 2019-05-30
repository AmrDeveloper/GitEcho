package com.amrdeveloper.gitecho.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.amrdeveloper.gitecho.utils.NetworkUtils;

public class NetworkReceiver extends BroadcastReceiver {

    private OnNetworkListener mOnNetworkListener;

    public NetworkReceiver(OnNetworkListener listener){
        mOnNetworkListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null && action.equals(android.net.ConnectivityManager.CONNECTIVITY_ACTION)) {
            boolean isConnected = NetworkUtils.isNetworkConnected(context);
            if (isConnected) mOnNetworkListener.onInternetConnected();
            else mOnNetworkListener.onInternetDisConenected();
        }
    }
}
