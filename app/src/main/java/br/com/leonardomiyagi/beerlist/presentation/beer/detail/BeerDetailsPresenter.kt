package br.com.leonardomiyagi.beerlist.presentation.beer.detail

import br.com.leonardomiyagi.beerlist.domain.beer.StoreBeer
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.domain.provider.SchedulerProvider
import br.com.leonardomiyagi.beerlist.presentation.utils.AppConstants
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by lmiyagi on 02/02/18.
 */
class BeerDetailsPresenter @Inject constructor(@Named(AppConstants.EXTRA_BEER) private val beer: Beer,
                                               private val storeBeer: StoreBeer,
                                               private val schedulerProvider: SchedulerProvider) : BeerDetailsContract.Presenter {

    private var storeBeerDisposable: Disposable? = null

    private var view: BeerDetailsContract.View? = null

    override fun attachView(view: BeerDetailsContract.View) {
        this.view = view
        view.renderBeer(beer)
    }

    override fun detachView() {
        this.view = null
        storeBeerDisposable?.dispose()
    }

    override fun onFavoriteBeerClicked() {
        storeBeer()
    }

    private fun storeBeer() {
        storeBeerDisposable = storeBeer.execute(beer)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.main())
                .subscribe({ view?.showStoreBeerSuccess() },
                        {
                            it.printStackTrace()
                            view?.showStoreBeerError()
                        })
    }
}