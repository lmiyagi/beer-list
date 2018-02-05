package br.com.leonardomiyagi.beerlist.presentation.beer.detail

import br.com.leonardomiyagi.beerlist.domain.model.Beer
import java.io.File

/**
 * Created by lmiyagi on 02/02/18.
 */
interface BeerDetailsContract {

    interface View {
        fun showGetBeerError()
        fun renderBeer(beer: Beer)
        fun showStoreBeerSuccess()
        fun showStoreBeerError()
        fun handleBeerImage(path: String)
        fun showDeleteBeerSuccess()
        fun showDeleteBeerError()
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun onFavoriteBeerClicked()
        fun onUnfavoriteBeerClicked()
        fun onImageProcessed(imageFile: File?)
    }
}