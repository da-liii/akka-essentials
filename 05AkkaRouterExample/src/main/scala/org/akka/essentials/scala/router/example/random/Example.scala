package org.akka.essentials.scala.router.example.random
import akka.actor.ActorSystem
import akka.actor.Props
import akka.routing.RandomPool
import org.akka.essentials.scala.router.example.MsgEchoActor

object Example {
  def main(args: Array[String]): Unit = {
    val _system = ActorSystem("RandomRouterExample")
    val randomRouter = _system.actorOf(RandomPool(5).props(Props[MsgEchoActor]), name = "myRandomRouterActor")
    1 to 10 foreach {
      i => randomRouter ! i
    }
    Thread.sleep(500)
    _system.terminate()
  }
}