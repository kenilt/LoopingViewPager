package com.kenilt.loopingviewpager.widget

import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.kenilt.loopingviewpager.util.LoopingUtil

class InternalOnPageChangeListener(
    private val pageChangeListener: ViewPager.OnPageChangeListener,
    private val adapterGetter: () -> PagerAdapter?
) : ViewPager.OnPageChangeListener {

    override fun onPageScrollStateChanged(state: Int) {
        pageChangeListener.onPageScrollStateChanged(state)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        val originalAdapter = adapterGetter.invoke()
        val pagerPosition =
            LoopingUtil.getPagerPosition(
                originalAdapter,
                position
            )
        val lastPosition = originalAdapter?.let { it.count - 1 } ?: 0
        val isScrollToLooping = pagerPosition == lastPosition && positionOffset != 0f
        val positionLoop = if (isScrollToLooping) 0 else LoopingUtil.getPagerPosition(
            originalAdapter,
            position
        )
        val offset = if (isScrollToLooping) lastPosition * (1 - positionOffset) else positionOffset
        pageChangeListener.onPageScrolled(positionLoop, offset, positionOffsetPixels)
    }

    override fun onPageSelected(position: Int) {
        pageChangeListener.onPageSelected(
            LoopingUtil.getPagerPosition(
                adapterGetter.invoke(),
                position
            )
        )
    }
}
