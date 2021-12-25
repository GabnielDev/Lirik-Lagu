package com.example.liriklagu.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.liriklagu.R
import com.example.liriklagu.data.DataItem
import com.example.liriklagu.databinding.ItemListBinding

class HotAdapter(
    private var listData : MutableList<DataItem?>,
    private var onItemClickCallback: OnItemClickCallback
): RecyclerView.Adapter<HotAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listData: MutableList<DataItem?>) {
        this.listData = listData
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListBinding.bind(itemView)
        fun bind(data: DataItem?) {
            with(binding) {
                txtLagu.text = data?.songTitle
                txtArtis.text = data?.artist

                itemView.setOnClickListener {
                    onItemClickCallback.onItemClick(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    interface OnItemClickCallback {
        fun onItemClick(data: DataItem?)
    }
}