package com.lc.testdemo.aidlTest.messenger4CS;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lc.testdemo.R;
import com.lc.testdemo.aidlTest.ParcelableData;

/**
 * 通过Messenger 实现SERVER/CLIENT互相通信
 * 通过bundle传递Parcelable类型数据时,需要将bundle的classLoader类型
 * 设置为apk的classLoader，因为过程中classLoader会被转换成frame-work的classLoader
 * 在来回通信的时候要将messenger绑定到msg.replyTo上
 * 绑定关系handler->Messenger->msg.replyTo
 * 通过这个关系在发送msg的时候才能找到是对应的handler
 * Created by Administrator on 2019/2/24.
 */

public class MessengerActivity extends AppCompatActivity {
    private static final String TAG = "4Test";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        startService(new Intent(this, MessengerService.class));
    }

    public void bindService(View view) {
        Intent intent = new Intent(this, MessengerService.class);

        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    public void unbindService(View view) {
        unbind();
    }

    private void unbind() {
        if (mConnection != null) {
            unbindService(mConnection);
        }
        stopService(new Intent(this, MessengerService.class));
    }

    private Messenger clientSendMessenger;
    private Messenger clientReceiveMessenger = new Messenger(new ClientReceiveHandler());

    private static class ClientReceiveHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessageConstant.MSG_SERVER_SEND:
                    Bundle bundle = msg.getData();
                    bundle.setClassLoader(ParcelableData.class.getClassLoader());
                    ParcelableData data = bundle.getParcelable(MessageConstant.MSG_SERVER_SEND_PACELABLE);
                    String text = bundle.getString(MessageConstant.MSG_SERVER_SEND_STRING);
                    Log.d(TAG, "client receive data: " + data.getName() + ",age:" + data.getAge());
                    Log.d(TAG, "client receive: " + text);
                    break;
            }
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            //clientSendMessenger 就是 service中的mServerMessenger
            clientSendMessenger = new Messenger(iBinder);
            Message msg = Message.obtain(null, MessageConstant.MSG_CLIENT_SEND);
            Bundle bundle = new Bundle();
            bundle.putParcelable(MessageConstant.MSG_CLIENT_SEND_PACELABLE, new ParcelableData("client send a class implement IParcelable", 9));
            bundle.putString(MessageConstant.MSG_CLIENT_SEND_STRING, "client send a string");
            msg.setData(bundle);
            //把用来发送给server的messenger告诉Service那
            msg.replyTo = clientReceiveMessenger;
            try {
                clientSendMessenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        unbind();
        super.onDestroy();
    }
}
