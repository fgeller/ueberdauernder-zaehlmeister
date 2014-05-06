package com.github.fgeller.zaehlmeister

import akka.actor.Actor

object Zaehlmeister {
  case object IncrementAndGet
}

class Zaehlmeister extends Actor {

  var count: Long = 1L

  def receive = {
    case _ ⇒
      count += 1
      sender ! count
 }

}
