package com.kenilt.loopingviewpager.example.fragmentPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kenilt.loopingviewpager.example.model.PageModel

/**
 * Created by thangnguyen on 3/13/20.
 */
class ExampleFragmentPagerAdapter(fragmentManager: FragmentManager, private var dataList: List<PageModel>) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return PageFragment.newInstance(dataList[position])
    }

    override fun getCount(): Int {
        return dataList.size
    }
}
