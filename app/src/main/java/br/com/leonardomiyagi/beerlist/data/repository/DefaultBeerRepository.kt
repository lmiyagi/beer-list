package br.com.leonardomiyagi.beerlist.data.repository

import br.com.leonardomiyagi.beerlist.data.api.ApiClient
import br.com.leonardomiyagi.beerlist.data.api.model.ApiBeer
import br.com.leonardomiyagi.beerlist.data.mapper.Mapper
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.domain.repository.BeerRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by lmiyagi on 2/1/18.
 */
class DefaultBeerRepository @Inject constructor(private val apiClient: ApiClient,
                                                private val apiToDomainMapper: Mapper<ApiBeer, Beer>) : BeerRepository {

    override fun getBeers(): Single<List<Beer>> {
        return apiClient.getBeers().map(apiToDomainMapper::mapCollection)
    }

    override fun getStoredBeers(): Single<List<Beer>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBeer(): Single<Beer> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}