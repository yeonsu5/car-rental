package workshop.car_api.dto

data class CarAndCategoryCreateRequestDto (
    val manufacturer: String,
    val model: String,
    val year: String,
    val isAvailable: Boolean,
    val categoryNames: List<String> = listOf(),
)
