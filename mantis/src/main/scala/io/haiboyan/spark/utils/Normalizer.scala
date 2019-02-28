package io.haiboyan.spark.utils

import io.haiboyan.spark.model.Message
import org.apache.spark.ml.feature.{StopWordsRemover, Tokenizer}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, SQLContext, SparkSession}

import scala.annotation.tailrec

object Normalizer {
  def containsOnlyLetters(s: String): Boolean = s.count(java.lang.Character.isLetter).equals(s.length)


  @tailrec
  def replaceLoop(x: String, charsToRemove: List[String]): String = {
    charsToRemove match {
      case Nil => x
      case head :: tail => replaceLoop(x.replace(head, ""), tail)
    }
  }


  def clearWords(words: Seq[String]): Seq[String] = {
    words.map(_.toLowerCase().trim)
      .filterNot(_.isEmpty)
      .filterNot(_.startsWith(">"))
      .filterNot(_.contains("-"))
      .filter(s => containsOnlyLetters(s))
      .map(_.replaceAll("\\<[^>]*>", ""))
      .map(replaceLoop(_, StopWords.forumCharsToRemove))
  }


  def tokenizeAndStopWords(data: DataFrame, spark: SparkSession): DataFrame = {

    val tokenizer = new Tokenizer().setInputCol("body").setOutputCol("words")
    val words = tokenizer.transform(data)

    val stopWordsRemover = new StopWordsRemover().setInputCol("words").setOutputCol("without-stop-word")
    stopWordsRemover.setStopWords(StopWords.allStopWords)

    val withoutStopWords = stopWordsRemover.transform(words)
    cleanForumSpecific(withoutStopWords, spark).toDF
  }


  def cleanForumSpecific(withoutStopWords: DataFrame, spark: SparkSession): Dataset[Message] = {
    import spark.implicits._
    withoutStopWords.map(row => Message(row.getAs[Seq[String]]("without-stop-word"), row.getAs[String]("author"),
      row.getAs[Long]("datestamp"), row.getAs[Long]("message_id"), row.getAs[String]("subject")))
      .filter(m => m.words.nonEmpty)
      .filter(m => !m.author.equalsIgnoreCase("Anonymous user"))
      .map(r => Message(clearWords(r.words).filter(_.length > 2),
        r.author, toMillis(r), r.messageId, r.subject)
      )


//    withoutStopWords.rdd
//      .map(row => Message(row.getAs[Seq[String]]("without-stop-word"), row.getAs[String]("author"),
//        row.getAs[Long]("datestamp"), row.getAs[Long]("message_id"), row.getAs[String]("subject")))
//      .filter(m => m.words.nonEmpty)
//      .filter(m => !m.author.equalsIgnoreCase("Anonymous user"))
//      .map(r => Message(clearWords(r.words).filter(_.length > 2),
//        r.author, toMillis(r), r.messageId, r.subject)
//      )
  }

  def toMillis(r: Message): Long = r.createdTimestamp * 1000
}
