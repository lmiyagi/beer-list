package br.com.leonardomiyagi.beerlist.presentation.main

/**
 * Created by lmiyagi on 11/8/17.
 */
interface MainContract {

    interface View {

    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
    }
}