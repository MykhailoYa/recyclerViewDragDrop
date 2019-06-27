package com.example.dragdropapplication.dragDrop

/**
 * ViewHolder should implement it to use Drag&Drop
 *
 * Implementation should add onLongClickListener in bind method to itemView like this:
 * <pre>
 * itemView.setOnLongClickListener {
 *     // Notify ViewHolder about start drag
 *     this@ViewHolder.onViewHolderStartDrag()
 *
 *     itemTouchHelper.startDrag(this@ViewHolder)
 *     true
 * }
 * </pre>
 */
interface DragDropViewHolder {

    /**
     * Called when ViewHolder start drag
     *
     * Implementation can, for example, change color to visualize dragging
     */
    fun onViewHolderStartDrag()

    /**
     * Called when ViewHolder has been dropped
     *
     * Implementation can, for example, change color back to standard
     */
    fun onViewHolderDropped()
}