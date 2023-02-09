package com.example.inventoryappusingroomdb.repository

import com.example.inventoryappusingroomdb.database.InventoryDatabase
import com.example.inventoryappusingroomdb.model.InventoryItem

class InventoryRepository(private val inventoryDatabase: InventoryDatabase) {

    fun getAllItems() = inventoryDatabase.getDao().getAllItems()

    suspend fun addItem(inventoryItem: InventoryItem) = inventoryDatabase.getDao().addItem(inventoryItem)

    suspend fun deleteItem(inventoryItem: InventoryItem) = inventoryDatabase.getDao().deleteItem(inventoryItem)

    suspend fun updateItem(id: Long, name: String, quantity: Long, price: Double) = inventoryDatabase.getDao().updateItem(
        id, name, quantity, price
    )
}