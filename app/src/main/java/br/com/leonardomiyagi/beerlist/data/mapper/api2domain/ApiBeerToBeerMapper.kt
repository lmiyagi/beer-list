package br.com.leonardomiyagi.beerlist.data.mapper.api2domain

import br.com.leonardomiyagi.beerlist.data.api.model.ApiBeer
import br.com.leonardomiyagi.beerlist.data.mapper.Mapper
import br.com.leonardomiyagi.beerlist.domain.model.Beer

/**
 * Created by lmiyagi on 2/1/18.
 */
class ApiBeerToBeerMapper : Mapper<ApiBeer, Beer>() {

    override fun map(apiBeer: ApiBeer): Beer {
        val beer = Beer()
        beer.id = apiBeer.id
        beer.name = apiBeer.name
        beer.tagline = apiBeer.tagline
        beer.first_brewed = apiBeer.first_brewed
        beer.description = apiBeer.description
        beer.image_url = apiBeer.image_url
        return beer
    }
}