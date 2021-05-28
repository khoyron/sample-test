package com.sample.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PostModel (
    var profile :UserModel = UserModel(),
    var userId   :String = "",
    var idPost   :String = "",
    var title    :String = "",
    var body     :String = ""
): Parcelable