package com.kenilt.loopingviewpager.example.simpleExample

import android.os.Bundle
import com.kenilt.loopingviewpager.example.BaseExampleActivity
import com.kenilt.loopingviewpager.example.R
import com.kenilt.loopingviewpager.example.model.DataGenerator
import kotlinx.android.synthetic.main.activity_simple_example.*

class SimpleExampleActivity : BaseExampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_example)

        vpPager.adapter =
            ExamplePagerAdapter(
                this,
                DataGenerator.generateList()
            )
        indicator.setViewPager(vpPager)
    }

    override fun getTitleId(): Int {
        return R.string.simple_example
    }
}
