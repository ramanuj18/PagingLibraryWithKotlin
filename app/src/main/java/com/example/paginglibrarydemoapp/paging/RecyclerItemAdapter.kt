package com.example.paginglibrarydemoapp.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paginglibrarydemoapp.R
import kotlinx.android.synthetic.main.item_layout.view.*

class RecyclerItemAdapter :PagedListAdapter<Item,RecyclerItemAdapter.ItemViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
       val item=getItem(position)
        item?.let {
            holder.setData(item)
        }
    }

    class ItemViewHolder(val view: View):RecyclerView.ViewHolder(view) {
        fun setData(item: Item) {
            view.repo_name.text = item.fullName
            view.repo_description.text = item.description
            view.repo_language.text = item.language
            view.repo_stars.text = item.stars.toString()
            view.repo_forks.text = item.forks.toString()
        }
    }

    companion object {
        private val REPO_COMPARATOR=object :DiffUtil.ItemCallback<Item>(){
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
                 oldItem.fullName == newItem.fullName

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem == newItem
        }
    }
}