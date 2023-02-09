package com.example.inventoryappusingroomdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inventoryappusingroomdb.repository.InventoryRepository

class InventoryViewModelProviderFactory(
    private val inventoryRepository: InventoryRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InventoryViewModel(inventoryRepository) as T
    }
}