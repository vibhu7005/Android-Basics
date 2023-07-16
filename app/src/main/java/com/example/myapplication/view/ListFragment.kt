package com.example.myapplication.view

import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.view.adapter.CountryListAdapter
import com.example.myapplication.view.adapter.CountryListGridAdapter

class ListFragment : Fragment() {
    lateinit var binding: FragmentListBinding

    companion object {
        fun newInstance() : ListFragment {
            val fragment = ListFragment()
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val countryList = listOf("Bangkok", "London", "Tokyo", "mapple", "Sydney", "Austin", "Russia", "Bolavia", "Libya", "Sudan", "Nigeria", "India", "Bhutan", "Nepal", "Australia", "Canada", "USA")
        val adapter = CountryListAdapter(countryList, requireContext())
        binding.rvListCountries.layoutManager = GridLayoutManager( context, 2 ,RecyclerView.VERTICAL, false)
        binding.rvListCountries.adapter = adapter
        binding.gvListStudents.adapter = CountryListGridAdapter(countryList)

    }

    override fun onResume() {
        super.onResume()
        val listFragment2 = ListFragment2.newInstance()
    }

}