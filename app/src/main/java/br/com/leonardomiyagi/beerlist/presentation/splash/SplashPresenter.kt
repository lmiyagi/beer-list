package br.com.leonardomiyagi.beerlist.presentation.splash

import javax.inject.Inject

/**
 * Created by lmiyagi on 2/2/18.
 */
class SplashPresenter @Inject constructor() : SplashContract.Presenter {

    private var view: SplashContract.View? = null

    override fun attachView(view: SplashContract.View) {
        this.view = view
        this.view?.goToMain()
        this.view?.closeView()
    }

    override fun detachView() {
        this.view = null
    }
}