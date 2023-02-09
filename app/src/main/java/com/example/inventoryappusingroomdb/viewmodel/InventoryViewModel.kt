package com.example.inventoryappusingroomdb.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventoryappusingroomdb.model.InventoryItem
import com.example.inventoryappusingroomdb.repository.InventoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InventoryViewModel(
    private val inventoryRepository: InventoryRepository
): ViewModel() {

    var currentInventoryItem: InventoryItem? = null

    fun getAllItems(): LiveData<List<InventoryItem>> {
        return inventoryRepository.getAllItems()
    }

    fun addItem(inventoryItem: InventoryItem) {
        viewModelScope.launch(Dispatchers.IO) {
            inventoryRepository.addItem(inventoryItem)
        }
    }

    fun deleteItem(inventoryItem: InventoryItem) {
        viewModelScope.launch(Dispatchers.IO) {
            inventoryRepository.deleteItem(inventoryItem)
        }
    }

    fun updateItem(id: Long, name: String, quantity: Long, price: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            inventoryRepository.updateItem(id, name, quantity, price)
        }
    }
}