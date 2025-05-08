package com.bosta.myapplication.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bosta.myapplication.R
import com.bosta.myapplication.data.model.District
import androidx.recyclerview.widget.DiffUtil

class DistrictAdapter : ListAdapter<District, DistrictAdapter.DistrictViewHolder>(DistrictDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistrictViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_district, parent, false)
        return DistrictViewHolder(view)
    }

    override fun onBindViewHolder(holder: DistrictViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DistrictViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val districtNameTextView: TextView = itemView.findViewById(R.id.textDistrictName)

        fun bind(district: District) {
            districtNameTextView.text = district.districtName
        }
    }

    class DistrictDiffCallback : DiffUtil.ItemCallback<District>() {
        override fun areItemsTheSame(oldItem: District, newItem: District): Boolean {
            return oldItem.districtId == newItem.districtId
        }

        override fun areContentsTheSame(oldItem: District, newItem: District): Boolean {
            return oldItem == newItem
        }
    }
}
