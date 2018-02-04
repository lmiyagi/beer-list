package br.com.leonardomiyagi.beerlist.presentation.beer.detail

import br.com.leonardomiyagi.beerlist.domain.model.Beer

/**
 * Created by lmiyagi on 02/02/18.
 */
interface BeerDetailsContract {

    interface View {
        fun renderBeer(beer: Beer)
        fun showStoreBeerSuccess()
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun onFavoriteBeerClicked()
    }
}