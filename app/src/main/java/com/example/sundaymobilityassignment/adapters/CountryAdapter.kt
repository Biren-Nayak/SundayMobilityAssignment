package com.example.sundaymobilityassignment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sundaymobilityassignment.databinding.ItemViewBinding
import com.example.sundaymobilityassignment.ui.fragment.HomeFragmentDirections


class CountryAdapter: RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    private val differCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

        val differ = AsyncListDiffer(this, differCallback)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.srNo.visibility = View.VISIBLE
        holder.binding.srNo.text = (position+1).toString()
        holder.binding.firstName.text = item

        holder.binding.layout.setOnClickListener {
            Toast.makeText(it.context, "Country: ${differ.currentList[position]}", Toast.LENGTH_LONG).show()
            holder.binding.root.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToPlayersFragment(differ.currentList[position]))
        }
    }


    override fun getItemCount(): Int = differ.currentList.size

}