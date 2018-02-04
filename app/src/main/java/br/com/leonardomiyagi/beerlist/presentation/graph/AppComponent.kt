package br.com.leonardomiyagi.beerlist.presentation.graph

import android.app.Application
import br.com.leonardomiyagi.beerlist.presentation.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by lmiyagi on 11/8/17.
 */
@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class),
    (BindingModule::class),
    (ApiModule::class),
    (MappersModule::class),
    (AppModule::class)])
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}