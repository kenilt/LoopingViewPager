package com.kenilt.loopingviewpager.scroller

import android.os.Handler
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewpager.widget.ViewPager

/**
 * Created by Kenilt on 3/11/20.
 */
class AutoScroller(val viewPager: ViewPager, lifecycle: Lifecycle? = null, scrollInterval: Long = 5000) : LifecycleObserver {

    var scrollInterval: Long = scrollInterval
        set(value) {
            field = value
            resumeAutoScrollWhenNeeded()
        }
    var isAutoScroll = true
        set(value) {
            field = value
            if (value) {
                startDelayAutoScroll()
            } else {
                removeAutoScrollCallback()
            }
        }
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
                resumeAutoScrollWhenNeeded()
            }
        })
        viewPager.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener{
            override fun onViewDetachedFromWindow(v: View?) {
                removeAutoScrollCallback()
            }

            override fun onViewAttachedToWindow(v: View?) {
                resumeAutoScrollWhenNeeded()
            }
        })
        lifecycle?.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        resumeAutoScrollWhenNeeded()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun onPause() {
        removeAutoScrollCallback()
    }

    private fun resumeAutoScrollWhenNeeded() {
        if (isAutoScroll) {
            startDelayAutoScroll()
        }
    }

    private fun startDelayAutoScroll() {
        removeAutoScrollCallback()
        autoScrollHandler.postDelayed(autoScrollRunnable, scrollInterval)
    }

    private fun removeAutoScrollCallback() {
        autoScrollHandler.removeCallbacks(autoScrollRunnable)
    }
}
