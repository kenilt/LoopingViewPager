package com.kenilt.loopingviewpager.widget

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.kenilt.loopingviewpager.util.LoopingUtil

internal class InternalLoopingAdapter(private val pagerAdapter: PagerAdapter): PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return pagerAdapter.isViewFromObject(view, `object`)
    }

    override fun getCount(): Int {
        val itemsSize = pagerAdapter.count
        return if (itemsSize > 1) itemsSize + 2 else itemsSize
    }

    override fun getItemPosition(`object`: Any): Int {
        return pagerAdapter.getItemPosition(`object`)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return pagerAdapter.instantiateItem(container,
            LoopingUtil.getPagerPosition(
                pagerAdapter,
                position
            )
        )
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pagerAdapter.getPageTitle(position)
    }

    override fun getPageWidth(position: Int): Float {
        return pagerAdapter.getPageWidth(position)
    }
}
