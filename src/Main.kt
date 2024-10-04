import kotlin.math.sqrt
import kotlin.math.abs

// Класс Точка
data class Point(val x: Double, val y: Double)

// Класс Треугольник
class Triangle(val p1: Point, val p2: Point, val p3: Point) {

    // Функция для вычисления длины стороны треугольника
    private fun distance(p1: Point, p2: Point): Double {
        return sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y))
    }

    // Функция для вычисления площади треугольника по формуле Герона
    private fun area(): Double {
        val a = distance(p1, p2)
        val b = distance(p2, p3)
        val c = distance(p3, p1)
        val s = (a + b + c) / 2
        return sqrt(s * (s - a) * (s - b) * (s - c))
    }

    // Функция для вычисления радиуса описанной окружности
    fun circumradius(): Double {
        val a = distance(p1, p2)
        val b = distance(p2, p3)
        val c = distance(p3, p1)
        val A = area()
        return (a * b * c) / (4 * A)
    }

    // Функция для вычисления координат центра описанной окружности
    fun circumcenter(): Point {
        val x1 = p1.x
        val y1 = p1.y
        val x2 = p2.x
        val y2 = p2.y
        val x3 = p3.x
        val y3 = p3.y

        val D = 2 * (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2))

        val A = x1 * x1 + y1 * y1
        val B = x2 * x2 + y2 * y2
        val C = x3 * x3 + y3 * y3

        val xCenter = (A * (y2 - y3) + B * (y3 - y1) + C * (y1 - y2)) / D
        val yCenter = (A * (x3 - x2) + B * (x1 - x3) + C * (x2 - x1)) / D

        return Point(xCenter, yCenter)
    }
}

// Функция для безопасного ввода числового значения
fun getDoubleInput(prompt: String): Double {
    while (true) {
        print(prompt)
        val input = readLine()
        try {
            return input?.toDouble() ?: throw IllegalArgumentException("Некорректный ввод")
        } catch (e: NumberFormatException) {
            println("Ошибка: введите числовое значение.")
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}

fun main() {
    // Ввод координат вершин треугольника
    println("Введите координаты треугольника:")
    val x1 = getDoubleInput("x1: ")
    val y1 = getDoubleInput("y1: ")
    val x2 = getDoubleInput("x2: ")
    val y2 = getDoubleInput("y2: ")
    val x3 = getDoubleInput("x3: ")
    val y3 = getDoubleInput("y3: ")

    // Создание объекта Triangle
    val triangle = Triangle(Point(x1, y1), Point(x2, y2), Point(x3, y3))

    // Вычисление центра и радиуса окружности
    val circumcenter = triangle.circumcenter()
    val radius = triangle.circumradius()

    // Вывод результатов
    println("Координаты центра описанной окружности: (${circumcenter.x}, ${circumcenter.y})")
    println("Радиус описанной окружности: $radius")
}
