package org.nico.search.simple.app

import org.nico.search.simple.Messages.Warns.Warn
import org.nico.search.simple.Messages.Errors.Error
import org.nico.search.simple.Constants._

/**
  * Created by nicolasaubry on 09/12/2016.
  */
object Console {

  case class Command(cmd: String, args: List[String])

  def input: Option[Command] = {
    val in = scala.io.StdIn.readLine("cmd> ").trim
    val inData = in.split(WordSeparator).toList
    inData.headOption.map(cmd => Command(cmd, inData.tail))
  }

  def info(message: String) = println(s"info> $message")

  def warn(warn: Warn) = println(s"warn> ${warn.message}")

  def error(error: Error) = println(s"error> ${error.message}")

}
