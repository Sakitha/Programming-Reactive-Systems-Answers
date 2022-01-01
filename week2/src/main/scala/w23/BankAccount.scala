package w23

import akka.actor.Actor
import akka.event.LoggingReceive


object BankAccount{
  case class Deposit(n:Int)
  case class Withdraw(n:Int)
  case object Done
  case object Failed
}

class BankAccount extends Actor {
  import BankAccount._
  var balance = 0
  def receive: Receive = LoggingReceive {
    case Deposit(n) =>
      balance +=  n
      sender() ! Done
    case Withdraw(n) if balance <= n =>
      balance -= n
      sender() ! Done
    case _ => sender() ! Failed
  }

}
