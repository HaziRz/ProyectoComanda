package me.hazi.pytcomanda.model

data class MenuItem(
    val id: Int,
    val name: String,
    val price: Double,
    val imageRes: Int = 0,
    val quantity: Int = 0
) {
    val total: Double
    get() = quantity * price
}
