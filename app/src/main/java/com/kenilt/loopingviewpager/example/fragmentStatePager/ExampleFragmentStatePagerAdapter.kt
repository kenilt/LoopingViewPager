package com.kenilt.loopingviewpager.example.fragmentStatePager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.kenilt.loopingviewpager.example.fragmentPager.PageFragment
import com.kenilt.loopingviewpager.example.model.PageModel

/**
 * Created by thangnguyen on 3/13/20.
 */
class ExampleFragmentStatePagerAdapter(fragmentManager: FragmentManager, private var dataList: List<PageModel>): FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return PageFragment.newInstance(dataList[position])
    }

    override fun getCount(): Int {
        return dataList.size
    }
}
