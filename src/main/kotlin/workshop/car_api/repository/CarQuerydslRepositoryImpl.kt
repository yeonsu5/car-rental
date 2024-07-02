package workshop.car_api.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import workshop.car_api.dto.CarListRequestDto
import workshop.car_api.dto.CarListResponseDto
import workshop.car_api.entity.QCarEntity

@Repository
class CarQuerydslRepositoryImpl() : CarQuerydslRepository {
    @Autowired
    lateinit var queryFactory: JPAQueryFactory

    override fun getCars(dto: CarListRequestDto): Any
//            List<CarListResponseDto>
    {
        QCarEntity.carEntity
//        queryFactory
//            .select()
    }
}