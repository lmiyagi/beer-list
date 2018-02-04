package br.com.leonardomiyagi.beerlist.presentation.graph

import br.com.leonardomiyagi.beerlist.data.api.model.ApiBeer
import br.com.leonardomiyagi.beerlist.data.local.model.RealmBeer
import br.com.leonardomiyagi.beerlist.data.mapper.Mapper
import br.com.leonardomiyagi.beerlist.data.mapper.api2domain.ApiBeerToBeerMapper
import br.com.leonardomiyagi.beerlist.data.mapper.domain2realm.BeerToRealmBeerMapper
import br.com.leonardomiyagi.beerlist.data.mapper.realm2domain.RealmBeerToBeerMapper
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by lmiyagi on 01/02/18.
 */
@Module
abstract class MappersModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @Singleton
        fun bindApiBeerToBeerMapper(): Mapper<ApiBeer, Beer> {
            return ApiBeerToBeerMapper()
        }

        @Provides
        @JvmStatic
        @Singleton
        fun bindRealmBeerToBeerMapper(): Mapper<RealmBeer, Beer> {
            return RealmBeerToBeerMapper()
        }

        @Provides
        @JvmStatic
        @Singleton
        fun bindBeerToRealmBeerMapper(): Mapper<Beer, RealmBeer> {
            return BeerToRealmBeerMapper()
        }
    }
}