package com.kenilt.loopingviewpager.widget

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kenilt.loopingviewpager.util.LoopingUtil

internal class InternalFragmentPagerAdapter(private val pagerAdapter: FragmentPagerAdapter, fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return pagerAdapter.getItem(
            LoopingUtil.getPagerPosition(
                pagerAdapter,
                position
            )
        )
    }

    override fun getCount(): Int {
        val itemsSize = pagerAdapter.count
        return if (itemsSize > 1) itemsSize + 2 else itemsSize
    }

    override fun getItemPosition(`object`: Any): Int {
        return pagerAdapter.getItemPosition(`object`)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pagerAdapter.getPageTitle(position)
    }

    override fun getPageWidth(position: Int): Float {
        return pagerAdapter.getPageWidth(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}
