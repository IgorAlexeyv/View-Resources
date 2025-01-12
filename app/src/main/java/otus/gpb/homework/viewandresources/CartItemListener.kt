package otus.gpb.homework.viewandresources

interface CartItemListener {
    fun onItemClick(item: CartItem)
    fun onItemSwiped(item: CartItem)
    fun onLoadMoreItem()
}