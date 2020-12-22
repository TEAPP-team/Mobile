package com.github.vsbauer.teapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import data.TeaHouse
import kotlinx.android.synthetic.main.fragment_favourites.*
import kotlinx.android.synthetic.main.teahouse_list.*


class FavouritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateData()
    }

    private fun updateData() {
        val teahouseList = listOf(
            TeaHouse(
                title = "Чайный дом",
                address = "Улица Рубинштейна, 13",
                phone = "+79650097399",
                site = "https://teahouse-group.ru/grand"
            ), TeaHouse(
                title = "Чайная студия на Васильевском",
                address = "7-я линия Васильевского острова, д. 34",
                phone = "+78129244385",
                site = "https://kitchai.ru/"
            ), TeaHouse(
                title = "Белая обезьяна",
                address = "ул. Малая Пушкарская, д. 4-6",
                phone = "+78126282034",
                site = "https://whitemonkeytea.ru"
            )
        )
        teahouseList.forEach {
            val newTeahouse = LayoutInflater.from(requireActivity().applicationContext)
                .inflate(R.layout.favourite_list_item, null, false)

            newTeahouse.findViewWithTag<TextView>("name").text = it.title

            newTeahouse.setOnClickListener {
                val teahouseIntent = Intent(
                    requireActivity().applicationContext,
                    TeahousePageActivity::class.java
                )

                teahouseIntent.putExtra("TeahouseData", it.toString())

                startActivity(teahouseIntent)
            }

            favouriteListLayout.addView(newTeahouse)
        }
    }
}