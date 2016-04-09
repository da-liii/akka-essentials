package com.sadhen.actors

import akka.actor.{Actor, Props}
import akka.routing.RoundRobinPool
import com.sadhen.{MapData, ReduceData, Result}

/**
  * Created by shenda on 16-4-8.
  */
class MasterActor extends Actor {
  val mapActor = context.actorOf(RoundRobinPool(5).props(Props[MapActor]), "map")
  val reduceActor = context.actorOf(RoundRobinPool(5).props(Props[ReduceActor]), "reduce")
  val aggregateActor = context.actorOf(Props[AggregateActor], "aggregate")

  def receive: Receive = {
    case line: String => mapActor ! line
    case mapData: MapData => reduceActor ! mapData
    case reduceData: ReduceData => aggregateActor ! reduceData
    case Result => aggregateActor forward Result
  }
}
