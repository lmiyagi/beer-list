package br.com.leonardomiyagi.beerlist.presentation.beer.detail

import android.databinding.DataBindingUtil
import android.os.Bundle
import br.com.leonardomiyagi.beerlist.R
import br.com.leonardomiyagi.beerlist.databinding.ActivityBeerDetailsBinding
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.presentation.base.BaseActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import javax.inject.Inject

class BeerDetailsActivity : BaseActivity(), BeerDetailsContract.View {

    @Inject
    lateinit var presenter: BeerDetailsContract.Presenter

    private lateinit var binding: ActivityBeerDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_beer_details)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
    }

    override fun renderBeer(beer: Beer) {
        binding.beer = beer
        Glide.with(this)
                .load(beer.imageUrl)
                .apply(RequestOptions().fitCenter().placeholder(R.drawable.ic_beer_placeholder))
                .into(binding.beerImage)
    }
}
