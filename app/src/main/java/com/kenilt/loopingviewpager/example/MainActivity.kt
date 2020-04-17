package com.kenilt.loopingviewpager.example

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kenilt.loopingviewpager.example.fragmentPager.FragmentPagerExampleActivity
import com.kenilt.loopingviewpager.example.fragmentStatePager.FragmentStatePagerExampleActivity
import com.kenilt.loopingviewpager.example.indicatorExample.IndicatorExampleActivity
import com.kenilt.loopingviewpager.example.showHideFragmentExample.ShowHideFragmentActivity
import com.kenilt.loopingviewpager.example.simpleExample.SimpleExampleActivity
import com.psoffritti.librarysampleapptemplate.core.Constants
import com.psoffritti.librarysampleapptemplate.core.SampleAppTemplateActivity
import com.psoffritti.librarysampleapptemplate.core.utils.ExampleActivityDetails


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, SampleAppTemplateActivity::class.java)
        intent.putExtra(Constants.TITLE.name, getString(R.string.app_name))
        intent.putExtra(
            Constants.GITHUB_URL.name,
            "https://github.com/kenilt/LoopingViewPager"
        )
        intent.putExtra(
            Constants.HOMEPAGE_URL.name,
            "https://github.com/kenilt/LoopingViewPager/blob/master/README.md"
        )
        val examples = arrayOf(
            ExampleActivityDetails(
                R.string.simple_example,
                null,
                SimpleExampleActivity::class.java
            ),
            ExampleActivityDetails(
                R.string.fragment_pager_example,
                null,
                FragmentPagerExampleActivity::class.java
            ),
            ExampleActivityDetails(R.string.fragment_state_pager_example, null, FragmentStatePagerExampleActivity::class.java),
            ExampleActivityDetails(R.string.show_hide_fragment_example, null, ShowHideFragmentActivity::class.java),
            ExampleActivityDetails(R.string.indicator_example, null, IndicatorExampleActivity::class.java)
        )
        intent.putExtra(Constants.EXAMPLES.name, examples)
        startActivity(intent)
        finish()
    }
}
