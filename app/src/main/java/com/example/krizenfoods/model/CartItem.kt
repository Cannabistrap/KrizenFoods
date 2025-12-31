package com.example.krizenfoods.model


data class CartItem(
    val food: Food,
    var quantity: Int = 1
) {
    // Calculate total price for this item
    val totalPrice: Double
        get() = food.price * quantity
}