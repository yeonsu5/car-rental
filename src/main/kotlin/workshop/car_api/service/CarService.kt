package workshop.car_api.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import workshop.car_api.dto.CarAndCategoryCreateRequestDto
import workshop.car_api.dto.CarCreateRequestDto
import workshop.car_api.dto.CarCreateResponseDto
import workshop.car_api.dto.CarListRequestDto
import workshop.car_api.dto.CarListResponseDto
import workshop.car_api.entity.CarEntity
import workshop.car_api.entity.CarCategoryEntity
import workshop.car_api.entity.CategoryEntity
import workshop.car_api.repository.CarCategoryRepository
import workshop.car_api.repository.CarRepository
import workshop.car_api.repository.CategoryRepository
import java.util.stream.Collectors

@Service
class CarService(
    private val carRepository: CarRepository,
    private val carCategoryRepository: CarCategoryRepository,
    private val categoryRepository: CategoryRepository,
) {

    @Transactional
    fun createCar(dto: CarCreateRequestDto): CarCreateResponseDto {
        // get categories by category ids
        val categoryIds = dto.categoryIds
        val categories = getCategoryByIds(categoryIds)

        // save car
        val savedCarEntity = carRepository.save(
            CarEntity(
                manufacturer = dto.manufacturer,
                model = dto.model,
                year = dto.year,
                isAvailable = dto.isAvailable
            )
        )

        for (category in categories) {
            val carCategoryEntity = CarCategoryEntity(carEntity = savedCarEntity, categoryEntity = category)
            carCategoryRepository.save(carCategoryEntity)
        }

        return CarCreateResponseDto(
            id = savedCarEntity.id,
            manufacturer = savedCarEntity.manufacturer,
            year = savedCarEntity.year,
            isAvailable = savedCarEntity.isAvailable,
            categories = categories.map { it.name }
        )
    }

    @Transactional
    fun createCarWithCategories(dto: CarAndCategoryCreateRequestDto): CarCreateResponseDto {
        // get or create categories by category names
        val categories = dto.categoryNames.map { getOrCreateCategoryByName(it) }

        // save car
        val savedCarEntity = carRepository.save(
            CarEntity(
                manufacturer = dto.manufacturer,
                model = dto.model,
                year = dto.year,
                isAvailable = dto.isAvailable,
            )
        )

        // save car-category relationships
        for (category in categories) {
            val carCategoryEntity = CarCategoryEntity(carEntity = savedCarEntity, categoryEntity = category)
            carCategoryRepository.save(carCategoryEntity)
        }

        return CarCreateResponseDto(
            id = savedCarEntity.id,
            manufacturer = savedCarEntity.manufacturer,
            year = savedCarEntity.year,
            isAvailable = savedCarEntity.isAvailable,
            categories = categories.map { it.name }
        )
    }


    fun getCars(dto: CarListRequestDto): List<CarListResponseDto> {
        return carRepository.getCars(dto)
    }


    private fun getOrCreateCategoryByName(name: String): CategoryEntity {
        val existingCategory = categoryRepository.findByName(name)
        return existingCategory ?: categoryRepository.save(CategoryEntity(name = name))
    }

    private fun getCategoryByIds(categoryIds: List<Long>): List<CategoryEntity> {
        val result = categoryRepository.findAll()
            .stream()
            .filter { category -> categoryIds.contains(category.id) }
            .collect(Collectors.toList()) ?: listOf()

        return result
    }

}