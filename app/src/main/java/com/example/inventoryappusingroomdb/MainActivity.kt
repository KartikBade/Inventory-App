package com.example.inventoryappusingroomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.inventoryappusingroomdb.database.InventoryDatabase
import com.example.inventoryappusingroomdb.databinding.ActivityMainBinding
import com.example.inventoryappusingroomdb.repository.InventoryRepository
import com.example.inventoryappusingroomdb.viewmodel.InventoryViewModel
import com.example.inventoryappusingroomdb.viewmodel.InventoryViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    lateinit var inventoryViewModel: InventoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

        val inventoryRepository = InventoryRepository(InventoryDatabase.getDatabase(this))
        val inventoryViewModelProviderFactory = InventoryViewModelProviderFactory(inventoryRepository)
        inventoryViewModel = ViewModelProvider(this, inventoryViewModelProviderFactory).get(InventoryViewModel::class.java)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}