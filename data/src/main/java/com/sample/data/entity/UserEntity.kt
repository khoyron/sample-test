package com.sample.data.entity

import com.google.gson.annotations.SerializedName


data class UserEntity (

    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String,
    @SerializedName("username") var username : String,
    @SerializedName("email") var email : String,
    @SerializedName("address") var address : AddressEntity,
    @SerializedName("phone") var phone : String,
    @SerializedName("website") var website : String,
    @SerializedName("company") var company : CompanyEntity

)

data class GeoEntity (

    @SerializedName("lat") var lat : String,
    @SerializedName("lng") var lng : String

)

data class AddressEntity (

    @SerializedName("street") var street : String,
    @SerializedName("suite") var suite : String,
    @SerializedName("city") var city : String,
    @SerializedName("zipcode") var zipcode : String,
    @SerializedName("geo") var geo : GeoEntity

)

data class CompanyEntity (

    @SerializedName("name") var name : String,
    @SerializedName("catchPhrase") var catchPhrase : String,
    @SerializedName("bs") var bs : String

)