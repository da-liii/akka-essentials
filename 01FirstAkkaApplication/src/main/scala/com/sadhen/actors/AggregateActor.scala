package com.sadhen.actors

import akka.actor.Actor
import com.sadhen.{ReduceData, Result}

import scala.collection.mutable

/**
  * Created by shenda on 16-4-8.
  */
class AggregateActor extends Actor {
  val finalReduceMap = new mutable.HashMap[String, Int]
  def receive: Receive = {
    case ReduceData(reduceDataMap) =>
      aggregateInMemoryReduce(reduceDataMap)
    case Result =>
      sender ! finalReduceMap.toString
  }

  def aggregateInMemoryReduce(reduceList: Map[String, Int]): Unit = {
    for ((key, value) <- reduceList) {
      if (finalReduceMap contains key)
        finalReduceMap(key) = value + finalReduceMap.get(key).get
      else
        finalReduceMap += (key -> value)
    }
  }
}
