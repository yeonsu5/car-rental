package workshop.car_api.dto

data class CarListRequestDto (
    val manufacturer: String?,
    val model: String?,
    val year: String?,
    val isAvailable: Boolean?,
    val categoryId: Long?,
)