package br.com.leonardomiyagi.beerlist.data.local.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by lmiyagi on 2/1/18.
 */
class RealmBeer : RealmObject() {

    @PrimaryKey
    var id: Long? = null
    var name: String? = null
    var tagline: String? = null
    var first_brewed: String? = null
    var description: String? = null
    var image_url: String? = null
}