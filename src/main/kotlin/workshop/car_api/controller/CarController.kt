package workshop.car_api.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import workshop.car_api.dto.CarCreateRequestDto
import workshop.car_api.dto.CarListRequestDto
import workshop.car_api.service.CarService

@RestController
@RequestMapping("/car")
class CarController(
    private val carService: CarService,
) {
    // 등록 API
    @PostMapping
    fun createCar(@RequestBody dto: CarCreateRequestDto) : ResponseEntity<*> {
        val createdCar = carService.createCar(dto)

        return ResponseEntity.ok(createdCar)
    }

    // 조회 -> 파라미터 없으면 전체조회
    @GetMapping
    fun getCars(@ModelAttribute dto: CarListRequestDto) : ResponseEntity<*> {
        carService.getCars(dto)

        return ResponseEntity.ok("")
    }

}