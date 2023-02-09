package com.example.inventoryappusingroomdb.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.inventoryappusingroomdb.MainActivity
import com.example.inventoryappusingroomdb.R
import com.example.inventoryappusingroomdb.databinding.FragmentAddBinding
import com.example.inventoryappusingroomdb.model.InventoryItem
import com.example.inventoryappusingroomdb.viewmodel.InventoryViewModel

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    lateinit var inventoryViewModel: InventoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater)

        inventoryViewModel = (activity as MainActivity).inventoryViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentInventoryItem = inventoryViewModel.currentInventoryItem

        if (currentInventoryItem == null) {
            binding.addButton.setOnClickListener {
                try {
                    val itemName = binding.title.text.toString()
                    val itemQuantity = binding.quantity.text.toString().toLong()
                    val itemPrice = binding.price.text.toString().toDouble()

                    if (itemName.isNotEmpty()) {
                        inventoryViewModel.addItem(InventoryItem(0, itemName, itemQuantity, itemPrice))
                        Toast.makeText(context, "Item Added Successfully!", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_addFragment_to_listFragment)
                    } else {
                        Toast.makeText(context, "Please Enter a Name", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: java.lang.Exception) {
                    Toast.makeText(context, "Please Enter a Valid Input!", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            try {
                binding.title.setText(currentInventoryItem.itemName)
                binding.quantity.setText(currentInventoryItem.itemQuantity.toString())
                binding.price.setText(currentInventoryItem.itemPrice.toString())

                binding.addButton.setText("Update")
                binding.deleteButton.visibility = View.VISIBLE

                binding.addButton.setOnClickListener {

                    val itemName = binding.title.text.toString()
                    val itemQuantity = binding.quantity.text.toString().toLong()
                    val itemPrice = binding.price.text.toString().toDouble()

                    if (itemName.isNotEmpty()) {
                        inventoryViewModel.updateItem(currentInventoryItem.id, itemName, itemQuantity, itemPrice)
                        Toast.makeText(context, "Item Updated Successfully!", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_addFragment_to_listFragment)
                    } else {
                        Toast.makeText(context, "Please Enter a Name", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: java.lang.Exception) {
                Toast.makeText(context, "Please Enter a Valid Input!", Toast.LENGTH_SHORT).show()
            }

            binding.deleteButton.setOnClickListener {
                inventoryViewModel.deleteItem(currentInventoryItem)
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            }
        }
    }
}