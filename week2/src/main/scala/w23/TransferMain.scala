package w23

import akka.actor.{Actor, Props}
import akka.event.LoggingReceive

class TransferMain extends Actor{
  val accountA = context.actorOf(Props[BankAccount], "accountA")
  val accountB = context.actorOf(Props[BankAccount], "accountB")
  val transaction = context.actorOf(Props[WireTransfer], "transfer")

  transaction ! WireTransfer.Transfer(accountA,accountB,10)

  def receive =LoggingReceive{
      case WireTransfer.Done =>
        println("success")
        context.stop(self)
      case WireTransfer.Failed =>
        println("failed")
        context.stop(self)
  }


}