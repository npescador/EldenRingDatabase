package com.nachopr.eldenringdatabase.view.weapons

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nachopr.eldenringdatabase.databinding.ItemRowBinding
import com.nachopr.eldenringdatabase.model.remote.Weapon

class WeaponAdapter () : RecyclerView.Adapter<WeaponAdapter.WeaponViewHolder>() {

    private var weaponsList = emptyList<Weapon>()

    var onClickListener: (Weapon) -> Unit = {}

    @SuppressLint("NotifyDataSetChanged")
    fun submitWeaponList(list: List<Weapon>) {
        weaponsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {
        val binding =
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeaponViewHolder(binding)
    }

    override fun getItemCount() = weaponsList.size

    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        val weaponItem = weaponsList[position]

        holder.weaponName.text = weaponItem.name

        Glide.with(holder.weaponImageView)
            .load(weaponItem.image)
            .into(holder.weaponImageView)

        holder.rootView.setOnClickListener {
            onClickListener.invoke(weaponItem)
        }
    }

    inner class WeaponViewHolder(binding: ItemRowBinding) :
            RecyclerView.ViewHolder(binding.root) {
        val rootView = binding.root
        val weaponName = binding.tvItemListName
        val weaponImageView = binding.ivItemList
    }
}