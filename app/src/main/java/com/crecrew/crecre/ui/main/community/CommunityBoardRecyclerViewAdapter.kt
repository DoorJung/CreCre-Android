package com.crecrew.crecre.ui.main.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.crecrew.crecre.BR
import com.crecrew.crecre.R
import com.crecrew.crecre.data.model.board.Board
import com.crecrew.crecre.databinding.ItemBoardBinding

class CommunityBoardRecyclerViewAdapter(
    val items: ArrayList<Board>
) : RecyclerView.Adapter<CommunityBoardRecyclerViewAdapter.ViewHolder>() {

    //val items = ArrayList<Board>()

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun replaceAll(items: ArrayList<Board>?) {
        items?.let {
            this.items.run {
                clear()
                addAll(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewDataBinding = DataBindingUtil.inflate<ItemBoardBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_board, parent, false
        )
        return ViewHolder(viewDataBinding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBinding.board = items[position]
        holder.dataBinding.root.setOnClickListener { listener?.onItemClicked(items[position]) }
        holder.dataBinding.itemBoardIvLike.setOnClickListener { listener?.onLikeClicked(items[position]) }
    }

    inner class ViewHolder(val dataBinding: ItemBoardBinding) : RecyclerView.ViewHolder(dataBinding.root)

    interface OnItemClickListener {
        fun onLikeClicked(item: Board)
        fun onItemClicked(item: Board)
    }
}