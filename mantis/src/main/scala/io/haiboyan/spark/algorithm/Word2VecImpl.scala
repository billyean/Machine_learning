package io.haiboyan.spark.algorithm

import io.haiboyan.spark.RunConfig
import io.haiboyan.spark.utils.Normalizer
import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.ml.feature.{Word2Vec, Word2VecModel}
import org.apache.spark.sql.{DataFrame, SparkSession}

case class Word2VectWithModel(dataFrame: DataFrame, model: Word2VecModel)

object Word2VecImpl {

  def apply(sentence: String, spark:SparkSession, runConfig: RunConfig): Vector = {
    import spark.implicits._
    val sentenceDf =
      Normalizer
        .tokenizeAndStopWords(
          spark.sparkContext.parallelize(List(sentence), 2).map(x => InputBody(x)).toDF, spark)
    sentenceDf.show()
    val vectFromSentence = apply(sentenceDf, spark, runConfig).dataFrame.rdd.map(row => row.getAs[Vector]("result")).first()

    println(vectFromSentence)
    vectFromSentence
  }

  def apply(words: List[String], spark:SparkSession, runConfig: RunConfig): Word2VectWithModel = {
    import spark.implicits._
    apply(spark.sparkContext.parallelize(words, 2).repartition(16).map(x => Input(Seq(x))).toDF, spark, runConfig)
  }

  case class InputBody(body: String, author: String = "ignore", datestamp: Long = 0,
                       message_id: Long = 0, subject: String = "Ignore") {
    def apply(body: String) = InputBody(body)
  }

  case class Input(words: Seq[String])


  //dataFrame should have .get("words") = Array[String]
  def apply(dataFrame: DataFrame, spark:SparkSession, runConfig: RunConfig): Word2VectWithModel = {
    // Learn a mapping from words to Vectors.
    val word2Vec = new Word2Vec()
      .setInputCol("words")
      .setOutputCol("result")
      .setVectorSize(runConfig.W2VVectSize)
      .setMinCount(runConfig.W2VMinCount) //minWordFrequency is the minimum number of times a word must appear in the corpus. Here, if it appears less than 5 times, it is not learned.
    val model = word2Vec.fit(dataFrame)
    val result = model.transform(dataFrame)
    result.show(20)
    Word2VectWithModel(result, model)
  }
}

