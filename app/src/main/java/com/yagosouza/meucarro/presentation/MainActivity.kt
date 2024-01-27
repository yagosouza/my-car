package com.yagosouza.meucarro.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yagosouza.meucarro.R
import com.yagosouza.meucarro.presentation.home.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commitNow()
        }
    }
}