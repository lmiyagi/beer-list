package br.com.leonardomiyagi.beerlist.data.repository

import br.com.leonardomiyagi.beerlist.data.api.ApiClient
import br.com.leonardomiyagi.beerlist.data.mapper.api2domain.ApiBeerToBeerMapper
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.domain.repository.BeerRepository
import io.reactivex.Single

/**
 * Created by lmiyagi on 2/1/18.
 */
class DefaultBeerRepository(private val apiClient: ApiClient,
                            private val apiToDomainMapper: ApiBeerToBeerMapper) : BeerRepository {

    override fun getBeers(): Single<List<Beer>> {
        return apiClient.getBeers().map(apiToDomainMapper::mapCollection)
    }

    override fun getBeer(): Single<Beer> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}