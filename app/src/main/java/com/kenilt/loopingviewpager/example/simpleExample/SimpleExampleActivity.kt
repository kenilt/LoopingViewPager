package com.kenilt.loopingviewpager.example.simpleExample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kenilt.loopingviewpager.example.R
import com.kenilt.loopingviewpager.example.TextPagerAdapter
import com.kenilt.loopingviewpager.example.model.DataGenerator
import kotlinx.android.synthetic.main.activity_simple_example.*

class SimpleExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_example)
        setTitle(R.string.simple_example)

        vpPager.adapter = TextPagerAdapter(this, DataGenerator.generateList())
        indicator.setViewPager(vpPager)
    }
}
