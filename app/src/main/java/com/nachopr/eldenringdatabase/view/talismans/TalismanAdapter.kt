package com.nachopr.eldenringdatabase.view.talismans

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nachopr.eldenringdatabase.databinding.ItemRowBinding
import com.nachopr.eldenringdatabase.model.remote.Talismans

class TalismanAdapter () : RecyclerView.Adapter<TalismanAdapter.TalismanViewHolder>() {

    private var talismansList = emptyList<Talismans>()

    var onClickListener: (Talismans) -> Unit = {}

    @SuppressLint("NotifyDataSetChanged")
    fun submitTalismanList(list: List<Talismans>) {
        talismansList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TalismanViewHolder {
        val binding =
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TalismanViewHolder(binding)
    }

    override fun getItemCount() = talismansList.size

    override fun onBindViewHolder(holder: TalismanViewHolder, position: Int) {
        val talismanItem = talismansList[position]

        holder.talismanName.text = talismanItem.name

        Glide.with(holder.talismanImageView)
            .load(talismanItem.image)
            .into(holder.talismanImageView)

        holder.rootView.setOnClickListener {
            onClickListener.invoke(talismanItem)
        }
    }

    inner class TalismanViewHolder(binding: ItemRowBinding) :
            RecyclerView.ViewHolder(binding.root) {
        val rootView = binding.root
        val talismanName = binding.tvItemListName
        val talismanImageView = binding.ivItemList
    }
}