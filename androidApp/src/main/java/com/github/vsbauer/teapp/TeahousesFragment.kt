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
import kotlinx.android.synthetic.main.fragment_teahouses.*
import kotlinx.android.synthetic.main.item_teahouse.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TeahousesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_teahouses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()

    }
    fun getData(){

        val adapter = GroupAdapter<GroupieViewHolder>()
        teahousesRecyclerView.adapter = adapter

        adapter.setOnItemClickListener { item, view ->
            val teahouse = item as TeahouseItem
            Log.d("loglistener", teahouse.name) // потом жмакать будем
        }

        resTeahouses.forEach {
            adapter.add(TeahouseItem(it.title))
        }

    }
    class TeahouseItem(val name : String) : Item<GroupieViewHolder>(){
        override fun getLayout() = R.layout.item_teahouse
        override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            viewHolder.itemView.teahouse_name.text = name
        }
    }
}