package br.com.leonardomiyagi.beerlist.presentation.splash

import br.com.leonardomiyagi.beerlist.presentation.graph.ActivityScoped
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by lmiyagi on 2/2/18.
 */
@ActivityScoped
@Subcomponent(modules = [(SplashComponent.SplashModule::class)])
interface SplashComponent : AndroidInjector<SplashActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SplashActivity>()

    @Module
    abstract class SplashModule {

        @Binds
        @ActivityScoped
        abstract fun bindPresenter(presenter: SplashPresenter): SplashContract.Presenter
    }
}