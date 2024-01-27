package com.yagosouza.meucarro.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.yagosouza.meucarro.core.extensions.DefaultDiffCallback
import com.yagosouza.meucarro.databinding.ItemCarBinding
import com.yagosouza.meucarro.domain.model.Car

class HomeAdapter(
    private val onItemClicked: (Car) -> Unit
) : ListAdapter<Car, HomeViewHolder>(DefaultDiffCallback<Car>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCarBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val car = getItem(position)
        holder.bindTo(car)
    }
}