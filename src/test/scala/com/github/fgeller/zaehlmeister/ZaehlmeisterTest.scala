package com.github.fgeller.zaehlmeister

import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import akka.testkit.TestActorRef
import akka.actor.ActorSystem
import akka.util.Timeout
import akka.pattern.ask

class ZaehlmeisterTest
    extends FunSpec
    with ShouldMatchers {

  import Zaehlmeister._
  import ExecutionContext.Implicits.global
  implicit val system = ActorSystem("test")
  implicit val timeout = Timeout(1 second)

  def await[T](future: Future[T]): T = Await.result(future, 1 second)
  def awaitAll[T](futures: Seq[Future[T]]): Seq[T] = await(Future.sequence(futures))

  it("when asked for the first time it returns a positive number") {
    val target = TestActorRef(new Zaehlmeister)

    await((target ? IncrementAndGet).mapTo[Long]) should be > 0L
  }

  it("when asked for a series of counts it hands out increasing numbers") {
    val target = TestActorRef(new Zaehlmeister)
    val counts = (1 to 10) map { _ ⇒ (target ? IncrementAndGet).mapTo[Long] }

    awaitAll(counts).sorted.foldLeft(-1L) { (lastCount, count) ⇒
      count should be > lastCount
      count
    }
  }

  it("reads a configured start count on startup")(pending)
  it("performs an optional consistency check of the read start count on startup")(pending)
  it("persists the count to a configured backend periodically if the count changes")(pending)
  it("fails to hand out numbers if the persisted value lags behind the actual value")(pending)

}
