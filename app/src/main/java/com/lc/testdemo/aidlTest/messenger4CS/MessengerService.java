package com.lc.testdemo.aidlTest.messenger4CS;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lc.testdemo.aidlTest.ParcelableData;

/**
 * Created by Administrator on 2019/2/24.
 */

public class MessengerService extends Service {

    private static final String TAG = "4Test";
    private static class ServerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "server handleMessage: "+msg.what);
            switch (msg.what){
                case MessageConstant.MSG_CLIENT_SEND:
                    Bundle bundle = msg.getData();
                    bundle.setClassLoader(ParcelableData.class.getClassLoader());
                    ParcelableData data = bundle.getParcelable(MessageConstant.MSG_CLIENT_SEND_PACELABLE);
                    String text = bundle.getString(MessageConstant.MSG_CLIENT_SEND_STRING);
                    Log.d(TAG, "server receive data: "+data.getName()+",age:"+data.getAge());
                    Log.d(TAG, "server receive: "+text);
                    sendToClient(msg);
                    break;
            }
        }
    }

    private static void sendToClient(Message msg){
        Messenger clientMessenger = msg.replyTo;
        Message message  = Message.obtain(null,MessageConstant.MSG_SERVER_SEND);
        Bundle bundle = new Bundle();
        bundle.putParcelable(MessageConstant.MSG_SERVER_SEND_PACELABLE,new ParcelableData("SERVER send a class implement IParcelable",9));
        bundle.putString(MessageConstant.MSG_SERVER_SEND_STRING,"SERVER send a string");
        message.setData(bundle);
        try {
            clientMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private final Messenger mServerMessenger = new Messenger(new ServerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mServerMessenger.getBinder();
    }

}
