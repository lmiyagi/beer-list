package br.com.leonardomiyagi.beerlist.domain.beer

import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.domain.repository.BeerRepository
import io.reactivex.Single

/**
 * Created by lmiyagi on 2/1/18.
 */
class GetBeers(private val beerRepository: BeerRepository) {

    fun execute(): Single<List<Beer>> {
        return beerRepository.getBeers()
    }
}