package conctest

import java.util.concurrent.Executors

object ex extends App{
  val pool = Executors.newFixedThreadPool(10)

  pool.execute(()=>{
    Thread.sleep(2000)
    println("almost done")
    Thread.sleep(1000)
    println("Done after 2 second")
  })

  pool.execute(() => {
    Thread.sleep(1000)
    println("Done after 1 second")
  })

}

