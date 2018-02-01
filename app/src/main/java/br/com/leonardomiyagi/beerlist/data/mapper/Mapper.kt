package br.com.leonardomiyagi.beerlist.data.mapper

/**
 * Created by lmiyagi on 2/1/18.
 */
abstract class Mapper<T, U> {

    abstract fun map(t: T): U

    fun mapCollection(tList: List<T>?): List<U> {
        val uList = ArrayList<U>()
        if (tList == null) return uList
        tList.forEach { uList.add(map(it)) }
        return uList
    }
}