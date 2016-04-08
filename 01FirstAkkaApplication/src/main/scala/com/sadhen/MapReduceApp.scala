package com.sadhen

import akka.actor.{ActorSystem, Props}
import com.sadhen.actors.MasterActor

import scala.collection.mutable.ArrayBuffer

sealed trait MapReduceMessage
case class WordCount(word: String, count: Int) extends MapReduceMessage
case class MapData(dataList: ArrayBuffer[WordCount]) extends MapReduceMessage
case class ReduceData(reduceDataMap: Map[String, Int]) extends MapReduceMessage
case class Result() extends MapReduceMessage
/**
  * Created by shenda on 16-4-8.
  */
object MapReduceApp extends App {
  val _system = ActorSystem("MapReduceApp")
  val master = _system.actorOf(Props[MasterActor], "master")

  master ! "hello world"
  master ! "hello nihao"
  master ! "a jiao ao"

  Thread.sleep(500)
  master ! Result

  master ! "hello hell"
  Thread.sleep(500)
  master ! Result

  _system.terminate()
}
