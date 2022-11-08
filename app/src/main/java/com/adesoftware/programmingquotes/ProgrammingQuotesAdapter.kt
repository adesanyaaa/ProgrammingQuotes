package com.adesoftware.programmingquotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adesoftware.programmingquotes.databinding.ItemProgramQuotesBinding

class ProgrammingQuotesAdapter :
    RecyclerView.Adapter<ProgrammingQuotesAdapter.ProgrammingQuotesViewHolder>() {

    class ProgrammingQuotesViewHolder(
        val binding: ItemProgramQuotesBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<ProgrammingQuotes>() {
        override fun areItemsTheSame(
            oldItem: ProgrammingQuotes,
            newItem: ProgrammingQuotes
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProgrammingQuotes,
            newItem: ProgrammingQuotes
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var programmingQuotes: List<ProgrammingQuotes>
        get() = differ.currentList
        set(value) {
            differ.submitList((value))
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgrammingQuotesViewHolder {
        return ProgrammingQuotesViewHolder(ItemProgramQuotesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: ProgrammingQuotesViewHolder, position: Int) {
        holder.binding.apply {
            val programmingQuotes = programmingQuotes[position]
            tvAuthor.text = programmingQuotes.author
            tvQuotes.text = programmingQuotes.en
        }
    }

    override fun getItemCount(): Int = programmingQuotes.size

}