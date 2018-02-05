package br.com.leonardomiyagi.beerlist.presentation.main

import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.presentation.utils.PlaceholderData

/**
 * Created by lmiyagi on 11/8/17.
 */
interface MainContract {

    interface View {
        fun renderBeers(beers: List<Beer>)
        fun showLoading()
        fun showEmptyList()
        fun showFetchError(placeholderData: PlaceholderData)
        fun hidePlaceholders()
        fun goToBeerDetails(beer: Beer)
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun onBeerClicked(beer: Beer)
        fun onShowAllBeersClicked()
        fun onShowFavoriteBeersClicked()
    }
}