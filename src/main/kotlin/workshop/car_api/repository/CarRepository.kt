package workshop.car_api.repository

import workshop.car_api.entity.CarEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepository : JpaRepository<CarEntity, Long>, CarQuerydslRepository {
}
