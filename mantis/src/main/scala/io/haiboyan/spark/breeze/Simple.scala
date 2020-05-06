package io.haiboyan.spark.breeze
import breeze.linalg.DenseVector
import breeze.math.Complex

object Simple extends App {
  val i = Complex.i

  // Plus
  println((1 + 2 * i) + ( 2 + 3 * i))
  // Minus
  println((1 + 2 * i) - ( 2 + 3 * i))
  // Division
  println((5 + 10 * i) / (3 - 4 * i))
  // Multiplication
  println((1 + 2 * i) * (-3 + 6 * i))
  println((1 + 5 * i) * (-3 + 2 * i))
  // Negative
  println(-(1 + 2 * i))

  val list = List((5 + 7 * i), (1 + 3 * i), (13 + 17 * i))
  // Sum
  println(list.sum)
  // Product
  println(list.product)
  // Sort
  println(list.sorted)
}
