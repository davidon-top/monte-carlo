package top.davidon.montecarlo.main

import java.util.concurrent.atomic.AtomicLong
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

@main def main(iterCount: Long, batchSize: Int): Unit = {
  MonteCarlo(iterCount, batchSize)
}

class MonteCarlo(var iterCount: Long, val batchSize: Int) {
  var sp = AtomicLong(0L)
  var cp = AtomicLong(0L)

  {
    while iterCount > 0 do {
      val bs = if (iterCount < batchSize) iterCount.toInt else batchSize
      val futures = for (i <- 0 until bs) yield calc()
      Future.sequence(futures).map(_ => ())
      iterCount -= bs
      println(s"iterCount = $iterCount")
    }
    val pi = (4.0*cp.get())/sp.get()
    println(s"pi = $pi")
  }

  def calc(): Future[Unit] = Future {
    val px = Math.random()*2-1
    val py = Math.random()*2-1

    val d = px*px + py*py
    if (d < 1) {
      cp.addAndGet(1)
    }
    sp.addAndGet(1)
  }
}
