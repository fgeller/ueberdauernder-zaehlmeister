package com.github.fgeller.zaehlmeister

import akka.actor.Actor

object Zaehlmeister {
  case object CountRequest
}

class Zaehlmeister extends Actor {

  def receive = { case _ ⇒ ??? }

}
