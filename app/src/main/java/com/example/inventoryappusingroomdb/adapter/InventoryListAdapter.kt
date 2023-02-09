package com.example.inventoryappusingroomdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.inventoryappusingroomdb.databinding.InventoryListItemBinding
import com.example.inventoryappusingroomdb.model.InventoryItem

class InventoryListAdapter: ListAdapter<InventoryItem, InventoryListAdapter.InventoryListViewHolder>(DiffCallBack) {

    class InventoryListViewHolder(val binding: InventoryListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(inventoryItem: InventoryItem) {
            binding.itemTitle.text = inventoryItem.itemName
            binding.itemPrice.text = "Price: Rs.${inventoryItem.itemPrice}"
            binding.itemQuantity.text = "Quantity: ${inventoryItem.itemQuantity}"

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryListViewHolder {
        return InventoryListViewHolder(InventoryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: InventoryListViewHolder, position: Int) {
        val currentInventoryItem = getItem(position)
        holder.bind(currentInventoryItem)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(currentInventoryItem)
            }
        }
        holder.binding.saleButton.setOnClickListener {
            onSaleButtonClickListener?.let {
                it(currentInventoryItem)
            }
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<InventoryItem>() {
        override fun areItemsTheSame(oldItem: InventoryItem, newItem: InventoryItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: InventoryItem, newItem: InventoryItem): Boolean {
            return oldItem.id == newItem.id
        }

    }

    private var onItemClickListener: ((InventoryItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (InventoryItem) -> Unit) {
        onItemClickListener = listener
    }

    private var onSaleButtonClickListener: ((InventoryItem) -> Unit)? = null

    fun setSaleButtonClickListener(listener: (InventoryItem) -> Unit) {
        onSaleButtonClickListener = listener
    }
}