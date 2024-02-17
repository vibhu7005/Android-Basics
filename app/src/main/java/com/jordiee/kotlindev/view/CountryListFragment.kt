package com.jordiee.kotlindev.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jordiee.kotlindev.R
import com.jordiee.kotlindev.databinding.FragmentListBinding
import com.jordiee.kotlindev.view.adapter.CountryListAdapter

class CountryListFragment : Fragment() {
    lateinit var binding: FragmentListBinding
    lateinit var x : String

    companion object {
        fun newInstance() : CountryListFragment {
            val fragment = CountryListFragment()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        x = "sort"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("vaibhav country fragment", "onCreateView")
        binding = FragmentListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val countryList = listOf("Bangkok", "London", "Tokyo", "mapple", "Sydney", "Austin", "Russia", "Bolavia", "Libya", "Sudan", "Nigeria", "India", "Bhutan", "Nepal", "Australia", "Canada", "USA")
        val adapter = CountryListAdapter(countryList, requireContext())
        binding.rvListCountries.layoutManager = LinearLayoutManager( context, RecyclerView.VERTICAL, false)
        binding.rvListCountries.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
//        val listFragment2 = WebViewFragment.newInstance()
//        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment_container, listFragment2, "Frag2").commit()
    }
}