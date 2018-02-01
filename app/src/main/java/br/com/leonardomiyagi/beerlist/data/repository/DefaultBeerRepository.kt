package br.com.leonardomiyagi.beerlist.data.repository

import br.com.leonardomiyagi.beerlist.data.api.ApiClient
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.domain.repository.BeerRepository

/**
 * Created by lmiyagi on 2/1/18.
 */
class DefaultBeerRepository(private val apiClient: ApiClient) : BeerRepository {

    override fun getBeers(): List<Beer> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBeer(): Beer {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}