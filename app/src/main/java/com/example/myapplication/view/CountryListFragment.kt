package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.view.adapter.CountryListAdapter
import com.example.myapplication.view.adapter.CountryListGridAdapter

class CountryListFragment : Fragment() {
    lateinit var binding: FragmentListBinding

    companion object {
        fun newInstance() : CountryListFragment {
            val fragment = CountryListFragment()
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
    }

    override fun onResume() {
        super.onResume()
        val listFragment2 = ListFragment2.newInstance()
    }

}