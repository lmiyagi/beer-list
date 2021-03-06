package br.com.leonardomiyagi.beerlist.presentation.graph

import android.app.Activity
import br.com.leonardomiyagi.beerlist.presentation.beer.detail.BeerDetailsActivity
import br.com.leonardomiyagi.beerlist.presentation.beer.detail.BeerDetailsComponent
import br.com.leonardomiyagi.beerlist.presentation.main.MainActivity
import br.com.leonardomiyagi.beerlist.presentation.main.MainComponent
import br.com.leonardomiyagi.beerlist.presentation.splash.SplashActivity
import br.com.leonardomiyagi.beerlist.presentation.splash.SplashComponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by lmiyagi on 11/8/17.
 */
@Module(subcomponents = [(SplashComponent::class), (MainComponent::class), (BeerDetailsComponent::class)])
abstract class BindingModule {

    @Binds
    @IntoMap
    @ActivityKey(SplashActivity::class)
    abstract fun bindSplashActivityInjectorFactory(builder: SplashComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindMainActivityInjectorFactory(builder: MainComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(BeerDetailsActivity::class)
    abstract fun bindBeerDetailsActivityInjectorFactory(builder: BeerDetailsComponent.Builder): AndroidInjector.Factory<out Activity>
}