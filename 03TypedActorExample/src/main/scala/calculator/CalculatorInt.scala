package org.akka.essentials.calculator
import akka.actor.TypedActor.Receiver

import scala.concurrent.Future


trait CalculatorInt extends Receiver  {

	def add(first: Int, second: Int): Future[Int]

	def subtract(first: Int, second: Int): Future[Int]

	def incrementCount(): Unit

	def incrementAndReturn(): Option[Int]
}
