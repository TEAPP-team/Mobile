package com.github.vsbauer.teapp

import TeappApi
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.UiThread
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import data.TeaHouse
import kotlinx.android.synthetic.main.teahouse_list.*
import kotlinx.android.synthetic.main.item_teahouse.view.*
import kotlinx.android.synthetic.main.teahouse_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TeahouseListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.teahouse_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()

        teahouseListSwipeRefresh.setOnRefreshListener {
            teahouseListSwipeRefresh.isRefreshing = false
            Toast.makeText(requireActivity().applicationContext,
                "List Updated", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun getData() {
        resTeahouses.forEach { _ ->
            val newTeahouse = LayoutInflater.from(requireActivity().applicationContext)
                .inflate(R.layout.item_teahouse, null, false)
            teahouseListLayout.addView(newTeahouse)
            newTeahouse.setOnClickListener {}
        }
    }
}