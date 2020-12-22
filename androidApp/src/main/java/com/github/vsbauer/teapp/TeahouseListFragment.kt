package com.github.vsbauer.teapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import data.TeaHouse
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
        progressBar.visibility = View.VISIBLE
        updateData(0)

        teahouseListSwipeRefresh.setOnRefreshListener {
//            Toast.makeText(
//                requireActivity().applicationContext,
//                "List Updated", Toast.LENGTH_SHORT
//            ).show()
            progressBar.visibility = View.VISIBLE
            updateData(100)
        }
    }

    private fun updateData(delay: Int = 0) {
        teahouseListLayout.removeAllViews()

        CoroutineScope(Dispatchers.IO).launch {
            val teahouseList = (activity as MainActivity).getTeahouseList().shuffled()

            activity?.runOnUiThread {
                teahouseListSwipeRefresh.isRefreshing = false
                progressBar.visibility = View.GONE

                var index: Long = 0

                teahouseList.forEach { it ->
                    val newTeahouse = LayoutInflater.from(requireActivity().applicationContext)
                        .inflate(R.layout.item_teahouse, null, false)
                    newTeahouse.findViewWithTag<TextView>("name").text = it.title
                    newTeahouse.findViewWithTag<TextView>("address").text = it.address

                    newTeahouse.findViewWithTag<TextView>("")

                    newTeahouse.findViewWithTag<Button>("favourite").setOnClickListener {
                        it.setBackgroundResource(R.drawable.ic_favourites)
                    }

                    Handler().postDelayed(
                        { teahouseListLayout?.addView(newTeahouse) }, index * delay
                    )
                    newTeahouse.setOnClickListener {
                        startActivity(
                            Intent(
                                requireActivity().applicationContext,
                                TeahousePageActivity::class.java
                            )
                        )
                    }
                    index += 1
                }
            }
        }
    }
}