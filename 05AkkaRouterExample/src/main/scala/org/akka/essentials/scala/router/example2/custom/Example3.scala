package org.akka.essentials.scala.router.example2.custom
import akka.routing.{DefaultResizer, RandomPool}
import akka.actor.ActorSystem
import akka.actor.Props
import org.akka.essentials.scala.router.example.MsgEchoActor

object Example3 {
  def main(args: Array[String]): Unit = {
    val _system = ActorSystem.create("CustomRouteeRouterExample")

    val resizer = DefaultResizer(lowerBound = 2, upperBound = 15)

    val randomRouter = _system.actorOf(RandomPool(5).withResizer(resizer).props(Props[MsgEchoActor]))
    1 to 100 foreach {
      i => randomRouter ! i
    }
    Thread.sleep(500)
    _system.terminate()
  }
}	