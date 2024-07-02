package workshop.car_api.dto

data class CarListResponseDto(
    val manufacturer: String,
    val model: String,
    val year: String,
    val isAvailable: Boolean,
    val categoryNames: List<String>,
)