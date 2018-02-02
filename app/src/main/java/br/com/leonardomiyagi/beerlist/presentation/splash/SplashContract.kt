package br.com.leonardomiyagi.beerlist.presentation.splash

/**
 * Created by lmiyagi on 2/2/18.
 */
interface SplashContract {

    interface View {
        fun goToMain()
        fun closeView()
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
    }
}