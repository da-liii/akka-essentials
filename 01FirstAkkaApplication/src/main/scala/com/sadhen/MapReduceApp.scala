package com.sadhen

import akka.actor.{ActorSystem, Props}
import akka.util.Timeout

import scala.concurrent.duration._
import akka.pattern.ask
import com.sadhen.actors.MasterActor

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.Await

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
  implicit val timeout = Timeout(5 seconds)

  master ! "hello world"
  master ! "hello nihao"
  master ! "a jiao ao"

  Thread.sleep(500)
  val future = (master ? Result).mapTo[String]
  val result = Await.result(future, timeout.duration)
  println(result)

  _system.terminate()
}
