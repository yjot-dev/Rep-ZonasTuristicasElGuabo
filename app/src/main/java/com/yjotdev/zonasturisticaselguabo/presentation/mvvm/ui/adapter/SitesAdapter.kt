package com.yjotdev.zonasturisticaselguabo.presentation.mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yjotdev.zonasturisticaselguabo.presentation.utils.Site

class SitesAdapter(
    private val sites: List<Site>,
    private val onClick: (Site) -> Unit
) : RecyclerView.Adapter<SitesAdapter.SiteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return SiteViewHolder(view)
    }

    override fun onBindViewHolder(holder: SiteViewHolder, position: Int) {
        val site = sites[position]
        holder.bind(site, onClick)
    }

    override fun getItemCount() = sites.size

    class SiteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(site: Site, onClick: (Site) -> Unit) {
            val title = itemView.context.getString(site.titleId)
            (itemView as TextView).text = title
            itemView.setOnClickListener { onClick(site) }
        }
    }
}