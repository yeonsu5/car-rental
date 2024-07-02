package workshop.car_api.config

import com.querydsl.jpa.JPQLTemplates
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QuerydslConfig(
    private val entityManager: EntityManager,
) {

    @Bean
    fun jpaQueryFactory() = JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager)
    /*
    버전 호환 문제로

    Caused by: java.lang.NoSuchMethodError:
    'java.lang.Object org.hibernate.ScrollableResults.get(int)'

    에러 발생하였다.
    해결 방법 -> QuerydslConfig를 Default모드로 변경 적용한다.
     */

}