package br.com.leonardomiyagi.beerlist.presentation.utils

import br.com.leonardomiyagi.beerlist.domain.provider.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by lmiyagi on 01/02/18.
 */
class DefaultSchedulerProvider : SchedulerProvider {

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun main(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}