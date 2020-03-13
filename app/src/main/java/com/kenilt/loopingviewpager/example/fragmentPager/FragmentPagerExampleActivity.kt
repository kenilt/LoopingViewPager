package com.kenilt.loopingviewpager.example.fragmentPager

import android.os.Bundle
import com.kenilt.loopingviewpager.example.BaseExampleActivity
import com.kenilt.loopingviewpager.example.R
import com.kenilt.loopingviewpager.example.model.DataGenerator
import kotlinx.android.synthetic.main.activity_fragment_pager_example.*

class FragmentPagerExampleActivity : BaseExampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_pager_example)

        vpPager.adapter =
            ExampleFragmentPagerAdapter(
                supportFragmentManager,
                DataGenerator.generateList(4, "Fragment Page")
            )
        indicator.setViewPager(vpPager)
    }

    override fun getTitleId(): Int {
        return R.string.fragment_pager_example
    }
}
