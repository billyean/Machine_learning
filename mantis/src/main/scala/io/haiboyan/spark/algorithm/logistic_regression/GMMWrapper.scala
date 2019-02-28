package io.haiboyan.spark.algorithm.logistic_regression

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.clustering.{GaussianMixture, GaussianMixtureModel}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.joda.time.{DateTime, DateTimeZone}

object GMMWrapper {
  def apply(df: DataFrame, author: String, spark: SparkSession, numberOfClusters: Int = 2): GaussianMixtureModel = {
    import spark.implicits._
    val timeOfPostsForAuthor = df.rdd.map(row => (row.getAs[String]("author"), row.getAs[Long]("createdTimestamp")))
      .filter(_._1 == author)
      .map(r => (r._1, new DateTime(r._2, DateTimeZone.UTC).getHourOfDay))

    val parsedData = timeOfPostsForAuthor.map(elem => Vectors.dense(elem._2)).cache()
    // Cluster the data into classes using GaussianMixture
    val gmm = new GaussianMixture()
      .setK(numberOfClusters) //experiment with number of gaussian
        .run(parsedData)
    gmm
  }
}
