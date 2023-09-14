package com.jordiee.kotlindev.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.jordiee.kotlindev.R

private const val TAG = "Country Adapter"

class CountryListAdapter(private val countryList : List<String>, private val context: Context) : RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    class CountryViewHolder(itemView: View, context : Context) : RecyclerView.ViewHolder(itemView) {
        val cardView : CardView = itemView.findViewById(R.id.card_view_country_info)
        val textView : TextView = itemView.findViewById(R.id.tv_country_name)
         init {
            cardView.setOnClickListener {
                Toast.makeText(context, adapterPosition.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        fun setData(country : String) {
            textView.text = country
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        Log.d(TAG, "onCreate")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_list_item, parent, false)
        return CountryViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "onItemCount")
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        Log.d(TAG, "onBind")
        holder.setData(countryList[position])
    }
}