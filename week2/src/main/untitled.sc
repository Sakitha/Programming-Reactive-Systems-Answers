
/*
trait Recieve{
  type Receive = PartialFunction[Any, Unit]
}


trait ActorContext extends Recieve {

  def become(behavior: Receive): Unit
  def unbecome(): Unit
}

trait Actor extends Recieve{

  implicit val self: ActorRef
  implicit val context: ActorContext
  def sender : ActorRef
  def receive : Receive
}

abstract  class ActorRef {
  def !(msg: Any) (implicit sender : ActorRef) : Unit
}
*/


import java.util.concurrent.Executors

class ex extends App{
  val pool = Executors.newFixedThreadPool(10)

  pool.execute(()=>{
    Thread.sleep(1000)
    println("almost done")
    Thread.sleep(1000)
    println("Done after 2 second")
  })

  pool.execute(() => {
    Thread.sleep(1000)
    println("Done after 1 second")
  })

}
