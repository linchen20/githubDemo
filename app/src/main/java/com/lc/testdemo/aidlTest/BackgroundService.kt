package com.lc.testdemo.aidlTest

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

/**
 * Created by Administrator on 2019/2/23.
 */
class BackgroundService :Service(){

    private var i =0
    override fun onBind(intent: Intent): IBinder {
        if(intent.hasExtra(AIDLActivity.KEY_BUNDLE)) {
            val serviceReceiveData = intent.getBundleExtra(AIDLActivity.KEY_BUNDLE)
            val data = serviceReceiveData.getParcelable<ParcelableData>(AIDLActivity.KEY_PARCELABLE_DATA)
            println("name:${data.name},age:${data.age}")
            println("service  receive:${serviceReceiveData.getString(AIDLActivity.KEY_HELLO_WORLD)}")
        }
        val aidlData = AIDLData()
        aidlData.addData(ParcelableData("name:${++i}", i))
        return aidlData
    }

    private val sendData = ParcelableData("service back", 22)
    class MyBinder(val service: BackgroundService): Binder() {
        fun getSendData(): ParcelableData {
            return service.sendData
        }
    }

    override fun onUnbind(intent: Intent?): Boolean {
        println("onUnbind")
        return super.onUnbind(intent)
    }

}