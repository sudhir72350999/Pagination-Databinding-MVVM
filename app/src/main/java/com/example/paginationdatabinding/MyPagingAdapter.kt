package com.example.paginationdatabinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class MyPagingAdapter : PagingDataAdapter<MyDataModel, MyPagingAdapter.MyViewHolder>(MyDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: MyDataModel) {
            itemView.findViewById<TextView>(R.id.text_view).text = data.name
            itemView.findViewById<TextView>(R.id.text_password).text = data.password
        }
    }
}

class MyDiffUtil : DiffUtil.ItemCallback<MyDataModel>() {
    override fun areItemsTheSame(oldItem: MyDataModel, newItem: MyDataModel): Boolean {
        return oldItem.name == newItem.name && oldItem.password == newItem.password
    }

    override fun areContentsTheSame(oldItem: MyDataModel, newItem: MyDataModel): Boolean {
        return oldItem == newItem
    }
}
