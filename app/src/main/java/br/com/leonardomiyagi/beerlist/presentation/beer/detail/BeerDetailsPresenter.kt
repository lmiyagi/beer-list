package br.com.leonardomiyagi.beerlist.presentation.beer.detail

import br.com.leonardomiyagi.beerlist.domain.beer.DeleteBeer
import br.com.leonardomiyagi.beerlist.domain.beer.StoreBeer
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.domain.provider.SchedulerProvider
import br.com.leonardomiyagi.beerlist.domain.utils.ImageManager
import br.com.leonardomiyagi.beerlist.presentation.utils.AppConstants
import io.reactivex.disposables.CompositeDisposable
import java.io.File
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by lmiyagi on 02/02/18.
 */
class BeerDetailsPresenter @Inject constructor(@Named(AppConstants.EXTRA_BEER) private val beer: Beer,
                                               private val storeBeer: StoreBeer,
                                               private val deleteBeer: DeleteBeer,
                                               private val imageManager: ImageManager,
                                               private val schedulerProvider: SchedulerProvider) : BeerDetailsContract.Presenter {

    private var disposables: CompositeDisposable? = null

    private var view: BeerDetailsContract.View? = null

    override fun attachView(view: BeerDetailsContract.View) {
        this.view = view
        view.renderBeer(beer)
    }

    override fun detachView() {
        this.view = null
        disposables?.dispose()
    }

    override fun onFavoriteBeerClicked() {
        view?.handleBeerImage("${imageManager.getStorageDir()}/${beer.id ?: "unknown_beer"}.png")
    }

    override fun onUnfavoriteBeerClicked() {
        unfavoriteBeer()
    }

    override fun onImageProcessed(imageFile: File?) {
        storeBeer(imageFile)
    }

    private fun storeBeer(imageFile: File?) {
        beer.imageUrl = imageFile?.absolutePath
        val storeBeerDisposable = storeBeer.execute(beer)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.main())
                .subscribe({ view?.showStoreBeerSuccess() },
                        {
                            it.printStackTrace()
                            view?.showStoreBeerError()
                        })
        disposables?.add(storeBeerDisposable)
    }

    private fun unfavoriteBeer() {
        val deleteBeerDisposable = deleteBeer.execute(beer)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.main())
                .subscribe({ view?.showDeleteBeerSuccess() },
                        {
                            it.printStackTrace()
                            view?.showDeleteBeerError()
                        })
        disposables?.add(deleteBeerDisposable)
    }
}