package com.sample.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class AlbumModel (
    var idAlbum   :String = "",
    var tumbnails : ArrayList<PhotoModel> = ArrayList(),
    var title     :String = ""
): Parcelable