package w23

import akka.actor.{Actor, ActorRef}
import akka.event.LoggingReceive

object WireTransfer{
  case class Transfer(from:ActorRef, to:ActorRef, amount: Int)
  case object Done
  case object Failed
}

class WireTransfer extends Actor{
  import  WireTransfer._
  def receive = LoggingReceive{
    case Transfer(from:ActorRef, to:ActorRef, amount: Int) =>
      from ! BankAccount.Withdraw(amount)
      val customer = sender()
      context.become(LoggingReceive {
        case BankAccount.Done =>
          to ! BankAccount.Deposit(amount)
          context.become(LoggingReceive{
            case BankAccount.Done =>
              customer ! Done
              context.stop(self)
          })
        case BankAccount.Failed =>
          customer ! Failed
          context.stop(self)
      } )
  }



}