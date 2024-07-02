package workshop.car_api.dto

data class CarCreateRequestDto (
    val manufacturer: String,
    val model: String,
    val year: String,
    val isAvailable: Boolean,
    val categoryIds: List<Long> = listOf(),
)