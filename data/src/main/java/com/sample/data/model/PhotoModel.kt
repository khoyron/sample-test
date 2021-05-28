package com.sample.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PhotoModel (
    var url       :String = "",
    var thumbnail :String = "",
    var title     :String = ""
): Parcelable