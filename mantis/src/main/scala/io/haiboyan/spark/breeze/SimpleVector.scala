package io.haiboyan.spark.breeze

import breeze.linalg.DenseVector

object SimpleVector {
  val v = DenseVector(2f, 0f, 3f, 2f, -1f)
  v.update(3, 6f)
  println(v)
}
