package com.kenilt.loopingviewpager.example

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
                R.string.complete_example,
                null,
                SimpleExampleActivity::class.java
            ),
            ExampleActivityDetails(R.string.web_ui_example, null, SimpleExampleActivity::class.java),
            ExampleActivityDetails(R.string.custom_ui_example, null, SimpleExampleActivity::class.java)
        )
        intent.putExtra(Constants.EXAMPLES.name, examples)
        startActivity(intent)
        finish()
    }
}
