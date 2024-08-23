package com.kenilt.loopingviewpager.example.showHideFragmentExample

import android.os.Bundle
import android.widget.Button
import com.kenilt.loopingviewpager.example.BaseExampleActivity
import com.kenilt.loopingviewpager.example.R

class ShowHideFragmentActivity : BaseExampleActivity() {

    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_hide_fragment)

        button = findViewById(R.id.button)

        val simpleExampleFragment = SimpleExampleFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.flFrame, simpleExampleFragment)
            .commit()

        button.setOnClickListener {
            if (simpleExampleFragment.isHidden) {
                button.text = "Hide"
                supportFragmentManager.beginTransaction().show(simpleExampleFragment).commit()
            } else {
                button.text = "Show"
                supportFragmentManager.beginTransaction().hide(simpleExampleFragment).commit()
            }
        }
    }

    override fun getTitleId(): Int {
        return R.string.show_hide_fragment_example
    }
}
