package com.kenilt.loopingviewpager.example.fragmentPager

import android.os.Bundle
import android.widget.SeekBar
import com.kenilt.loopingviewpager.example.BaseExampleActivity
import com.kenilt.loopingviewpager.example.R
import com.kenilt.loopingviewpager.example.model.DataGenerator
import com.kenilt.loopingviewpager.example.simpleExample.ExamplePagerAdapter
import com.kenilt.loopingviewpager.scroller.AutoScroller
import kotlinx.android.synthetic.main.activity_fragment_pager_example.*

class FragmentPagerExampleActivity : BaseExampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_pager_example)

        vpPager.setAdapter(
            ExampleFragmentPagerAdapter(
                supportFragmentManager,
                DataGenerator.generateList(4, "Fragment Page")
            ), supportFragmentManager
        )
        indicator.setViewPager(vpPager)

        btnPrevious.setOnClickListener { vpPager.setCurrentItem(vpPager.currentItem - 1, true) }
        btnNext.setOnClickListener { vpPager.setCurrentItem(vpPager.currentItem + 1, true) }

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val count = progress + 1
                txtItemCount.text = "Item count:   $count"
                vpPager.adapter = ExamplePagerAdapter(
                    this@FragmentPagerExampleActivity,
                    DataGenerator.generateList(count)
                )
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // auto scroll
        val autoScroller = AutoScroller(vpPager, lifecycle, 3000)
        swAutoScroll.setOnCheckedChangeListener { _, isChecked ->
            autoScroller.isAutoScroll = isChecked
        }
    }

    override fun getTitleId(): Int {
        return R.string.fragment_pager_example
    }
}
