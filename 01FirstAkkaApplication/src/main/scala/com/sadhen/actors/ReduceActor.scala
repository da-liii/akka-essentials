package com.sadhen.actors

import akka.actor.Actor
import com.sadhen.{MapData, ReduceData, WordCount}

/**
  * Created by shenda on 16-4-8.
  */
class ReduceActor extends Actor {
  def receive: Receive = {
    case MapData(dataList) =>
      sender ! reduce(dataList)
  }

  def reduce(words: IndexedSeq[WordCount]): ReduceData = ReduceData {
    words.foldLeft(Map.empty[String, Int]) { (index, words) =>
      if (index.contains(words.word))
        index + (words.word -> (index.get(words.word).get + 1))
      else
        index + (words.word -> 1)
    }
  }
}
