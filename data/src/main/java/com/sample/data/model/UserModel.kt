package com.sample.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserModel (
    var username :String = "",
    var email    :String = "",
    var company  :String = "",
    var address  :String = "",
    var albums   : ArrayList<AlbumModel> = ArrayList()
): Parcelable