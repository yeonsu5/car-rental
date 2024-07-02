package workshop.car_api.repository

import workshop.car_api.dto.CarListRequestDto
import workshop.car_api.dto.CarListResponseDto

interface CarQuerydslRepository {

    fun getCars(dto: CarListRequestDto): List<CarListResponseDto>
}