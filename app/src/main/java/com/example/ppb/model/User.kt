package com.example.ppb.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("height")
    val height: Int,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("eyeColor")
    val eyeColor: String?,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("birthDate")
    val birthDate: String?,
    @SerializedName("university")
    val university: String?
) : Parcelable {
    //Parcelable untuk mengizinkan objek User untuk dikirimkan melalui intent
    constructor(parcel: Parcel) : this(
        // Membaca nilai-nilai dari Parcel dan
        // menggunakan konstruktor utama untuk membuat objek User.
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        // Menulis nilai-nilai properti User ke Parcel.
        parcel.writeString(firstName)
        parcel.writeString(gender)
        parcel.writeInt(height)
        parcel.writeString(phone)
        parcel.writeString(image)
        parcel.writeString(email)
        parcel.writeString(eyeColor)
        parcel.writeString(lastName)
        parcel.writeString(birthDate)
        parcel.writeString(university)
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
