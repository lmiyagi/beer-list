package br.com.leonardomiyagi.beerlist.data.mapper.domain2realm

import br.com.leonardomiyagi.beerlist.data.local.model.RealmBeer
import br.com.leonardomiyagi.beerlist.data.mapper.Mapper
import br.com.leonardomiyagi.beerlist.domain.model.Beer

/**
 * Created by lmiyagi on 2/4/18.
 */
class BeerToRealmBeerMapper : Mapper<Beer, RealmBeer>() {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun map(beer: Beer): RealmBeer {
        val realmBeer = RealmBeer()
        realmBeer.id = beer.id
        realmBeer.name = beer.name
        realmBeer.tagline = beer.tagline
        realmBeer.firstBrewed = beer.firstBrewed
        realmBeer.description = beer.description
        realmBeer.imageUrl = beer.imageUrl
        return realmBeer
    }
}