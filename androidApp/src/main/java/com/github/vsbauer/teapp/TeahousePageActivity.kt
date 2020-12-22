package com.github.vsbauer.teapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_teahouse_page.*

class TeahousePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teahouse_page)

        backArrowButton.setOnClickListener {
            super.onBackPressed()
        }

        intent.extras?.getString("TeahouseData")?.let {
            Log.d("TeahouseData", it)
        }
    }
}