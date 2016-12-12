package org.nico.search.simple.app

import org.nico.search.simple.Constants._
import org.nico.search.simple.Messages.Warns._
import org.nico.search.simple.Messages.Infos._
import org.nico.search.simple.app.Console.Command
import org.nico.search.simple.domain.Model.DatasetScore
import org.nico.search.simple.domain.datasource.Datasource
import org.nico.search.simple.engine.SearchEngine
import org.nico.search.simple.engine.SearchEngines._

import scala.util.Try


/**
  * Created by nicolasaubry on 09/12/2016.
  */
class Controller(datasource: Datasource) {

  private var maxResults: Int = DefaultMaxResult

  def prompt(): Unit = {
    Console.input.fold(invalid)(execute)
  }

  private def execute(command: Command): Unit = {
    val args = command.args
    command.cmd match {
      case ":quit" | ":q"   => Main.stop(0, None)
      case ":help" | ":h"   => help
      case ":data"          => data
      case ":max"           => max(args.headOption)
      case ":search" | ":s" => search(args)
      case _                => invalid
    }
  }

  private def invalid: Unit = {
    Console.warn(InvalidCommand)
    prompt()
  }

  private def help: Unit = {
    Console.info(Help)
    prompt()
  }

  private def data: Unit = {
    Console.info(datasource.datasets.map(_.id).mkString("\n"))
    prompt()
  }

  private def search(args: List[String]): Unit = {
    args match {
      case head :: tail => head match {
        case "-loose" | "-l" => executeSearch(LooseSearch, tail)
        case "-exact" | "-e" => executeSearch(ExactSearch, tail)
        case _ => executeSearch(LooseSearch, args)
      }
      case _ => Console.warn(InvalidSearch)
    }
    prompt()
  }

  def executeSearch(searchEngine: SearchEngine, searchTerms: List[String]): List[DatasetScore] = {
    Console.info(SearchTerms(searchTerms))
    val scores = searchEngine.execute(searchTerms, datasource.datasets, maxResults)
    scores.foreach(fs => Console.info(FileScoreResult(fs.id, fs.score)))
    scores
  }

  private def max(arg: Option[String]): Unit = {
    Try(arg.map(_.toInt)).toOption.flatten.foreach(max => if(max > 0) {
      Console.info(MaxResultUpdate(max))
      maxResults = max
    })
    prompt()
  }


}
