package com.example.dragdropapplication.dragDrop

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * Item touch helper callback for Drag&Drop
 *
 * Set this callback for adapter like this:
 * <pre>
 * val dragDropCallback = DragDropItemTouchHelperCallback(adapter, ItemTouchHelper.UP or ItemTouchHelper.DOWN)
 * val itemTouchHelper = ItemTouchHelper(dragDropCallback)
 * adapter.attachToRecyclerView(itemTouchHelper)
 * adapter.itemTouchHelper = itemTouchHelper // adapter.itemTouchHelper - your field
 * </pre>
 */
class DragDropItemTouchHelperCallback(
    private val adapter: DragDropAdapter,
    dragDirs: Int
) : ItemTouchHelper.SimpleCallback(dragDirs, ItemTouchHelper.RIGHT) {

    // Positions of element dragging
    private var fromPos = -1
    private var toPos = -1

    override fun isItemViewSwipeEnabled(): Boolean {
        return false
    }

    override fun isLongPressDragEnabled(): Boolean {
        return false
    }

    override fun onMove(
        recyclerView: RecyclerView,
        source: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        if (source.itemViewType != target.itemViewType) {
            return false
        }

        val fromPosition = source.adapterPosition
        val toPosition = target.adapterPosition

        if (fromPos == -1)
            fromPos = fromPosition
        toPos = toPosition

        adapter.onItemMove(fromPosition, toPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {}

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        adapter.onItemDropped(fromPos, toPos, viewHolder)
        fromPos = -1
        toPos = -1
    }

    override fun getMoveThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return 0.1f
    }
}