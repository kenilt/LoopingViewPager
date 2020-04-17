package com.kenilt.loopingviewpager.scroller

/**
 * Created by thangnguyen on 4/17/20.
 */
class ScrollerCycle {

    private val observers: MutableList<ScrollerObserver> = ArrayList()

    fun addObserver(observer: ScrollerObserver) {
        observers.add(observer)
    }

    fun removeObserver(observer: ScrollerObserver) {
        observers.remove(observer)
    }

    fun onChangeAutoScroll(isAutoScroll: Boolean) {
        observers.forEach { it.changeAutoScroll(isAutoScroll) }
    }
}
