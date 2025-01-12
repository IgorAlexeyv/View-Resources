package otus.gpb.homework.viewandresources

import android.graphics.drawable.Drawable

data class CartItem (
    val caption: String,
    val category: String,
    val price: String,
    val description: String,
    val image: Drawable? = null,
    )