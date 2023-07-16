package com.example.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.myapplication.R

class CountryListGridAdapter(val countryList : List<String>) : BaseAdapter() {
    override fun getCount(): Int {
        return countryList.size
    }

    override fun getItem(position: Int): Any {
        return countryList[position]
    }

    override fun getItemId(position: Int): Long {
        return 3
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return LayoutInflater.from(parent?.context).inflate(R.layout.country_list_item, parent, false)
    }

}