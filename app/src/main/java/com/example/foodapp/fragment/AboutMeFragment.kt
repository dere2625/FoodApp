package com.example.foodapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.adapter.AboutMeAdapter
import com.example.foodapp.models.AboutMeData
import com.example.foodapp.sharedpreference.AboutMeStorage
import com.google.android.material.floatingactionbutton.FloatingActionButton


class AboutMeFragment : Fragment() {

    private var aboutMeDataList = mutableListOf<AboutMeData>()
    private lateinit var adapter: AboutMeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about_me, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.aboutRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = AboutMeAdapter(aboutMeDataList)
        recyclerView.adapter = adapter

        loadAboutMeData()

        val fabAddDetail = view.findViewById<FloatingActionButton>(R.id.fabAddDetail)
        fabAddDetail.setOnClickListener {
            showAddDetailsDialog()
        }
    }

    private fun showAddDetailsDialog() {
        val dialogView = layoutInflater.inflate(R.layout.add_about_me, null)
        val editTextculinary = dialogView.findViewById<EditText>(R.id.culinary)
        val editTextfavorite = dialogView.findViewById<EditText>(R.id.favorite)
        val editTextfood = dialogView.findViewById<EditText>(R.id.food)

        AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add About Me")
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Add") { dialog, _ ->
                val culinary = editTextculinary.text.toString()
                val favorite = editTextfavorite.text.toString()
                val food = editTextfood.text.toString()
                val newItem = AboutMeData(culinary, favorite, food)
                aboutMeDataList.add(newItem)
                saveAboutMeData()
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            .show()
    }

    private fun saveAboutMeData() {
        AboutMeStorage.saveAboutMeData(requireContext(), aboutMeDataList)
    }

    private fun loadAboutMeData() {
        aboutMeDataList.clear()
        aboutMeDataList.addAll(AboutMeStorage.getAboutMeData(requireContext()))
        adapter.notifyDataSetChanged()
    }
}