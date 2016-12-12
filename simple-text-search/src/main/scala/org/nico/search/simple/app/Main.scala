package org.nico.search.simple.app

import org.nico.search.simple.Messages.Infos._
import org.nico.search.simple.Messages.Errors._
import org.nico.search.simple.domain.datasource.{Datasource, DatasourceFactory}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by nicolasaubry on 09/12/2016.
  */
object Main extends App {

  init()

  private def init(): Unit = {
    DatasourceFactory.getInstance(args.headOption).fold(
      error => stop(1, Some(error)),
      datasource => start(datasource)
    )
  }

  private def start(datasource: Datasource): Unit = {

    // never block is the rule of thumb but we are initializing the app here
    // that's just for discussing the role of futures...
    Await.ready(datasource.loadDatasets, 5 seconds)

    // print data loading info
    Console.info(LoadedDatasets(datasource.datasets.size))

    // start the controller (if any data)
    datasource.datasets.nonEmpty match {
      case true => new Controller(datasource).prompt()
      case false => stop(1, Some(InvalidData))
    }
  }


  def stop(status: Int, error: Option[Error]): Unit = {
    error.foreach(Console.error)
    Console.info(Quit)
    sys.exit(status)
  }

}
