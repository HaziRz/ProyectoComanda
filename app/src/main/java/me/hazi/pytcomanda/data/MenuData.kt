package me.hazi.pytcomanda.data

import me.hazi.pytcomanda.R

object MenuData {
    val items = listOf(
        MenuItem(1, "Paella", 100.00, R.drawable.paella),
        MenuItem(2, "Lasaña", 95.00, R.drawable.lasana),
        MenuItem(3, "Postre", 45.00, R.drawable.postre),
        MenuItem(4, "Bebida", 25.00, R.drawable.bebida),
    )
}

data class MenuItem(
    val id: Int,
    val name: String,
    val price: Double,
    val imageRes: Int,
    var quantity: Int = 0
) {
    val total: Double
        get() = quantity * price
}