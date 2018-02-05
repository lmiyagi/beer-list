package br.com.leonardomiyagi.beerlist.presentation.main

import br.com.leonardomiyagi.beerlist.domain.beer.GetBeers
import br.com.leonardomiyagi.beerlist.domain.beer.GetFavoriteBeers
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.domain.provider.SchedulerProvider
import br.com.leonardomiyagi.beerlist.presentation.utils.ErrorHandler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by lmiyagi on 11/8/17.
 */
class MainPresenter @Inject constructor(private val getBeers: GetBeers,
                                        private val getFavoriteBeers: GetFavoriteBeers,
                                        private val schedulers: SchedulerProvider,
                                        private val errorHandler: ErrorHandler) : MainContract.Presenter {

    private var view: MainContract.View? = null
    private var disposables: CompositeDisposable? = null

    override fun attachView(view: MainContract.View) {
        this.view = view
        fetchBeers()
    }

    override fun detachView() {
        this.view = null
        disposables?.dispose()
    }

    override fun onBeerClicked(beer: Beer) {
        view?.goToBeerDetails(beer)
    }

    override fun onShowAllBeersClicked() {
        fetchBeers()
    }

    override fun onShowFavoriteBeersClicked() {
        getFavoriteBeers()
    }

    private fun fetchBeers() {
        view?.showLoading()
        val getBeersDisposable = getBeers.execute()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .subscribe({
                    view?.hidePlaceholders()
                    if (it.isEmpty()) {
                        view?.showEmptyList()
                    } else {
                        view?.renderBeers(it)
                    }
                }, { view?.showFetchError(errorHandler.handleError(it, Runnable(this::fetchBeers))) })
        disposables?.add(getBeersDisposable)
    }

    private fun getFavoriteBeers() {
        view?.showLoading()
        val getFavoriteBeersDisposable = getFavoriteBeers.execute()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .subscribe({
                    view?.hidePlaceholders()
                    if (it.isEmpty()) {
                        view?.showEmptyList()
                    } else {
                        view?.renderBeers(it)
                    }
                }, { view?.showFetchError(errorHandler.handleError(it, Runnable(this::fetchBeers))) })
        disposables?.add(getFavoriteBeersDisposable)

    }
}