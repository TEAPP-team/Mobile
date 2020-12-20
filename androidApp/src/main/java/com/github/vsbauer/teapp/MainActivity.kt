package com.github.vsbauer.teapp


import TeappApi
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.yandex.mapkit.MapKitFactory
import data.TeaHouse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_teahouse_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

lateinit var bottomsheet: BottomSheetBehavior<ConstraintLayout>
lateinit var resTeahouses: List<TeaHouse>

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MapKitFactory.setApiKey("48d45a79-cbbd-4c13-9649-f3c003f692a6")

        bottomsheet = BottomSheetBehavior.from(bottomSheetTeahouse)
        bottomsheet.state = BottomSheetBehavior.STATE_COLLAPSED

        fetchTeahouses()

        setItemSelectedListener()


    }

    private fun setItemSelectedListener() {
        mainBottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val manager = supportFragmentManager
            when (item.itemId) {

                R.id.menu_map -> {
                    manager.beginTransaction().replace(R.id.mainContainer, Screens.MapScreen).commit()
                    true
                }
                R.id.menu_list -> {
                    manager.beginTransaction().replace(R.id.mainContainer, Screens.TeahouseListScreen)
                        .commit()
                    bottomsheet.state = BottomSheetBehavior.STATE_COLLAPSED
                    true
                }
                R.id.menu_saved -> {
                    manager.beginTransaction().replace(R.id.mainContainer, Screens.FavouriteScreen)
                        .commit()
                    true
                }
                else -> false
            }


        }
    }

    private fun fetchTeahouses() {
        val api = TeappApi()
        CoroutineScope(Dispatchers.IO).launch {
            resTeahouses = api.allTeaHouses()
            runOnUiThread {
                supportFragmentManager.beginTransaction().replace(R.id.mainContainer, Screens.MapScreen)
                    .commit() // запускаем карту только после подгрузки, чтобы не произошел вылет при расстановке маркеров
                hideSplashscreen()
            }
        }
    }

    private fun hideSplashscreen() {
        val animationFadeContainerSplashscreen =
            splashscreenContainer.animate().alpha(0f).setDuration(1000)
        animationFadeContainerSplashscreen.start()
        animationFadeContainerSplashscreen.setUpdateListener {
            if (it.currentPlayTime >= it.duration) {
                splashscreenContainer.visibility = View.GONE
            }
        }
    }
}

