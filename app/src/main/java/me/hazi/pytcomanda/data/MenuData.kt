package me.hazi.pytcomanda.data

import me.hazi.pytcomanda.R
import me.hazi.pytcomanda.model.MenuItem

object MenuData {
    val items = listOf(
        MenuItem(1, "Paella", 100.00),
        MenuItem(2, "Lasaña", 95.00),
        MenuItem(3, "Postre", 45.00, R.drawable.postre),
        MenuItem(4, "Bebida", 25.00, R.drawable.bebida),
    )
}