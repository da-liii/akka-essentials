package org.akka.essentials.scala.router.example.broadcast
import akka.actor.ActorSystem
import akka.actor.Props
import akka.routing.BroadcastPool
import org.akka.essentials.scala.router.example.MsgEchoActor

object Example {

  def main(args: Array[String]): Unit = {
    val _system = ActorSystem("BroadcastRouterExample")
    val broadcastRouter = _system.actorOf(BroadcastPool(5).props(Props[MsgEchoActor]), name = "myBroadcastRouterActor")
    1 to 1 foreach {
      i => broadcastRouter ! i
    }
    _system.terminate()
  }

}