package workshop.car_api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import workshop.car_api.entity.CategoryEntity
import java.util.*

@Repository
interface CategoryRepository : JpaRepository<CategoryEntity, Long> {

    fun findByName(name: String): CategoryEntity?
}
