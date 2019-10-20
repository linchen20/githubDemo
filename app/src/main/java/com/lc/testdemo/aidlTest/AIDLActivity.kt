package com.lc.testdemo.aidlTest

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import com.lc.testdemo.R

/**
 * 通过aidl实现activity和service通信
 * 顺序:
 * 同进程时
 * 1.activity向service发送信息通过intent
 * 2.service向activity发送信息通过nBind返回的IBinder实现类(MyBinder)
 * 跨进程时
 * 1.activity向service发送信息通过intent
 * 2.service向activity发送信息通过onBind返回的IBinder实现类(AIDLData)
 * 注意事项：
 * 1.需要传递是数据类型实现了parcelable接口，则要为其对应创建一个aidl文件(ParcelableData.aidl)
 * 2.创建一个声明数据操作的aidl接口(IParcelableManager.aidl)
 * 3.在onServiceConnected中使用IParcelableManager.Stub.asInterface(iBinder)获得AIDLData对象
 * 从而实现了双向通信
 *
 * --------------给binder 设置 IBinder.DeathRecipient 死亡代理-------------
 * #binderDied 中
 * IAIDLIml.asBinder.unlinkToDeath(mDeathRecipient,0);
 * IAIDLIml = null;
 * #onServiceConnected中
 * binder.linkToDeath(mDeathRecipient,0)
 * --------------给binder 设置 IBinder.DeathRecipient 死亡代理-------------
 */
class AIDLActivity : AppCompatActivity() {


    private lateinit var mServiceConn: MyServiceConn
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aidl)

        mServiceConn = MyServiceConn()
    }

    private var activityBinder: IParcelableManager? = null
    inner class MyServiceConn:ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {
            println("onServiceDisconnected")
        }

        override fun onServiceConnected(name: ComponentName?, iBinder: IBinder?) {
            println("onServiceConnected")

            iBinder?.let {
                //aidl
                activityBinder = IParcelableManager.Stub.asInterface(iBinder)
//                activityBinder = iBinder as BackgroundService.MyBinder
                activityBinder!!.datas.forEach {
                    println("activity  receive name:${it.name},age:${it.age}")
                }
            }
        }
    }

    fun bindService(view: View){
        val intent = Intent(this, BackgroundService::class.java)
        val bundle = Bundle()
        bundle.putParcelable(KEY_PARCELABLE_DATA, ParcelableData("mike", 11))
        bundle.putString(KEY_HELLO_WORLD,"hello_world")
        intent.putExtra(KEY_BUNDLE,bundle)
        bindService(intent,mServiceConn,BIND_AUTO_CREATE)
    }

    fun unbindService(view:View){
        unbindService(mServiceConn)
    }

    companion object {
        val KEY_PARCELABLE_DATA = "KEY_PARCELABLE_DATA"
        val KEY_HELLO_WORLD = "KEY_HELLO_WORLD"
        val KEY_BUNDLE = "KEY_BUNDLE"
    }
}
