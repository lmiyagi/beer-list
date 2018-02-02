package br.com.leonardomiyagi.beerlist.presentation.splash

import android.os.Bundle
import br.com.leonardomiyagi.beerlist.R
import br.com.leonardomiyagi.beerlist.presentation.base.BaseActivity
import br.com.leonardomiyagi.beerlist.presentation.utils.Navigator
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashContract.View {

    @Inject
    lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
    }

    override fun goToMain() {
        Navigator.goToMain(this)
    }

    override fun closeView() {
        finish()
    }
}
