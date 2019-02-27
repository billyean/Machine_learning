package io.haiboyan.spark.model

case class Message(words: Seq[String], author: String, createdTimestamp: Long, messageId: Long, subject: String)
