package br.com.leonardomiyagi.beerlist.data.repository

import br.com.leonardomiyagi.beerlist.data.api.ApiClient
import br.com.leonardomiyagi.beerlist.data.api.model.ApiBeer
import br.com.leonardomiyagi.beerlist.data.local.model.RealmBeer
import br.com.leonardomiyagi.beerlist.data.local.utils.RealmNotFoundException
import br.com.leonardomiyagi.beerlist.data.mapper.Mapper
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.domain.repository.BeerRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.realm.Realm
import javax.inject.Inject

/**
 * Created by lmiyagi on 2/1/18.
 */
class DefaultBeerRepository @Inject constructor(private val apiClient: ApiClient,
                                                private val apiToDomainMapper: Mapper<ApiBeer, Beer>,
                                                private val realmToDomainMapper: Mapper<RealmBeer, Beer>,
                                                private val domainToRealmMapper: Mapper<Beer, RealmBeer>) : BeerRepository {

    override fun getBeers(): Single<List<Beer>> {
        return apiClient.getBeers().map(apiToDomainMapper::mapCollection)
    }

    override fun getStoredBeers(): Single<List<Beer>> {
        return Single.fromCallable<List<Beer>> {
            try {
                val realm = Realm.getDefaultInstance()
                val beers = ArrayList<RealmBeer>()
                beers.addAll(realm.where(RealmBeer::class.java).findAll())
                realmToDomainMapper.mapCollection(beers)
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
        }
    }

    override fun getBeer(beerId: Long): Single<Beer> {
        return Single.fromCallable<Beer> {
            try {
                val realm = Realm.getDefaultInstance()
                val realmBeer = realm.where(RealmBeer::class.java).equalTo(RealmBeer.ID_FIELD, beerId).findFirst()
                if (realmBeer == null) {
                    throw RealmNotFoundException()
                } else {
                    realmToDomainMapper.map(realmBeer)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
        }
    }

    override fun storeBeer(beer: Beer): Completable {
        return Completable.fromCallable {
            try {
                val realm = Realm.getDefaultInstance()
                realm.executeTransaction { it ->
                    it.insert(domainToRealmMapper.map(beer))
                }
                realm.close()
                Completable.complete()
            } catch (e: Exception) {
                e.printStackTrace()
                Completable.error(e)
            }
        }
    }

    override fun deleteBeer(beer: Beer): Completable {
        return Completable.fromCallable {
            try {
                val realm = Realm.getDefaultInstance()
                realm.executeTransaction { it ->
                    it.where(RealmBeer::class.java).equalTo(RealmBeer.ID_FIELD, beer.id).findFirst()?.deleteFromRealm()
                }
                realm.close()
                Completable.complete()
            } catch (e: Exception) {
                e.printStackTrace()
                Completable.error(e)
            }
        }
    }
}