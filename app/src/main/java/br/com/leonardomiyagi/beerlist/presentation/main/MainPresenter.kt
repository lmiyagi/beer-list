package br.com.leonardomiyagi.beerlist.presentation.main

import br.com.leonardomiyagi.beerlist.domain.beer.GetBeers
import br.com.leonardomiyagi.beerlist.domain.provider.SchedulerProvider
import br.com.leonardomiyagi.beerlist.presentation.utils.ErrorHandler
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by lmiyagi on 11/8/17.
 */
class MainPresenter @Inject constructor(private val getBeers: GetBeers,
                                        private val schedulers: SchedulerProvider,
                                        private val errorHandler: ErrorHandler) : MainContract.Presenter {

    private var view: MainContract.View? = null
    private var getBeersDisposable: Disposable? = null

    override fun attachView(view: MainContract.View) {
        this.view = view
        fetchBeers()
    }

    override fun detachView() {
        this.view = null
        getBeersDisposable?.dispose()
    }

    private fun fetchBeers() {
        getBeersDisposable = getBeers.execute()
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
    }
}