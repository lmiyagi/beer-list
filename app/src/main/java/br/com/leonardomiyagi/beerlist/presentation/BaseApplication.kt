package br.com.leonardomiyagi.beerlist.presentation

import br.com.leonardomiyagi.beerlist.presentation.graph.DaggerAppComponent
import br.com.leonardomiyagi.beerlist.presentation.utils.AppConstants
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.realm.Realm
import io.realm.RealmConfiguration


/**
 * Created by lmiyagi on 11/8/17.
 */
class BaseApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        setupRealm()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
                .application(this)
                .build()
    }

    private fun setupRealm() {
        Realm.init(this)
        val configuration = RealmConfiguration.Builder()
                .schemaVersion(AppConstants.DATABASE_SCHEMA_VERSION)
                .build()
        Realm.setDefaultConfiguration(configuration)
        Realm.compactRealm(configuration)
    }
}