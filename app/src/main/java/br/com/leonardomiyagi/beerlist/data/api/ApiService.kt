package br.com.leonardomiyagi.beerlist.data.api

import br.com.leonardomiyagi.beerlist.data.api.model.ApiBeer
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by lmiyagi on 11/8/17.
 */
interface ApiService {

    @GET("beers")
    fun getBeers(): Single<Response<List<ApiBeer>>>

    @GET
    fun getImage(@Url url: String): Single<Response<ResponseBody>>
}