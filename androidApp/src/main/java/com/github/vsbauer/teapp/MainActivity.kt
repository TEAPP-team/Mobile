package com.github.vsbauer.teapp

import TeappApi
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api = TeappApi()
        CoroutineScope(Dispatchers.IO).launch {
            val res = api.allTeaHouses()
            println("${res[0]}")
        }
    }
}