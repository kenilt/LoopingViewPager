package com.kenilt.loopingviewpager.example.showHideFragmentExample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.kenilt.loopingviewpager.example.R
import com.kenilt.loopingviewpager.example.model.DataGenerator
import com.kenilt.loopingviewpager.example.simpleExample.ExamplePagerAdapter
import com.kenilt.loopingviewpager.scroller.AutoScroller
import com.kenilt.loopingviewpager.scroller.ScrollerCycle
import kotlinx.android.synthetic.main.activity_simple_example.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [SimpleExampleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SimpleExampleFragment : Fragment() {

    private val scrollerCycle = ScrollerCycle()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.activity_simple_example, container, false)

        layout.vpPager.adapter = ExamplePagerAdapter(
            requireContext(),
            DataGenerator.generateList()
        )
        layout.indicator.setViewPager(layout.vpPager)

        layout.btnPrevious.setOnClickListener { layout.vpPager.setCurrentItem(layout.vpPager.currentItem - 1, true) }
        layout.btnNext.setOnClickListener { layout.vpPager.setCurrentItem(layout.vpPager.currentItem + 1, true) }

        layout.seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val count = progress + 1
                layout.txtItemCount.text = "Item count:   $count"
                layout.vpPager.adapter = ExamplePagerAdapter(
                    requireContext(),
                    DataGenerator.generateList(count)
                )
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // auto scroll
        val autoScroller = AutoScroller(layout.vpPager, lifecycle, 2000)
        layout.swAutoScroll.setOnCheckedChangeListener { _, isChecked ->
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
