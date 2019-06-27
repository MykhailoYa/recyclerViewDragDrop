package com.example.dragdropapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.dragdropapplication.data.ExampleViewModel
import com.example.dragdropapplication.dragDrop.DragDropAdapter
import com.example.dragdropapplication.dragDrop.DragDropViewHolder
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class ExampleAdapter(
    context: Context,
    private val exampleViewModel: ExampleViewModel
) : RecyclerView.Adapter<ExampleAdapter.ViewHolder>(), DragDropAdapter {

    lateinit var itemTouchHelper: ItemTouchHelper


    var dataList: MutableList<Int> = mutableListOf()
        set(value) {
            if (field == value) {
                // Apply same value without notifyDataSetChanged()
                field = value
            } else {
                field = value
                notifyDataSetChanged()
            }
        }

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        dataList[fromPosition] = dataList[toPosition].also {
            dataList[toPosition] = dataList[fromPosition]
        }

        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDropped(fromPosition: Int, toPosition: Int, viewHolder: RecyclerView.ViewHolder) {
        if (fromPosition != toPosition) {
            // Notify viewModel about list changes
            exampleViewModel.integerListMutableLiveData.postValue(dataList)
        }

        (viewHolder as ViewHolder).onViewHolderDropped()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), DragDropViewHolder {
        private var data: Int? = null

        fun bind(data: Int) {
            this.data = data

            itemView.dataTextView.text = data.toString()

            itemView.setOnLongClickListener {
                this@ViewHolder.onViewHolderStartDrag()
                itemTouchHelper.startDrag(this@ViewHolder)
                true
            }
        }

        override fun onViewHolderStartDrag() {
            // Change item background color
            (itemView as CardView).setCardBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.cardDragColor
                )
            )
        }

        override fun onViewHolderDropped() {
            // Change item background color
            (itemView as CardView).setCardBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.cardBgColor
                )
            )
        }
    }
}
