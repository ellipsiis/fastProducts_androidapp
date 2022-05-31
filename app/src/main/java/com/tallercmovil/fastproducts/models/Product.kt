package com.tallercmovil.fastproducts.models

import com.google.gson.annotations.SerializedName

class Product {
    @SerializedName("id")
    var id_sn: String? = null

    @SerializedName("name")
    var name_sn: String? = null

    @SerializedName("thumbnail_url")
    var thumbnail_sn: String? = null

    @SerializedName("price")
    var price_sn : String? = null

    @SerializedName("provider")
    var provider_sn : String? = null

    @SerializedName("delivery")
    var delivery_sn: String? = null


}