package io.haiboyan.spark

import io.haiboyan.spark.algorithm.logistic_regression.LogisticRegressionWrapper
import io.haiboyan.spark.model.Message
import io.haiboyan.spark.utils.Normalizer
import org.apache.spark.SparkConf
import org.apache.spark.ml.feature.Word2Vec
import org.apache.spark.sql.{DataFrame, SparkSession}

class DataLoader(spark: SparkSession, runConfig: RunConfig) {
  def load() = {
    val df_sql = spark.read
      .format("jdbc")
      .option("url", "jdbc:mysql://localhost/forum")
      .option("dbtable", "b_messages")
      .option("user", "billyean")
      .option("password", "guiying")
      .load

    val cleanedDf = Normalizer.tokenizeAndStopWords(df_sql, spark)
    val topAuthors = getTopAuthorsFromNumberOfPosts(cleanedDf, 1)
    val word2Vec = new Word2Vec()
      .setInputCol("words")
      .setOutputCol("result")
      .setVectorSize(200)
      .setMinCount(0)
    val model = word2Vec.fit(topAuthors)
    LogisticRegressionWrapper(model.getVectors, spark, runConfig)
  }

  def getTopAuthorsFromNumberOfPosts(df: DataFrame, numberOfPosts: Int): DataFrame = {
    import spark.implicits._

    df.rdd.map(row => (row.getAs[String]("author"),
      row.getAs[Seq[String]]("words"), row.getAs[Long]("createdTimestamp"), row.getAs[Long]("messageId"), row.getAs[String]("subject")))
      .groupBy(_._1)
      .filter(x => x._2.size > numberOfPosts)
      .map(x => x._2)
      .sortBy(_.size, ascending = false)
      .flatMap(_.map(x => Message(x._2, x._1, x._3, x._4, x._5)))
      .filter(_.words.nonEmpty)
      .toDF()
  }
}

object DataLoader extends App {
  val conf = new SparkConf()
  conf.setMaster("local")
  val runConfig = RunConfig(2, 1, 200)
  val spark = SparkSession
    .builder().config(conf)
    .appName("Spark DataLoader")
    .getOrCreate()

  new DataLoader(spark, runConfig).load
}
