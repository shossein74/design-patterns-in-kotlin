package com.hossein.dev.behavioral

interface Iterator<T> {
    fun hasNext(): Boolean
    fun next(): T
}

data class Car(
    val brand: String,
    val model: String,
)

class CarIterator(
    private val cars: List<Car>,
): Iterator<Car> {
    private var index = 0

    override fun hasNext(): Boolean {
        return index < cars.size
    }

    override fun next(): Car {
        return cars[index++]
    }

}

interface CarAggregate {
    fun createIterator(): Iterator<Car>
}

class CarIteratorProvider(
    private val cars: List<Car>,
) : CarAggregate {
    override fun createIterator(): Iterator<Car> {
        return CarIterator(cars)
    }
}

fun main() {
    val cars = listOf(
        Car("Toyota", "Camry"),
        Car("Ford", "Mustang"),
        Car("Mercedes", "S500"),
        Car("Honda", "Civic"),
    )

    val carIterator = CarIteratorProvider(cars).createIterator()

    while (carIterator.hasNext()) {
        val car = carIterator.next()
        println("${car.brand} -> ${car.model}")
    }
}