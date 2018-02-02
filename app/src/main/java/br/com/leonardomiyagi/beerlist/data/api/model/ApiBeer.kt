package br.com.leonardomiyagi.beerlist.data.api.model

import com.google.gson.annotations.SerializedName

/**
 * Created by lmiyagi on 2/1/18.
 */
class ApiBeer {

    @SerializedName("id")
    var id: Long? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("tagline")
    var tagline: String? = null
    @SerializedName("first_brewed")
    var firstBrewed: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("image_url")
    var imageUrl: String? = null
}