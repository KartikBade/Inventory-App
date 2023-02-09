package com.example.inventoryappusingroomdb.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.inventoryappusingroomdb.model.InventoryItem

@Dao
interface InventoryDao {

    @Query("SELECT * FROM inventory ORDER BY id ASC")
    fun getAllItems(): LiveData<List<InventoryItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(inventoryItem: InventoryItem)

    @Query("UPDATE inventory SET item_name = :name, item_quantity = :quantity, item_price = :price WHERE id = :id")
    suspend fun updateItem(id: Long, name: String, quantity: Long, price: Double)

    @Delete
    suspend fun deleteItem(inventoryItem: InventoryItem)
}