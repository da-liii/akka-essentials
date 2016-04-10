package org.akka.essentials.scala.dispatcher.example.BalancingDispatcher
import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import akka.actor.Props
import org.akka.essentials.scala.dispatcher.MsgEchoActor
import akka.routing.BalancingPool

object Example {
	def main(args: Array[String]): Unit = {}
	val _system = ActorSystem.create("balancing-dispatcher")

	// BalancingDispatcher is deprecated, we use BalancingPool
	val actor = _system.actorOf(BalancingPool(5).props(Props[MsgEchoActor]))

	0 to 25 foreach {
		i => actor ! i
	}
  Thread.sleep(3000)
	_system.terminate()
}