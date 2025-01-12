package otus.gpb.homework.viewandresources

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CartItemAdapter(private val listener: CartItemListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var list: MutableList<CartItem>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return CartItemViewHolder(view, listener)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        (holder as CartItemViewHolder).bind(item)

        if(position == list.size - 1) {
            listener.onLoadMoreItem()
        }
    }

    fun setItems(items: MutableList<CartItem>) {
        list = items
        notifyItemInserted(0)
    }
}