package com.yagosouza.meucarro.presentation.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.yagosouza.meucarro.databinding.ItemCarBinding
import com.yagosouza.meucarro.domain.model.Car

class HomeViewHolder(
    private val binding: ItemCarBinding,
    private val onItemClicked: (Car) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(car: Car) = with(binding) {
        txtCarName.text = car.name
        root.setOnClickListener { onItemClicked(car) }
    }
}
