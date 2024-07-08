package workshop.car_api.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.group.GroupBy
import com.querydsl.core.group.GroupBy.groupBy
import com.querydsl.core.group.GroupBy.list
import com.querydsl.core.types.Projections

import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.Specification.where
import org.springframework.stereotype.Repository
import workshop.car_api.dto.CarListRequestDto
import workshop.car_api.dto.CarListResponseDto
import workshop.car_api.dto.CategoryListResponseDto
import workshop.car_api.entity.QCarCategoryEntity.*
import workshop.car_api.entity.QCarEntity.*
import workshop.car_api.entity.QCategoryEntity.*

@Repository
class CarQuerydslRepositoryImpl() : CarQuerydslRepository {
    @Autowired
    lateinit var queryFactory: JPAQueryFactory

    override fun getCars(dto: CarListRequestDto): List<CarListResponseDto> {

        val builder = BooleanBuilder()

        dto.manufacturer?.let {
            builder.and(carEntity.manufacturer.eq(dto.manufacturer))
        }
        dto.model?.let {
            builder.and(carEntity.model.eq(dto.model))
        }
        dto.year?.let {
            builder.and(carEntity.year.like("${carEntity.year}%"))
        }
        dto.isAvailable?.let {
            builder.and(carEntity.isAvailable.eq(dto.isAvailable))
        }
        dto.categoryId?.let {
            builder.and(carCategoryEntity.categoryEntity.id.eq(dto.categoryId))
        }


        // 전체 조회
        val result = queryFactory
            .from(carEntity)
            .join(carEntity.carCategories, carCategoryEntity)
            .join(carCategoryEntity.categoryEntity, categoryEntity)
            .where(builder)
            .transform(
                groupBy(carEntity.id).list(
                    Projections.constructor(
                        CarListResponseDto::class.java,
                        carEntity.manufacturer,
                        carEntity.model,
                        carEntity.year,
                        carEntity.isAvailable,
                        list(
                            Projections.constructor(
                                CategoryListResponseDto::class.java, categoryEntity.name
                            ).skipNulls() // null 값은 출력 제외
                        )
                    )
                )
            ) ?: listOf()

        return result


    }
}