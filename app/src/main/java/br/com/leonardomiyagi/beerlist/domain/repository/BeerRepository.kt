package br.com.leonardomiyagi.beerlist.domain.repository

import br.com.leonardomiyagi.beerlist.domain.model.Beer
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by lmiyagi on 2/1/18.
 */
interface BeerRepository {

    fun getBeers(): Single<List<Beer>>

    fun getStoredBeers(): Single<List<Beer>>

    fun getBeer(beerId: Long): Single<Beer>

    fun storeBeer(beer: Beer): Completable

    fun deleteBeer(beer: Beer): Completable
}