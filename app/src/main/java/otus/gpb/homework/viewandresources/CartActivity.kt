package otus.gpb.homework.viewandresources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity(), CartItemListener {
    private lateinit var list :MutableList<CartItem>
    private lateinit var adapter :CartItemAdapter
    private val list_generate_size = 40
    private var num = 1

    private lateinit var recyclerView :RecyclerView
    private lateinit var textCartItemCount :TextView
    private lateinit var textCartOrderTotalAmount :TextView
    private lateinit var textCartSubtotalAmount :TextView
    private lateinit var textCartShippingAmount :TextView
    private lateinit var textCartTaxAmount :TextView
    private lateinit var textCartOrderTotal :TextView
    private lateinit var textCartSubtotal :TextView
    private lateinit var textCartShipping :TextView
    private lateinit var textCartTax :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        textCartOrderTotal = findViewById<TextView>(R.id.textCartOrderTotal)
        textCartSubtotal = findViewById<TextView>(R.id.textCartSubtotal)
        textCartShipping = findViewById<TextView>(R.id.textCartShipping)
        textCartTax = findViewById<TextView>(R.id.textCartTax)
        textCartItemCount = findViewById<TextView>(R.id.textCartItemCount)
        textCartOrderTotalAmount = findViewById<TextView>(R.id.textCartOrderTotalAmount)
        textCartSubtotalAmount = findViewById<TextView>(R.id.textCartSubtotalAmount)
        textCartShippingAmount = findViewById<TextView>(R.id.textCartShippingAmount)
        textCartTaxAmount = findViewById<TextView>(R.id.textCartTaxAmount)

        adapter = CartItemAdapter(this)
        list = generateList()

        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        ItemTouchHelper(CartItemItemTouchCallbacks(this)).attachToRecyclerView(recyclerView)
        recyclerView.adapter = adapter
        adapter.setItems(this.list)

        textCartOrderTotal.text = getString(R.string.order_total)
        textCartSubtotal.text = getString(R.string.subtotal)
        textCartShipping.text = getString(R.string.shipping)
        textCartTax.text = getString(R.string.tax)
    }

    // Клик на товаре в корзине.
    override fun onItemClick(item: CartItem) {
        Toast.makeText(this, item.caption + "\n" + item.description, Toast.LENGTH_SHORT).show()
    }

    // Удаляет товар из корзины
    override fun onItemSwiped(item: CartItem) {
        val index = list.indexOf(item)
        list.removeAt(index)
        recyclerView.post { adapter.notifyItemRemoved(index) }
    }

    // Подгружает 40 новых товаров в корзину
    override fun onLoadMoreItem() {
        val position = list.size - 1
        val newList = generateList()
        list+= newList
        recyclerView.post { adapter.notifyItemRangeInserted(position, newList.size) }
    }

    private fun generateList(): MutableList<CartItem> {
        var totalAmount = 0
        data class Item(val resId: Int, val name: String)
        val items = listOf(
            Item(R.drawable.im_goods, getString(R.string.subtotal)),
            Item(R.drawable.im_sport, getString(R.string.sport)),
            Item(R.drawable.im_groceries, getString(R.string.groceries)),
            Item(R.drawable.im_food, getString(R.string.food))
        )

        val list = mutableListOf<CartItem>()
        repeat(list_generate_size) {
            val amount = (1..1000000).random()
            totalAmount += amount
            val i = (0..3).random()
            val item = CartItem(
                items[i].name,
                getString(R.string.category) +" ${num++}",
                formatAmount(amount),
                getString(R.string.discription),
                ResourcesCompat.getDrawable(getResources(), items[i].resId,null))
            list.add(item)
        }

        textCartItemCount.text = "${num-1}" + getString(R.string.items_in_cart)
        textCartOrderTotalAmount.text = formatAmount(totalAmount)
        textCartSubtotalAmount.text = formatAmount((totalAmount.toFloat() * 0.85).toInt())
        textCartShippingAmount.text = formatAmount((totalAmount.toFloat() * 0.20).toInt())
        textCartTaxAmount.text = formatAmount((totalAmount.toFloat() * 0.1).toInt())

        return list
    }

    private fun formatAmount(amount :Int) :String {
        val first = amount / 100
        val second  = amount % 100
        return String.format("$%d.%02d", first, second)
    }
}