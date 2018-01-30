package br.com.leonardomiyagi.beerlist.presentation.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import br.com.leonardomiyagi.beerlist.R
import br.com.leonardomiyagi.beerlist.databinding.ActivityMainBinding
import br.com.leonardomiyagi.beerlist.presentation.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
    }
}
