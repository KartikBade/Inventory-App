package com.example.inventoryappusingroomdb.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("inventory")
data class InventoryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo("item_name")
    val itemName: String,
    @ColumnInfo("item_quantity")
    val itemQuantity: Long,
    @ColumnInfo("item_price")
    val itemPrice: Double
)
