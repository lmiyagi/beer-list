package br.com.leonardomiyagi.beerlist.presentation.graph

import android.app.Application
import br.com.leonardomiyagi.beerlist.domain.provider.SchedulerProvider
import br.com.leonardomiyagi.beerlist.presentation.utils.DefaultSchedulerProvider
import br.com.leonardomiyagi.beerlist.presentation.utils.ErrorHandler
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by lmiyagi on 11/8/17.
 */
@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindSchedulerProvider(defaultSchedulerProvider: DefaultSchedulerProvider): SchedulerProvider

    @Module
    companion object {


        @Provides
        @JvmStatic
        @Singleton
        fun provideErrorHandler(application: Application): ErrorHandler {
            return ErrorHandler(application)
        }
    }
}