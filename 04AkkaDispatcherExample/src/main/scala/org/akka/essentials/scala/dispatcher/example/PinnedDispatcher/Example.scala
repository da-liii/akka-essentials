package org.akka.essentials.scala.dispatcher.example.PinnedDispatcher
import org.akka.essentials.scala.dispatcher.MsgEchoActor
import com.typesafe.config.ConfigFactory
import akka.actor.actorRef2Scala
import akka.actor.ActorSystem
import akka.actor.Props
import akka.routing.RoundRobinPool

object Example {
	def main(args: Array[String]): Unit = {}
	val _system = ActorSystem.create("pinned-dispatcher",ConfigFactory.load().getConfig("MyDispatcherExample"))

	val actor = _system.actorOf(RoundRobinPool(5).withDispatcher("pinnedDispatcher").props(Props[MsgEchoActor]))

	0 to 25 foreach {
		i => actor ! i
	}
  Thread.sleep(3000)
	_system.terminate()
}