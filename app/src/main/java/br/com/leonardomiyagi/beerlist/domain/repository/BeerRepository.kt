package br.com.leonardomiyagi.beerlist.domain.repository

import br.com.leonardomiyagi.beerlist.domain.model.Beer

/**
 * Created by lmiyagi on 2/1/18.
 */
interface BeerRepository {

    fun getBeers(): List<Beer>

    fun getBeer(): Beer
}