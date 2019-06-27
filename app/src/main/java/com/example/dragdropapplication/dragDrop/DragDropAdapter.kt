package com.example.dragdropapplication.dragDrop

import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter should implement it to use Drag&Drop
 */
interface DragDropAdapter {

    /**
     * Called when an item has been dragged far enough to trigger a move. This is called every time
     * an item is shifted, and **not** at the end of a "drop" event.<br></br>
     *
     * Implementation should swap items on positions and
     * call RecyclerView.Adapter.notifyItemMoved(fromPosition, toPosition)
     *
     * @param fromPosition The start position of the moved item.
     * @param toPosition The resolved position of the moved item.
     *
     * @see RecyclerView.getAdapterPositionFor
     * @see RecyclerView.ViewHolder.getAdapterPosition
     */
    fun onItemMove(fromPosition: Int, toPosition: Int)

    /**
     * Called when item has been dropped
     *
     * Implementation should update data in ViewModel (if used)
     * and call (viewHolder as ViewHolder).onViewHolderDropped()
     * to notify viewHolder about drop event
     *
     * @param fromPosition The start position of dragged item
     * @param toPosition The end position of dragged item
     */
    fun onItemDropped(fromPosition: Int, toPosition: Int, viewHolder: RecyclerView.ViewHolder)
}