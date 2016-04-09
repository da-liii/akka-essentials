package org.akka.essentials.scala.router.example2.custom
import org.akka.essentials.scala.router.example.MsgEchoActor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.ActorRef
import akka.routing.{RandomGroup}
import scala.collection.immutable.Iterable

object Example1 {

  def main(args: Array[String]): Unit = {
    val _system = ActorSystem.create("CustomRouteeRouterExample")

    val echoActor1 = _system.actorOf(Props[MsgEchoActor], "a1")
    val echoActor2 = _system.actorOf(Props[MsgEchoActor], "a2")
    val echoActor3 = _system.actorOf(Props[MsgEchoActor], "a3")

    val routees = Iterable[ActorRef](echoActor1, echoActor2, echoActor3) map {x => x.path.toStringWithoutAddress}

    val randomRouter = _system.actorOf(RandomGroup(routees).props(), "randomRouter")
    1 to 10 foreach {
      i => randomRouter ! i
    }
    Thread.sleep(500)
    _system.terminate()
  }
}