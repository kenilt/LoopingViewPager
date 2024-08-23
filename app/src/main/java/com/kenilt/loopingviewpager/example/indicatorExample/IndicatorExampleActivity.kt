package com.kenilt.loopingviewpager.example.indicatorExample

import android.os.Bundle
import com.kenilt.loopingviewpager.example.BaseExampleActivity
import com.kenilt.loopingviewpager.example.R
import com.kenilt.loopingviewpager.example.model.DataGenerator
import com.kenilt.loopingviewpager.example.simpleExample.ExamplePagerAdapter
import com.kenilt.loopingviewpager.widget.LoopingViewPager
import com.ogaclejapan.smarttablayout.SmartTabLayout
import com.rd.PageIndicatorView
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import me.relex.circleindicator.CircleIndicator

class IndicatorExampleActivity : BaseExampleActivity() {

    private lateinit var vpPager: LoopingViewPager
    private lateinit var viewpagerTab: SmartTabLayout
    private lateinit var circleIndicator: CircleIndicator
    private lateinit var dotsIndicator: DotsIndicator
    private lateinit var pageIndicatorView: PageIndicatorView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indicator_example)

        vpPager = findViewById(R.id.vpPager)
        viewpagerTab = findViewById(R.id.viewpagertab)
        circleIndicator = findViewById(R.id.circle_indicator)
        dotsIndicator = findViewById(R.id.dots_indicator)
        pageIndicatorView = findViewById(R.id.pageIndicatorView)

        vpPager.adapter = ExamplePagerAdapter(
            this,
            DataGenerator.generateList()
        )
        viewpagerTab.setViewPager(vpPager)
        circleIndicator.setViewPager(vpPager)
        dotsIndicator.setViewPager(vpPager)
        pageIndicatorView.setViewPager(vpPager)
    }

    override fun getTitleId(): Int {
        return R.string.indicator_example
    }
}
