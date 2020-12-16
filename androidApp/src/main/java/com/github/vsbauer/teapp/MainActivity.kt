package com.github.vsbauer.teapp


import TeappApi
import android.opengl.Visibility
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.add
import androidx.fragment.app.commit
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class   MainActivity : AppCompatActivity(R.layout.activity_main){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        val api = TeappApi()
        CoroutineScope(Dispatchers.IO).launch {
            val res = api.allTeaHouses()
            println("${res[0]}")
            Log.d("checkforres", res[0].toString())
            runOnUiThread{
                lottie_container.visibility = View.GONE
            }
        }

    }
}

