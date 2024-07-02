package workshop.car_api.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "cars")
class CarEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val manufacturer: String,

    @Column(nullable = false)
    val model: String,

    @Column(nullable = false)
    val year: String,

    @Column(nullable = false)
    var isAvailable: Boolean = true,

    @OneToMany(mappedBy = "carEntity")
    @JsonIgnore
    val carCategories: List<CarCategoryEntity> = ArrayList(),

    )