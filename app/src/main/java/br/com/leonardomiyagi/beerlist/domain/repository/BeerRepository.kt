package br.com.leonardomiyagi.beerlist.domain.repository

import br.com.leonardomiyagi.beerlist.domain.model.Beer
import io.reactivex.Single

/**
 * Created by lmiyagi on 2/1/18.
 */
interface BeerRepository {

    fun getBeers(): Single<List<Beer>>

    fun getBeer(): Single<Beer>
}