package org.akka.essentials.calculator.example4

import org.akka.essentials.calculator._
import akka.actor.ActorSystem
import akka.actor.TypedActor
import akka.actor.TypedProps
import akka.actor.ActorRef
import com.typesafe.config.ConfigFactory

import scala.concurrent.Await
import scala.concurrent.duration._

object CalculatorActorSystem {

  def main(args: Array[String]): Unit = {

    val _system = ActorSystem("TypedActorsExample", ConfigFactory.load().getConfig("TypedActorExample"))

    val calculator: CalculatorInt =
      TypedActor(_system).typedActorOf(TypedProps[Calculator]().withDispatcher("defaultDispatcher"))

    calculator.incrementCount()

    // Invoke the method and wait for result
    val future = calculator.add(14, 6)
    var result = Await.result(future, 5 second)
    println("Result is " + result)

    // Invoke the method and wait for result
    var counterResult = calculator.incrementAndReturn()
    println("Result is " + counterResult.get)

    counterResult = calculator.incrementAndReturn()
    println("Result is " + counterResult.get)

    // Get access to the ActorRef
    val calActor: ActorRef = TypedActor(_system).getActorRefFor(calculator)
    // call actor with a message
    calActor ! "Hi there"

    _system.terminate()
  }
}