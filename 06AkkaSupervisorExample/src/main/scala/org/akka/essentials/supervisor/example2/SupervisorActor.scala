package org.akka.essentials.supervisor.example2
import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.Props
import akka.actor.AllForOneStrategy

class SupervisorActor extends Actor with ActorLogging {
  import akka.actor.SupervisorStrategy._
  import scala.concurrent.duration._

  val workerActor1 = context.actorOf(Props[WorkerActor1], name = "workerActor1")
  val workerActor2 = context.actorOf(Props[WorkerActor2], name = "workerActor2")

  override val supervisorStrategy = AllForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 10 seconds) {

    case _: ArithmeticException => Resume
    case _: NullPointerException => Restart
    case _: IllegalArgumentException => Stop
    case _: Exception => Escalate
  }

  def receive = {
    case result: Result =>
      workerActor1.tell(result, sender)
    case msg: Object =>
      workerActor1 ! msg

  }
}