package com.example.myapplication.ui.listfragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.DataItemBinding
import com.example.myapplication.ui.listfragment.ListViewModel.EventListener
import com.example.myapplication.ui.listfragment.model.UiData

class DataListAdapter(private val clickListener: EventListener): ListAdapter<UiData, DataViewHolder>(DataDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataItemBinding.inflate(layoutInflater, parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }
}

class DataViewHolder(private val binding: DataItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(data: UiData, listener: EventListener) {
        with(binding){
            this.data = data
            this.clickListener = listener
            executePendingBindings()
        }
    }
}

class DataDiffUtilCallback : DiffUtil.ItemCallback<UiData>(){
    override fun areItemsTheSame(oldItem: UiData, newItem: UiData): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: UiData, newItem: UiData): Boolean {
        return oldItem == newItem
    }

}