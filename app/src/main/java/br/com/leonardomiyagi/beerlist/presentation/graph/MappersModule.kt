package br.com.leonardomiyagi.beerlist.presentation.graph

import br.com.leonardomiyagi.beerlist.data.api.model.ApiBeer
import br.com.leonardomiyagi.beerlist.data.mapper.Mapper
import br.com.leonardomiyagi.beerlist.data.mapper.api2domain.ApiBeerToBeerMapper
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import dagger.Binds
import dagger.Module

/**
 * Created by lmiyagi on 01/02/18.
 */
@Module
abstract class MappersModule {

    @Binds
    @ActivityScoped
    abstract fun bindApiBeerToBeerMapper(mapper: ApiBeerToBeerMapper): Mapper<ApiBeer, Beer>
}