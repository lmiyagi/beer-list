package br.com.leonardomiyagi.beerlist.presentation.beer.detail

import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.presentation.graph.ActivityScoped
import br.com.leonardomiyagi.beerlist.presentation.utils.AppConstants
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.android.AndroidInjector
import javax.inject.Named

/**
 * Created by lmiyagi on 02/02/18.
 */
@ActivityScoped
@Subcomponent(modules = [(BeerDetailsComponent.BeerDetailsModule::class)])
interface BeerDetailsComponent : AndroidInjector<BeerDetailsActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<BeerDetailsActivity>()

    @Module
    abstract class BeerDetailsModule {

        @Binds
        @ActivityScoped
        abstract fun bindPresenter(presenter: BeerDetailsPresenter): BeerDetailsContract.Presenter

        @Module
        companion object {

            @Provides
            @ActivityScoped
            @JvmStatic
            @Named(AppConstants.EXTRA_BEER)
            fun provideBeer(activity: BeerDetailsActivity): Beer {
                return activity.intent.extras.getSerializable(AppConstants.EXTRA_BEER) as Beer
            }
        }
    }
}