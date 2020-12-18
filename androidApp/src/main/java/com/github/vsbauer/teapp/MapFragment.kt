package com.github.vsbauer.teapp

import TeappApi
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
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
import kotlinx.android.synthetic.main.bottom_sheet_teahouse_main.*
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


var camPos = CameraPosition(Point(59.93863, 30.31413), 11.0f, 0.0f, 0.0f) // изначальное положение карты на СПб
lateinit var res : List<TeaHouse>

lateinit var bottomSheet : BottomSheetBehavior<ConstraintLayout>
lateinit var textViewTitle : TextView
lateinit var textViewAddress : TextView
lateinit var textViewScore : TextView


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

        bottomSheet = BottomSheetBehavior.from((activity as MainActivity).bottomSheetTeahouse)
        textViewTitle = (activity as MainActivity).textView_teahouse_title
        textViewAddress = (activity as MainActivity).textView_teahouse_address
        textViewScore = (activity as MainActivity).textView_teahouse_score


        MapKitFactory.initialize(context)
        mapview.map.apply {
            logo.setAlignment(Alignment(HorizontalAlignment.LEFT, VerticalAlignment.TOP))
        }
        resTeahouses.forEach {
            addPointsOnMap(it)
        }
//        val api = TeappApi()
//        CoroutineScope(Dispatchers.IO).launch {
//
//            res = api.allTeaHouses()
//
//            res.forEach {
//
//                activity?.runOnUiThread { // расставляем маркеры
//                    addPointsOnMap(it)
//                }
//
//
//            }
//        }

        mapview.map.move(camPos, Animation(Animation.Type.SMOOTH, 0.toFloat()), null) // при запуске активити возвращаемся на предыдущее запомненное положение

    }

    private val eMapObjectTapListener =
        MapObjectTapListener { mapObject, point ->
            val teahouseData = mapObject.userData
            if(teahouseData is TeaHouse){
                bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
                textViewAddress.text = teahouseData.address
                textViewTitle.text = teahouseData.title
                Log.d("checkclick", teahouseData.address)
            }
            true
        } // искренне тупая система отслеживания клика, но работает

    private fun addPointsOnMap(teaHouse : TeaHouse){
        val latitude = teaHouse.coordinates.latitude
        val longitude = teaHouse.coordinates.longitude
        val point = mapview.map.mapObjects.addPlacemark(Point(latitude, longitude), ImageProvider.fromResource(activity, R.drawable.location_on_24px))
        point.userData = teaHouse // добавляем метке все данные о заведении
        point.addTapListener(eMapObjectTapListener)
    }
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