package br.com.leonardomiyagi.beerlist.presentation.beer

import br.com.leonardomiyagi.beerlist.data.repository.DefaultBeerRepository
import br.com.leonardomiyagi.beerlist.domain.beer.GetBeers
import br.com.leonardomiyagi.beerlist.domain.repository.BeerRepository
import br.com.leonardomiyagi.beerlist.presentation.graph.ActivityScoped
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by lmiyagi on 01/02/18.
 */
@Module
abstract class BeerModule {

    @Binds
    @ActivityScoped
    abstract fun bindBeerRepository(defaultBeerRepository: DefaultBeerRepository): BeerRepository

    @Module
    companion object {

        @Provides
        @JvmStatic
        @ActivityScoped
        fun provideGetBeers(beerRepository: BeerRepository): GetBeers {
            return GetBeers(beerRepository)
        }
    }
}