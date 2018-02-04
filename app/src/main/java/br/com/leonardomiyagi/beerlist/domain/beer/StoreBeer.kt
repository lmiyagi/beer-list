package br.com.leonardomiyagi.beerlist.domain.beer

import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.domain.repository.BeerRepository
import io.reactivex.Completable

/**
 * Created by lmiyagi on 2/3/18.
 */
class StoreBeer(private val beerRepository: BeerRepository) {

    fun execute(beer: Beer): Completable {
        return beerRepository.storeBeer(beer)
    }
}