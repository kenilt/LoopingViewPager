package com.kenilt.loopingviewpager.example.fragmentStatePager

import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import com.kenilt.circleindicator.CirclePageIndicator
import com.kenilt.loopingviewpager.example.BaseExampleActivity
import com.kenilt.loopingviewpager.example.R
import com.kenilt.loopingviewpager.example.model.DataGenerator
import com.kenilt.loopingviewpager.example.simpleExample.ExamplePagerAdapter
import com.kenilt.loopingviewpager.scroller.AutoScroller
import com.kenilt.loopingviewpager.widget.LoopingViewPager

class FragmentStatePagerExampleActivity : BaseExampleActivity() {

    private lateinit var vpPager: LoopingViewPager
    private lateinit var indicator: CirclePageIndicator
    private lateinit var swAutoScroll: Switch
    private lateinit var btnPrevious: Button
    private lateinit var btnNext: Button
    private lateinit var seekBar: SeekBar
    private lateinit var txtItemCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_state_pager_example)

        vpPager = findViewById(R.id.vpPager)
        indicator = findViewById(R.id.indicator)
        swAutoScroll = findViewById(R.id.swAutoScroll)
        btnPrevious = findViewById(R.id.btnPrevious)
        btnNext = findViewById(R.id.btnNext)
        seekBar = findViewById(R.id.seekBar)
        txtItemCount = findViewById(R.id.txtItemCount)

        // View pager adapter and indicator
        vpPager.setAdapter(
            ExampleFragmentStatePagerAdapter(
                supportFragmentManager,
                DataGenerator.generateList(4, "Fragment Page")
            ), supportFragmentManager
        )
        indicator.setViewPager(vpPager)

        // Auto scroll
        val autoScroller = AutoScroller(vpPager, lifecycle, 3000)
        swAutoScroll.setOnCheckedChangeListener { _, isChecked ->
            autoScroller.isAutoScroll = isChecked
        }

        // Others
        initRelatedViews()
    }

    private fun initRelatedViews() {
        btnPrevious.setOnClickListener { vpPager.setCurrentItem(vpPager.currentItem - 1, true) }
        btnNext.setOnClickListener { vpPager.setCurrentItem(vpPager.currentItem + 1, true) }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val count = progress + 1
                txtItemCount.text = "Item count:   $count"
                vpPager.adapter = ExamplePagerAdapter(
                    this@FragmentStatePagerExampleActivity,
                    DataGenerator.generateList(count)
                )
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    override fun getTitleId(): Int {
        return R.string.fragment_state_pager_example
    }
}
