package otus.gpb.homework.viewandresources

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class CartItemItemTouchCallbacks(context : Context) : ItemTouchHelper.Callback(){

    private val ic_trash = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_trash,null)
    private val paint =  Paint().apply { setARGB(0xFF, 0xA0,0xA0,0xFF) }
    private val rect = Rect()
    private lateinit var bitmap :Bitmap
    private var ic_trash_size :Int = 0
    private var once = false

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(0, ItemTouchHelper.LEFT)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        // При Swipe влево освободившееся место заполнить голубым фоном с иконкой архивирования
        if( isCurrentlyActive && actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

            rect.set(viewHolder.itemView.width + dX.toInt(),
                viewHolder.itemView.y.toInt(),
                viewHolder.itemView.width + viewHolder.itemView.paddingRight,
                viewHolder.itemView.y.toInt() + viewHolder.itemView.height)

            c.drawRect(rect, paint)

            if( ic_trash!= null ) {
                if( !once ){
                    once = true
                    ic_trash_size = viewHolder.itemView.height / 2
                    bitmap = ic_trash.toBitmap(ic_trash_size, ic_trash_size)
                }
                
                c.drawBitmap(
                        bitmap,
                        (viewHolder.itemView.width - ic_trash_size - ic_trash_size / 2).toFloat(),
                        viewHolder.itemView.y + ic_trash_size / 2,
                        null
                    )
            }
        }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        (viewHolder as CartItemViewHolder).listener.onItemSwiped(viewHolder.item)
    }
}