package com.example.inventoryappusingroomdb.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.inventoryappusingroomdb.MainActivity
import com.example.inventoryappusingroomdb.R
import com.example.inventoryappusingroomdb.adapter.InventoryListAdapter
import com.example.inventoryappusingroomdb.databinding.FragmentListBinding
import com.example.inventoryappusingroomdb.viewmodel.InventoryViewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    lateinit var inventoryViewModel: InventoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater)

        inventoryViewModel = (activity as MainActivity).inventoryViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inventoryListAdapter = InventoryListAdapter()
        inventoryListAdapter.setOnItemClickListener {
            inventoryViewModel.currentInventoryItem = it
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        inventoryListAdapter.setSaleButtonClickListener {
            val newQuantity = it.itemQuantity - 1
            if (newQuantity >= 0) {
                inventoryViewModel.updateItem(it.id, it.itemName, newQuantity, it.itemPrice)
            } else {
                Toast.makeText(context, "Out of Stock!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.itemRecyclerView.adapter = inventoryListAdapter

        inventoryViewModel.getAllItems().observe(viewLifecycleOwner) {
            inventoryListAdapter.submitList(it)
        }

        binding.fab.setOnClickListener {
            inventoryViewModel.currentInventoryItem = null
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
    }
}