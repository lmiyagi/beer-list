package br.com.leonardomiyagi.beerlist.presentation.utils

import android.content.Context
import android.content.Intent
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.presentation.beer.detail.BeerDetailsActivity
import br.com.leonardomiyagi.beerlist.presentation.main.MainActivity

/**
 * Created by lmiyagi on 02/02/18.
 */
class Navigator {
    companion object {
        fun goToBeerDetails(context: Context, beer: Beer) {
            val intent = Intent(context, BeerDetailsActivity::class.java)
            intent.putExtra(AppConstants.EXTRA_BEER, beer)
            context.startActivity(intent)
        }

        fun goToMain(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}