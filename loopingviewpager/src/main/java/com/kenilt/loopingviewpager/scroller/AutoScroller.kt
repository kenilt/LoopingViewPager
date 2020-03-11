package com.kenilt.loopingviewpager.scroller

import android.os.Handler
import androidx.viewpager.widget.ViewPager

/**
 * Created by Kenilt on 3/11/20.
 */
class AutoScroller(val viewPager: ViewPager, var scrollInterval: Long = 3000) {

    private var isAutoScroll = true
    private var isAutoScrollResumed = true
    private var currentPagePosition = 0
    private val autoScrollHandler: Handler = Handler()
    private val autoScrollRunnable: Runnable = object : Runnable {
        override fun run() {
            val pagerAdapter = viewPager.adapter
            if (!isAutoScroll || pagerAdapter == null || pagerAdapter.count < 2) return
            if (currentPagePosition >= pagerAdapter.count - 1) {
                currentPagePosition = 0
            } else {
                currentPagePosition++
            }
            viewPager.setCurrentItem(currentPagePosition, true)
        }
    }

    init {
        viewPager.addOnPageChangeListener(object: ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                currentPagePosition = position
                if (isAutoScrollResumed) {
                    autoScrollHandler.removeCallbacks(autoScrollRunnable)
                    autoScrollHandler.postDelayed(autoScrollRunnable, scrollInterval)
                }
            }
        })
    }

    fun resumeAutoScroll() {
        isAutoScrollResumed = true
        autoScrollHandler.removeCallbacks(autoScrollRunnable)
        autoScrollHandler.postDelayed(autoScrollRunnable, scrollInterval)
    }

    fun pauseAutoScroll() {
        isAutoScrollResumed = false
        autoScrollHandler.removeCallbacks(autoScrollRunnable)
    }
}
