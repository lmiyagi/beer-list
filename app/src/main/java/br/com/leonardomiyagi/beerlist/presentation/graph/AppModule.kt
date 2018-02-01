package br.com.leonardomiyagi.beerlist.presentation.graph

import br.com.leonardomiyagi.beerlist.domain.provider.SchedulerProvider
import br.com.leonardomiyagi.beerlist.presentation.utils.DefaultSchedulerProvider
import dagger.Binds
import dagger.Module

/**
 * Created by lmiyagi on 11/8/17.
 */
@Module
abstract class AppModule {

    @Binds
    @ActivityScoped
    abstract fun bindSchedulerProvider(defaultSchedulerProvider: DefaultSchedulerProvider): SchedulerProvider
}