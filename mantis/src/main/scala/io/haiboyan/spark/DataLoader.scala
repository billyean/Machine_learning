package io.haiboyan.spark

import io.haiboyan.spark.model.Message
import io.haiboyan.spark.utils.Normalizer
import org.apache.spark.ml.feature.Word2Vec
import org.apache.spark.sql.{DataFrame, SparkSession}

class DataLoader(spark: SparkSession) {
//  val spark = SparkSession
//    .builder()
//    .appName("Spark DataLoader")
//    .getOrCreate()

  def load() = {
    val df_sql = spark.read
      .format("jdbc")
      .option("url", "jdbc:mysql://localhost/forum")
      .option("dbtable", "b_messages")
      .option("user", "")
      .option("password", "")
      .load

    val cleanedDf = Normalizer.tokenizeAndStopWords(df_sql, spark)
    val topAuthors = getTopAuthorsFromNumberOfPosts(cleanedDf, 1)
    val model = new Word2Vec().fit(df_sql)
  }

  def getTopAuthorsFromNumberOfPosts(df: DataFrame, numberOfPosts: Int): DataFrame = {
    import spark.implicits._
    df.rdd.map(row => (row.getAs[String]("author"),
      row.getAs[Seq[String]]("words"), row.getAs[Long]("createdTimestamp"), row.getAs[Long]("messageId"), row.getAs[String]("subject")))
      .groupBy(_._1)
      .map(x => (x._1, x._2, x._2.size))
      .sortBy(_._3, ascending = false)
      .filter(x => x._3 > numberOfPosts)
      .map(_._2.map(x => Message(x._2, x._1, x._3, x._4, x._5)))
      .flatMap(identity)
      .filter(_.words.nonEmpty)
      .toDF()
  }
}
