package br.com.leonardomiyagi.beerlist.data.local.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by lmiyagi on 2/1/18.
 */
open class RealmBeer : RealmObject() {

    companion object {
        const val ID_FIELD = "id"
    }

    @PrimaryKey
    var id: Long? = null
    var name: String? = null
    var tagline: String? = null
    var firstBrewed: String? = null
    var description: String? = null
    var imageUrl: String? = null
}