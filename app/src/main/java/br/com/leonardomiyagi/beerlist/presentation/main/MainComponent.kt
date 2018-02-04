package br.com.leonardomiyagi.beerlist.presentation.main

import br.com.leonardomiyagi.beerlist.presentation.beer.BeerModule
import br.com.leonardomiyagi.beerlist.presentation.graph.ActivityScoped
import br.com.leonardomiyagi.beerlist.presentation.graph.MappersModule
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by lmiyagi on 11/8/17.
 */
@ActivityScoped
@Subcomponent(modules = [(MainComponent.MainModule::class), (BeerModule::class)])
interface MainComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()

    @Module
    abstract class MainModule {

        @Binds
        @ActivityScoped
        abstract fun bindPresenter(presenter: MainPresenter): MainContract.Presenter
    }
}