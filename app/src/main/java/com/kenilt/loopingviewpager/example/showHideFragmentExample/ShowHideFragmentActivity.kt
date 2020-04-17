package com.kenilt.loopingviewpager.example.showHideFragmentExample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kenilt.loopingviewpager.example.R
import kotlinx.android.synthetic.main.activity_show_hide_fragment.*

class ShowHideFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_hide_fragment)

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
}
