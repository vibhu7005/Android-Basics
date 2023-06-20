package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ListFragment : Fragment() {

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
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        val listFragment2 = ListFragment2.newInstance()
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, listFragment2, "listFragment")?.commit()
    }

}