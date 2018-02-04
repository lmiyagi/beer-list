package br.com.leonardomiyagi.beerlist.domain.beer

import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.domain.repository.BeerRepository
import io.reactivex.Completable

/**
 * Created by lmiyagi on 2/4/18.
 */
class DeleteBeer(private val beerRepository: BeerRepository) {

    fun execute(beer: Beer): Completable {
        return beerRepository.deleteBeer(beer)
    }
}