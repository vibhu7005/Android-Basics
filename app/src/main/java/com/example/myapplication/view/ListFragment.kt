package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.view.adapter.CountryListAdapter

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
        val countryList = listOf("Bangkok", "London", "Tokyo")
        val adapter = CountryListAdapter(countryList)
        binding.rvListStudents.layoutManager = LinearLayoutManager(context)
        binding.rvListStudents.adapter = adapter
//        binding.listStudents.adapter = adapter
//        binding.listStudents.setOnItemClickListener { _, _, position, _ ->
//            Toast.makeText(context, countryList[position], Toast.LENGTH_SHORT).show()
//        }
    }

    override fun onResume() {
        super.onResume()
        val listFragment2 = ListFragment2.newInstance()
    }

}