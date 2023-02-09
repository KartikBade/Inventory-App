package com.example.inventoryappusingroomdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inventoryappusingroomdb.model.InventoryItem

@Database([InventoryItem::class], version = 1)
abstract class InventoryDatabase: RoomDatabase() {

    abstract fun getDao(): InventoryDao

    companion object {
        @Volatile
        private var INSTANCE: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            return (INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    InventoryDatabase::class.java,
                    "inventoryDb"
                ).build()
                INSTANCE = instance

                instance
            })
        }
    }
}