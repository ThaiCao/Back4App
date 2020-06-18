package com.app.back4app.model.product

import android.os.Parcel
import android.os.Parcelable

class PictureParcel() : Parcelable {
    private var name : String? = null
    private var url : String? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        url = parcel.readString()
    }

    fun setName(name_: String){
        this.name = name_
    }

    fun setUrl(url_: String){
        this.url = url_
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PictureParcel> {
        override fun createFromParcel(parcel: Parcel): PictureParcel {
            return PictureParcel(parcel)
        }

        override fun newArray(size: Int): Array<PictureParcel?> {
            return arrayOfNulls(size)
        }
    }

}