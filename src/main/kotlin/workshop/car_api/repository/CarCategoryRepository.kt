package workshop.car_api.repository

import org.springframework.data.jpa.repository.JpaRepository
import workshop.car_api.entity.CarCategoryEntity

interface CarCategoryRepository : JpaRepository<CarCategoryEntity, Long> {
}