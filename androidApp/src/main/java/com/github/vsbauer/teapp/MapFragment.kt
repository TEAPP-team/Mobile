package com.github.vsbauer.teapp

import TeappApi
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.logo.Alignment
import com.yandex.mapkit.logo.HorizontalAlignment
import com.yandex.mapkit.logo.VerticalAlignment
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.runtime.Runtime.getApplicationContext
import com.yandex.runtime.image.ImageProvider
import data.TeaHouse
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


var camPos = CameraPosition(Point(59.93863, 30.31413), 11.0f, 0.0f, 0.0f) // изначальное положение карты на СПб
lateinit var res : List<TeaHouse>
class MapFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            MapKitFactory.initialize(context)
            mapview.map.apply {
                logo.setAlignment(Alignment(HorizontalAlignment.LEFT, VerticalAlignment.TOP))
            }

        val api = TeappApi()
        CoroutineScope(Dispatchers.IO).launch {

            res = api.allTeaHouses()

            res.forEach {

                activity?.runOnUiThread { // расставляем маркеры
                    val latitude = it.coordinates.latitude
                    val longitude = it.coordinates.longitude
                    val point = mapview.map.mapObjects.addPlacemark(Point(latitude, longitude), ImageProvider.fromResource(activity, R.drawable.location_on_24px))
                    point.userData = it // добавляем метке все данные о заведении
                    point.addTapListener(eMapObjectTapListener)
                }


            }
        }

        mapview.map.move(camPos, Animation(Animation.Type.SMOOTH, 0.toFloat()), null) // при запуске активити возвращаемся на предыдущее запомненное положение

    }

    private val eMapObjectTapListener =
        MapObjectTapListener { mapObject, point ->
            val teahouseData = mapObject.userData
            if(teahouseData is TeaHouse){
                Log.d("checkclick", teahouseData.address)
            }
            true
        } // искренне тупая система отслеживания клика, но работает


    override fun onStart() {
        super.onStart()
            mapview.onStart()
            MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        super.onStop()
        camPos = mapview.map.cameraPosition // запоминаем позицию

    }




}