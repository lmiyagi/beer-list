package br.com.leonardomiyagi.beerlist.data.mapper.api2domain

import br.com.leonardomiyagi.beerlist.data.api.model.ApiBeer
import br.com.leonardomiyagi.beerlist.data.mapper.Mapper
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import javax.inject.Inject

/**
 * Created by lmiyagi on 2/1/18.
 */
class ApiBeerToBeerMapper @Inject constructor() : Mapper<ApiBeer, Beer>() {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun map(apiBeer: ApiBeer): Beer {
        val beer = Beer()
        beer.id = apiBeer.id
        beer.name = apiBeer.name
        beer.tagline = apiBeer.tagline
        beer.firstBrewed = apiBeer.firstBrewed
        beer.description = apiBeer.description
        beer.imageUrl = apiBeer.imageUrl
        return beer
    }
}