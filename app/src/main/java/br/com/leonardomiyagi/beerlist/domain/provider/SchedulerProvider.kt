package br.com.leonardomiyagi.beerlist.domain.provider

import io.reactivex.Scheduler

/**
 * Created by lmiyagi on 01/02/18.
 */
interface SchedulerProvider {

    public fun io(): Scheduler
    public fun main(): Scheduler
}