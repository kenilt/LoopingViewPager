package com.kenilt.loopingviewpager.scroller

import android.annotation.SuppressLint
import android.os.Handler
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.SCROLL_STATE_DRAGGING

/**
 * Created by Kenilt on 3/11/20.
 */
@SuppressLint("ClickableViewAccessibility")
class AutoScroller(val viewPager: ViewPager, lifecycle: Lifecycle? = null, scrollInterval: Long = 5000) : LifecycleObserver, ScrollerObserver {

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
    var isStopAutoScrollWhileDragging = true
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
    var scrollerCycle: ScrollerCycle? = null
        set(value) {
            field = value
            value?.removeObserver(this)
            value?.addObserver(this)
        }

    init {
        viewPager.addOnPageChangeListener(object: ViewPager.SimpleOnPageChangeListener() {
            var isStopByDrag = false

            override fun onPageSelected(position: Int) {
                currentPagePosition = position
                resumeAutoScrollWhenNeeded()
            }

            override fun onPageScrollStateChanged(state: Int) {
                if (isStopAutoScrollWhileDragging) {
                    if (state == SCROLL_STATE_DRAGGING && !isStopByDrag) {
                        removeAutoScrollCallback()
                        isStopByDrag = true
                    } else if (state != SCROLL_STATE_DRAGGING && isStopByDrag) {
                        resumeAutoScrollWhenNeeded()
                        isStopByDrag = false
                    }
                }
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

    override fun changeAutoScroll(isAutoScroll: Boolean) {
        if (isAutoScroll) {
            resumeAutoScrollWhenNeeded()
        } else {
            removeAutoScrollCallback()
        }
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
