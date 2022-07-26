import kotlin.random.Random

fun main() {
    val num = "AaakbKJJKJHjh".numCap()
    println(num)
    println("AaakbKJJKJHjh".numCapitals)
    println(listOf(1,2,3,4,5).filterBy { it > 2 })
    listOf(1,2,3,4,5,6)
        .filterBy { it > 3 } //этот метод возвращает list
        .dropLast(1)
        .map { it * 2 } //смотреть сигнатуру метода

    listOf(1,2,3,4,5,6)
        .asSequence() //в этом сл все операторы будут вызываться последовательно для каждого элемента коллекции
        .filter {   //без asSequence сначала вся коллекция прогонится через фильтр, потом все оставшееся через find
            println("Filter: $it") //используя asSeq, можно уменьшить число проходов по коллекции
            it > 3
        }
        .find {
            println("Find: $it")
            it == 5
        }

    val rectangle = Rectangle(3.0, 4.0)
    val square = Square(5.0)
    val triangle = Triangle(6.0, 7.0)
    val circle = Circle(3.0)
    rectangle.figureType()
    println(rectangle.area)
    square.figureType()
    println(square.area)
    triangle.figureType()
    println(triangle.area)
    circle.figureType()
    println(circle.area)
    println(Rectangle(1.0,2.0) == Rectangle(1.0,2.0))

}

fun String.numCap() = this.count { it.isUpperCase() }

val String.numCapitals: Int
    get() = count { it.isUpperCase() }

fun List<Int>.filterBy(lam: (Int) -> Boolean): List<Int> { //более короткий вариант
    filter(lam) //так можно, потому что у filter такая же сигнатура, как у нашей лямбды
    return filter { lam(it) } //или так, более понятно
}

fun List<Int>.filterByLambda(lam: (Int) -> Boolean): List<Int> {
    val list = mutableListOf<Int>()

    for (item in this) { //более длинный вариант
        if (lam(item)) {
            list.add(item)
        }
    }

    this.forEach { item -> //вариант получше, кстати, this здесь можно вообще не писать
        if (lam(item)) {
            list.add(item)
        }
    }
    return list
}

interface Squarable {
//    fun squareFig(): Double
    val area: Double
}
abstract class Figure() : Squarable

data class Rectangle(private val x: Double, private val y: Double) : Figure() {
//    override fun squareFig(): Double {
//        return x * y
//    }
    val value = Random.nextInt()
    override val area: Double
        get() = x * y
}
data class Square(private val x: Double) : Figure() {
    //    override fun squareFig(): Double {
//        return x * x
//    }
    override val area: Double
        get() = x * x
}
data class Triangle(private val x: Double, private val y: Double) : Figure() {
//    override fun squareFig(): Double {
//        return (x * y) / 2
//    }
    override val area: Double
        get() = x * y
}
data class Circle(private val r: Double) : Figure() {
    override val area: Double
        get() = r * r * 3.1415
}

fun Figure.figureType() {
    when(this) {
        is Rectangle -> println("Rectangle")
        is Square -> println("Square")
        is Triangle -> println("Triangle")
        else -> println("Unknown figure")
    }
}

data class UserOld(val name: String, val surname: String, val age: Int, val userType: UserType) {

}
enum class UserType {
    STUDENT, TEACHER
}
sealed class User {
    abstract val name: String
    abstract val surname: String
    abstract val age: Int

    val isStudent: Boolean
        get() = this is Student
    fun isTeacher(): Boolean {
        return !isStudent
    }

    data class Student(
        override val name: String,
        override val surname: String,
        override val age: Int,
        val grade: List<Int>) : User() {

    }
    data class Teacher(
        override val name: String,
        override val surname: String,
        override val age: Int,
        val articles: List<String>) : User()
}

fun check(user: User) {
    when (user) {
        is User.Student -> TODO()
        is User.Teacher -> TODO()
        else -> TODO()
    }
}