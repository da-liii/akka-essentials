package com.sadhen.actors

import akka.actor.Actor
import com.sadhen.{MapData, WordCount}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by shenda on 16-4-8.
  */
class MapActor extends Actor {
  val STOP_WORDS_LIST = List("a", "am")
  val defaultCount: Int = 1

  def receive : Receive = {
    case message: String =>
      sender ! evaluateExpression(message)
  }

  def evaluateExpression(line: String) : MapData = MapData {
    // logic to map the words in the sentence
    line.split("""\s+""").foldLeft(ArrayBuffer.empty[WordCount]) {
      (index, word) =>
        if (!STOP_WORDS_LIST.contains(word.toLowerCase))
          index += new WordCount(word.toLowerCase, 1)
        else
          index
    }
  }
}
