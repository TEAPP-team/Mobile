package com.github.vsbauer.teapp


import TeappApi
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Screen
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.yandex.mapkit.MapKitFactory
import data.TeaHouse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_teahouse_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.invoke.ConstantCallSite

lateinit var bottomsheet : BottomSheetBehavior<ConstraintLayout>
lateinit var resTeahouses : List<TeaHouse>

class   MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MapKitFactory.setApiKey("48d45a79-cbbd-4c13-9649-f3c003f692a6")

        bottomsheet = BottomSheetBehavior.from(bottomSheetTeahouse)
        bottomsheet.state = BottomSheetBehavior.STATE_COLLAPSED

        fetchTeahouses()

        setItemSelectedListener()


    }

    private fun setItemSelectedListener(){
        mainBottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val manager = supportFragmentManager
            when(item.itemId){

                R.id.menu_map -> {
                    manager.beginTransaction().replace(R.id.mainContainer, Screens.Map()).commit()
                    true
                }
                R.id.menu_list -> {
                    manager.beginTransaction().replace(R.id.mainContainer, Screens.Teahouses()).commit()
                    bottomsheet.state = BottomSheetBehavior.STATE_COLLAPSED
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

    private fun fetchTeahouses(){
        val api = TeappApi()
        CoroutineScope(Dispatchers.IO).launch {
            resTeahouses = api.allTeaHouses()
            runOnUiThread{
                supportFragmentManager.beginTransaction().replace(R.id.mainContainer, Screens.Map()).commit() // запускаем карту только после подгрузки, чтобы не произошел вылет при расстановке маркеров
                hideSplashscreen()
            }
        }
    }
    private fun hideSplashscreen(){
        val animationFadeContainerSplashscreen = splashscreenContainer.animate().alpha(0f).setDuration(1000)
        animationFadeContainerSplashscreen.start()
        animationFadeContainerSplashscreen.setUpdateListener {
            if (it.currentPlayTime >= it.duration){
                splashscreenContainer.visibility = View.GONE
            }
        }
    }

}

