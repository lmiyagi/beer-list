package br.com.leonardomiyagi.beerlist.presentation.graph

import android.app.Activity
import br.com.leonardomiyagi.beerlist.presentation.main.MainActivity
import br.com.leonardomiyagi.beerlist.presentation.main.MainComponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by lmiyagi on 11/8/17.
 */
@Module(subcomponents = arrayOf(MainComponent::class))
abstract class BindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindMainActivityInjectorFactory(builder: MainComponent.Builder): AndroidInjector.Factory<out Activity>
}