package com.frantic.catfactsapp.presentation.catfavoritelistfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.frantic.catfactsapp.App
import com.frantic.catfactsapp.R
import com.frantic.catfactsapp.presentation.catfactslistfragment.CatFactItem
import kotlinx.android.synthetic.main.cat_fact_item.view.*

class CatFavoriteListAdapter : RecyclerView.Adapter<CatFavoriteListAdapter.ViewHolder>() {

    private var itemsList = mutableListOf<CatFactItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)!!
                .inflate(R.layout.cat_fact_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CatFavoriteListAdapter.ViewHolder, position: Int) {
        holder.catFactId.text = itemsList[position].id
        holder.catFactText.text = itemsList[position].text
        if (itemsList[position].preference) {
            holder.btnFavorite.setImageDrawable(App.context!!.getDrawable(R.drawable.cat_facts_favorite))
        } else {
            holder.btnFavorite.setImageDrawable(App.context!!.getDrawable(R.drawable.cat_facts_favorite_border))
        }
    }

    override fun getItemCount(): Int = itemsList.size

    fun setItemsList(itemsList: MutableList<CatFactItem>) {
        this.itemsList = itemsList
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val catFactId = view.catfact_id
        val catFactText = view.catfact_text
        val btnFavorite = view.btnFavorite
    }
}