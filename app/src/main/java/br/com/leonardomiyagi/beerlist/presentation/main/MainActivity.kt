package br.com.leonardomiyagi.beerlist.presentation.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import br.com.leonardomiyagi.beerlist.R
import br.com.leonardomiyagi.beerlist.databinding.ActivityMainBinding
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.presentation.base.BaseActivity
import br.com.leonardomiyagi.beerlist.presentation.main.adapter.BeerAdapter
import br.com.leonardomiyagi.beerlist.presentation.utils.Navigator
import br.com.leonardomiyagi.beerlist.presentation.utils.PlaceholderData
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View, BeerAdapter.OnClickListener {

    @Inject
    lateinit var presenter: MainContract.Presenter

    private lateinit var adapter: BeerAdapter

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
        adapter.setBeers(beers)
    }

    override fun showLoading() {
        binding.placeholders?.data = PlaceholderData.loading(this)
    }

    override fun showEmptyList() {
        binding.placeholders?.data = PlaceholderData.empty(this)
    }

    override fun showFetchError(placeholderData: PlaceholderData) {
        binding.placeholders?.data = placeholderData
    }

    override fun hidePlaceholders() {
        binding.placeholders?.data = PlaceholderData.hide()
    }

    override fun goToBeerDetails(beer: Beer) {
        Navigator.goToBeerDetails(this, beer)
    }

    override fun onClick(beer: Beer) {
        presenter.onBeerClicked(beer)
    }

    private fun setupRecyclerView() {
        adapter = BeerAdapter(this)
        binding.beersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.beersRecyclerView.adapter = adapter
        binding.beersRecyclerView.addItemDecoration(DividerItemDecoration(this,
                (binding.beersRecyclerView.layoutManager as LinearLayoutManager).orientation))
    }
}
