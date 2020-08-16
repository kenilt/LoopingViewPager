package com.kenilt.loopingviewpager.widget

import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.kenilt.loopingviewpager.util.LoopingUtil

internal class InternalOnPageChangeListener(
    private val pageChangeListener: ViewPager.OnPageChangeListener,
    private val adapterGetter: () -> PagerAdapter?
) : ViewPager.OnPageChangeListener {

    private var lastOuterIndex = -1

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
        val originalAdapter = adapterGetter.invoke() ?: return
        val pagerPosition = LoopingUtil.getPagerPosition(originalAdapter, position)

        when {
            // if the index is the first or last item, fire the event and assign flag
            // originalAdapter.count + 1 means the last item of internal adapter
            position == 0 || position == originalAdapter.count + 1 -> {
                pageChangeListener.onPageSelected(pagerPosition)
                lastOuterIndex = pagerPosition
            }
            // if the pager position is duplicate with the previous outer, it means this position
            // was selected in the last time, so no need to fire event again, just clear the flag
            pagerPosition == lastOuterIndex -> {
                lastOuterIndex = -1
            }
            // for normal case, fire the event and clear the flag
            else -> {
                pageChangeListener.onPageSelected(pagerPosition)
                lastOuterIndex = -1
            }
        }
    }
}
