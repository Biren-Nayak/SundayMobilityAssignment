package com.example.sundaymobilityassignment.adapters

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sundaymobilityassignment.classes.Player
import com.example.sundaymobilityassignment.databinding.ItemViewBinding


class PlayerAdapter : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.lastName.visibility = VISIBLE
        holder.binding.firstName.text = item.firstName
        holder.binding.lastName.text = differ.currentList[position].lastName
        holder.binding.srNo.apply {
            visibility =  when (item.isCaptain) {
                true -> {
                    text = "C"
                    VISIBLE
                }
                false -> {
                    text = ""
                    GONE
                }
            }

        }


        holder.binding.layout.setOnClickListener { view ->
            Toast.makeText(view.context,
                "${holder.binding.firstName.text} ${holder.binding.lastName.text}",
                Toast.LENGTH_LONG).show()
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemViewBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }


    override fun getItemCount(): Int = differ.currentList.size
}