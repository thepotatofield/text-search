package org.nico.search.simple.domain.datasource

import org.nico.search.simple.domain.Model.Dataset

import scala.concurrent.Future

/**
  * Created by nicolasaubry on 09/12/2016.
  */
trait Datasource {

  def loadDatasets: Future[Unit]

  def datasets: List[Dataset]

}