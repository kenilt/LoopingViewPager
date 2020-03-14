package com.kenilt.loopingviewpager.example.indicatorExample

import android.os.Bundle
import com.kenilt.loopingviewpager.example.BaseExampleActivity
import com.kenilt.loopingviewpager.example.R
import com.kenilt.loopingviewpager.example.model.DataGenerator
import com.kenilt.loopingviewpager.example.simpleExample.ExamplePagerAdapter
import kotlinx.android.synthetic.main.activity_indicator_example.*

class IndicatorExampleActivity : BaseExampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indicator_example)

        vpPager.adapter = ExamplePagerAdapter(
            this,
            DataGenerator.generateList()
        )
        viewpagertab.setViewPager(vpPager)
        circle_indicator.setViewPager(vpPager)
        dots_indicator.setViewPager(vpPager)
        pageIndicatorView.setViewPager(vpPager)
    }

    override fun getTitleId(): Int {
        return R.string.indicator_example
    }
}
