package com.lc.testdemo.aidlTest

import com.lc.testdemo.aidlTest.IParcelableManager


/**
 * Created by Administrator on 2019/2/24.
 */
class AIDLData : IParcelableManager.Stub() {
    override fun basicTypes(anInt: Int, aLong: Long, aBoolean: Boolean, aFloat: Float, aDouble: Double, aString: String?) {
    }

    private val dataList = arrayListOf<ParcelableData>()
    override fun addData(data: ParcelableData?) {
        data?.let {
            dataList.add(data)
        }
    }

    override fun getDatas(): MutableList<ParcelableData> {
        return dataList
    }
}