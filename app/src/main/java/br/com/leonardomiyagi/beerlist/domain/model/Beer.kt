package br.com.leonardomiyagi.beerlist.domain.model

import java.io.Serializable

/**
 * Created by lmiyagi on 2/1/18.
 */
class Beer : Serializable {

    companion object {
        private const val serialVersionUID: Long = 1L
    }

    var id: Long? = null
    var name: String? = null
    var tagline: String? = null
    var firstBrewed: String? = null
    var description: String? = null
    var imageUrl: String? = null
    val favorited: Boolean = false
}