package com.kenilt.loopingviewpager.example.showHideFragmentExample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.kenilt.circleindicator.CirclePageIndicator
import com.kenilt.loopingviewpager.example.R
import com.kenilt.loopingviewpager.example.model.DataGenerator
import com.kenilt.loopingviewpager.example.simpleExample.ExamplePagerAdapter
import com.kenilt.loopingviewpager.scroller.AutoScroller
import com.kenilt.loopingviewpager.scroller.ScrollerCycle
import com.kenilt.loopingviewpager.widget.LoopingViewPager

/**
 * A simple [Fragment] subclass.
 * Use the [SimpleExampleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SimpleExampleFragment : Fragment() {

    private lateinit var vpPager: LoopingViewPager
    private lateinit var indicator: CirclePageIndicator
    private lateinit var swAutoScroll: Switch
    private lateinit var btnPrevious: Button
    private lateinit var btnNext: Button
    private lateinit var seekBar: SeekBar
    private lateinit var txtItemCount: TextView

    private val scrollerCycle = ScrollerCycle()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.activity_simple_example, container, false)

        vpPager = layout.findViewById(R.id.vpPager)
        indicator = layout.findViewById(R.id.indicator)
        swAutoScroll = layout.findViewById(R.id.swAutoScroll)
        btnPrevious = layout.findViewById(R.id.btnPrevious)
        btnNext = layout.findViewById(R.id.btnNext)
        seekBar = layout.findViewById(R.id.seekBar)
        txtItemCount = layout.findViewById(R.id.txtItemCount)

        vpPager.adapter = ExamplePagerAdapter(
            requireContext(),
            DataGenerator.generateList()
        )
        indicator.setViewPager(vpPager)

        btnPrevious.setOnClickListener { vpPager.setCurrentItem(vpPager.currentItem - 1, true) }
        btnNext.setOnClickListener { vpPager.setCurrentItem(vpPager.currentItem + 1, true) }

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val count = progress + 1
                txtItemCount.text = "Item count:   $count"
                vpPager.adapter = ExamplePagerAdapter(
                    requireContext(),
                    DataGenerator.generateList(count)
                )
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // auto scroll
        val autoScroller = AutoScroller(vpPager, lifecycle, 2000)
        swAutoScroll.setOnCheckedChangeListener { _, isChecked ->
            autoScroller.isAutoScroll = isChecked
        }
        autoScroller.scrollerCycle = scrollerCycle

        return layout
    }

    override fun onHiddenChanged(hidden: Boolean) {
        scrollerCycle.onChangeAutoScroll(!hidden)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SimpleExampleFragment()
    }
}
