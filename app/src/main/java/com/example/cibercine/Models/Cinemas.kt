package com.example.cibercine.Models

import android.os.Parcel
import android.os.Parcelable

data class Cinemas(
    var Place: String?=null,
    var Street: String?=null,
    var Theaters: String?=null,
    var Image: String?=null,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Place)
        parcel.writeString(Street)
        parcel.writeString(Theaters)
        parcel.writeString(Image)
    }

    companion object CREATOR : Parcelable.Creator<Cinemas> {
        override fun createFromParcel(parcel: Parcel): Cinemas {
            return Cinemas(parcel)
        }

        override fun newArray(size: Int): Array<Cinemas?> {
            return arrayOfNulls(size)
        }
    }
}


