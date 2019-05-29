package com.example.to3et.qrreader.model

import android.os.Parcel
import android.os.Parcelable

data class SimpleBarcodeResult(
        val rawBytes: ByteArray,
        val text: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.createByteArray(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByteArray(rawBytes)
        parcel.writeString(text)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SimpleBarcodeResult> {
        override fun createFromParcel(parcel: Parcel): SimpleBarcodeResult {
            return SimpleBarcodeResult(parcel)
        }

        override fun newArray(size: Int): Array<SimpleBarcodeResult?> {
            return arrayOfNulls(size)
        }
    }
}