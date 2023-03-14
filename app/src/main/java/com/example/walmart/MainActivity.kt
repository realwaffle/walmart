package com.example.walmart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.walmart.ui.CountriesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CountriesFragment(), CountriesFragment.TAG)
                .commit()
        }
    }
}