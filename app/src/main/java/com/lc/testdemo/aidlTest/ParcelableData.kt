package com.lc.testdemo.aidlTest

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Administrator on 2019/2/23.
 */
class ParcelableData(val name:String,val age:Int) :Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt()) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeString(name)
        dest!!.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParcelableData> {
        override fun createFromParcel(parcel: Parcel): ParcelableData {
            return ParcelableData(parcel)
        }

        override fun newArray(size: Int): Array<ParcelableData?> {
            return arrayOfNulls(size)
        }
    }

}