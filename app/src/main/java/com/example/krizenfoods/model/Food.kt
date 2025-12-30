//package com.example.krizenfoods.model
//
//
//
//data class Food(
//    val id: String = "",
//    val name: String = "",
//    val description: String = "",
//    val price: Double = 0.0,
//    val category: String = "",
//    val imageUrl: String = "",
//    val isAvailable: Boolean = true,
//    val createdAt: Long = System.currentTimeMillis(),
//    val updatedAt: Long = System.currentTimeMillis()
//)
//
//// Categories for the food items
//object FoodCategory {
//    const val APPETIZER = "Appetizer"
//    const val MAIN_COURSE = "Main Course"
//    const val DESSERT = "Dessert"
//    const val BEVERAGE = "Beverage"
//    const val SNACK = "Snack"
//
//    fun getAllCategories() = listOf(
//        APPETIZER,
//        MAIN_COURSE,
//        DESSERT,
//        BEVERAGE,
//        SNACK
//    )
//}


package com.example.krizenfoods.model

data class Food(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val category: String = "",
    val imageUrl: String = "",
    val isAvailable: Boolean = true,
    val createdAt: Long = 0L,  // ✅ Changed to 0L
    val updatedAt: Long = 0L   // ✅ Changed to 0L
)

// Categories for the food items
object FoodCategory {
    const val APPETIZER = "Appetizer"
    const val MAIN_COURSE = "Main Course"
    const val DESSERT = "Dessert"
    const val BEVERAGE = "Beverage"
    const val SNACK = "Snack"

    fun getAllCategories() = listOf(
        APPETIZER,
        MAIN_COURSE,
        DESSERT,
        BEVERAGE,
        SNACK
    )
}