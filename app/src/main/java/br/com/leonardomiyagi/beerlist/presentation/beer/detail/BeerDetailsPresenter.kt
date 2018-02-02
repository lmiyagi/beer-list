package br.com.leonardomiyagi.beerlist.presentation.beer.detail

import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.presentation.utils.AppConstants
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by lmiyagi on 02/02/18.
 */
class BeerDetailsPresenter @Inject constructor(@Named(AppConstants.EXTRA_BEER) private val beer: Beer) : BeerDetailsContract.Presenter {

    private var view: BeerDetailsContract.View? = null

    override fun attachView(view: BeerDetailsContract.View) {
        this.view = view
        view.renderBeer(beer)
    }

    override fun detachView() {
        this.view = null
    }
}