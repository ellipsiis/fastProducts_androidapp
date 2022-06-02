package com.tallercmovil.fastproducts.models

import com.google.gson.annotations.SerializedName

class ProductDetail {
    @SerializedName("name")
    var name_detail: String? = null

    @SerializedName("imag_url")
    var image_detail: String? = null

    @SerializedName("desc")
    var desc_detail: String? = null
}