package com.github.vsbauer.teapp


import TeappApi
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Screen
import com.yandex.mapkit.MapKitFactory
import data.TeaHouse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class   MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MapKitFactory.setApiKey("48d45a79-cbbd-4c13-9649-f3c003f692a6")

        val api = TeappApi()
        CoroutineScope(Dispatchers.IO).launch {
            val res = api.allTeaHouses()
            Log.d("checkapi", res[1].coordinates.toString())
            println("${res[0]}")
        }

        mainBottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val manager = supportFragmentManager
            when(item.itemId){

                R.id.menu_map -> {
                    manager.beginTransaction().replace(R.id.mainContainer, Screens.Map()).commit()
                    true
                }
                R.id.menu_list -> {
                    manager.beginTransaction().replace(R.id.mainContainer, Screens.Teahouses()).commit()
                    true
                }
                R.id.menu_saved -> {
                    manager.beginTransaction().replace(R.id.mainContainer, Screens.Favourites()).commit()
                    true
                }
                else -> false
            }


        }

    }

}

