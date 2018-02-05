package br.com.leonardomiyagi.beerlist.data.mapper.realm2domain

import br.com.leonardomiyagi.beerlist.data.local.model.RealmBeer
import br.com.leonardomiyagi.beerlist.data.mapper.Mapper
import br.com.leonardomiyagi.beerlist.domain.model.Beer

/**
 * Created by lmiyagi on 2/4/18.
 */
class RealmBeerToBeerMapper : Mapper<RealmBeer, Beer>() {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun map(realmBeer: RealmBeer): Beer {
        val beer = Beer()
        beer.id = realmBeer.id
        beer.name = realmBeer.name
        beer.tagline = realmBeer.tagline
        beer.firstBrewed = realmBeer.firstBrewed
        beer.description = realmBeer.description
        beer.imageUrl = realmBeer.imageUrl
        beer.favorited = true
        return beer
    }
}