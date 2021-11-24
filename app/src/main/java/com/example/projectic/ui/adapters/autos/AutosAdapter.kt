package com.example.projectic.ui.adapters.autos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Auto
import com.example.projectic.databinding.VehiclesListViewBinding
import com.example.projectic.ui.adapters.base.BaseViewHolder

class AutosAdapter(
    private val vehiclesList: List<Auto>
): RecyclerView.Adapter<BaseViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = VehiclesListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VehiclesViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is VehiclesViewHolder -> holder.bind(vehiclesList[position])
        }
    }

    override fun getItemCount(): Int = vehiclesList.size

    private inner class VehiclesViewHolder(
        val binding: VehiclesListViewBinding
    ): BaseViewHolder<Auto>(binding.root) {
        override fun bind(item: Auto) {
            binding.txtRegistry.text = item.registration
            binding.txtHourEntry.text = item.hourEntry.toString()
            binding.txtType.text = item.type
        }
    }
}