package br.com.leonardomiyagi.beerlist.presentation.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import br.com.leonardomiyagi.beerlist.R
import br.com.leonardomiyagi.beerlist.databinding.ActivityMainBinding
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.presentation.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
    }

    override fun renderBeers(beers: List<Beer>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEmptyList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showFetchError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setupRecyclerView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
