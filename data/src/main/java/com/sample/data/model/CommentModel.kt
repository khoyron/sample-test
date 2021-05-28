package com.sample.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CommentModel (
    var autorName :String = "",
    var body      :String = ""
): Parcelable