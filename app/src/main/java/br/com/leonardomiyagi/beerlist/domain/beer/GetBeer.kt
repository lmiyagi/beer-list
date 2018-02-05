package br.com.leonardomiyagi.beerlist.domain.beer

import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.domain.repository.BeerRepository
import io.reactivex.Single

/**
 * Created by lmiyagi on 2/4/18.
 */
class GetBeer(private val beerRepository: BeerRepository) {

    fun execute(beerId: Long): Single<Beer> {
        return beerRepository.getBeer(beerId)
    }
}