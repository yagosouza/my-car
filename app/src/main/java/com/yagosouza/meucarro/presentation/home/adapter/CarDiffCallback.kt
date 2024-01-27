package com.yagosouza.meucarro.presentation.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.yagosouza.meucarro.domain.model.Car

class CarDiffCallback : DiffUtil.ItemCallback<Car>() {

    override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem == newItem
    }
}