package workshop.car_api.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "categories")
class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val name: String,

    @OneToMany(mappedBy = "categoryEntity")
    val carCategories: List<CarCategoryEntity> = ArrayList()
)