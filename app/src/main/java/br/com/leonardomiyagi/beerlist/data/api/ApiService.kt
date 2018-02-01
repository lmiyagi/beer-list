package br.com.leonardomiyagi.beerlist.data.api

import br.com.leonardomiyagi.beerlist.data.api.model.ApiBeer
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by lmiyagi on 11/8/17.
 */
interface ApiService {

    @GET("/beers")
    fun getBeers(): Single<Response<List<ApiBeer>>>
}