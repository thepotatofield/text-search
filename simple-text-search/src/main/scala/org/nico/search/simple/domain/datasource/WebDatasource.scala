package org.nico.search.simple.domain.datasource

import org.nico.search.simple.domain.Model.Dataset

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by nicolasaubry on 11/12/2016.
  */
class WebDatasource(url: String) extends Datasource {

  private var data: List[Dataset] = _

  override lazy val datasets: List[Dataset] = data

  override def loadDatasets: Future[Unit] = {
//    import scala.io.Source
//    val html = Source.fromURL("http://google.com")
//    val s = html.mkString
//    println(s)
    Future(())
  }

}