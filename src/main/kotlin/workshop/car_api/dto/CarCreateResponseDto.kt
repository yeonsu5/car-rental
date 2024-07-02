package workshop.car_api.dto

data class CarCreateResponseDto (
    val id: Long,
    val manufacturer: String,
    val year: String,
    val isAvailable: Boolean,
    val categories: List<String>
)