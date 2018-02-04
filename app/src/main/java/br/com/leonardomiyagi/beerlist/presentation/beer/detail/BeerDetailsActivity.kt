package br.com.leonardomiyagi.beerlist.presentation.beer.detail

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_beer_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.favorite_menu_item -> {
                presenter.onFavoriteBeerClicked()
                item.isChecked = !item.isChecked
                item.setIcon(if (item.isChecked) R.drawable.ic_favorite else R.drawable.ic_star_border)
                true
            }
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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

    override fun showStoreBeerSuccess() {
        Toast.makeText(this, R.string.beer_details_store_beer_success_message, Toast.LENGTH_SHORT).show()
    }
}
