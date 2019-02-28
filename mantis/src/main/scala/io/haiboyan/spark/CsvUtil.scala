package io.haiboyan.spark

import java.nio.file.Files.newBufferedWriter
import java.nio.file.Paths.get
import io.haiboyan.spark.algorithm.logistic_regression.LogisticRegressionWrapper.Statistic
import org.joda.time.DateTime

object CsvUtil {
  def writeAsCsv(result: Map[String, Statistic], testCasePrefix: String) = {
    val fileName = s"logistic_regression_$testCasePrefix _${DateTime.now().getMillis}.csv"
    val header = "author,precision,recall,falsePositiveRate,truePositiveRate,numberOfPosts,areaUnderROC,threshold\n"
    val content = result.map(line => s"${line._2.author},${line._2.precision}" +
      s",${line._2.recall},${line._2.falsePositiveRate},${line._2.truePositiveRate},${line._2.numberOfPosts}" +
      s",${line._2.areaUnderROC},${line._2.threshold}\n").reduce((a, b) => a + b)

    newBufferedWriter(get(fileName)).write(header + content)
  }
}
