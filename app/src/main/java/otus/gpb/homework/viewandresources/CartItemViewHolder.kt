package otus.gpb.homework.viewandresources

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartItemViewHolder(
    private val view: View,
    val listener: CartItemListener
) : RecyclerView.ViewHolder(view) {

    private var root = view.findViewById<ViewGroup>(R.id.cart_item_container)
    private var image = view.findViewById<ImageView>(R.id.cart_item_image)
    private var price = view.findViewById<TextView>(R.id.cart_item_price)
    private var caption = view.findViewById<TextView>(R.id.cart_item_caption)
    private var category = view.findViewById<TextView>(R.id.cart_item_category)
    private var description = view.findViewById<TextView>(R.id.cart_item_description)

    lateinit var item :CartItem

    fun bind(item: CartItem){
        root.setOnClickListener { listener.onItemClick(item) }
        item.image?.let{ image.setImageDrawable(it) }
        price.text = item.price
        caption.text = item.caption
        category.text = item.category
        description.text = item.description
        this.item = item
    }
}