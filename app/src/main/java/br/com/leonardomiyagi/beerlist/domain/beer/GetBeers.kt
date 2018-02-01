package br.com.leonardomiyagi.beerlist.domain.beer

import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.domain.repository.BeerRepository

/**
 * Created by lmiyagi on 2/1/18.
 */
class GetBeers(private val beerRepository: BeerRepository) {

    fun execute(): List<Beer> {
        return beerRepository.getBeers()
    }
}