package com.kenilt.loopingviewpager.example.fragmentStatePager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kenilt.loopingviewpager.example.BaseExampleActivity
import com.kenilt.loopingviewpager.example.R
import com.kenilt.loopingviewpager.example.model.DataGenerator
import kotlinx.android.synthetic.main.activity_fragment_state_pager_example.*

class FragmentStatePagerExampleActivity : BaseExampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_state_pager_example)

        vpPager.setAdapter(
            ExampleFragmentStatePagerAdapter(
                supportFragmentManager,
                DataGenerator.generateList(4, "Fragment Page")
            ), supportFragmentManager
        )
        indicator.setViewPager(vpPager)
    }

    override fun getTitleId(): Int {
        return R.string.fragment_state_pager_example
    }
}
