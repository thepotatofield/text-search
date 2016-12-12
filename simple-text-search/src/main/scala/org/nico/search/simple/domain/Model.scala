package org.nico.search.simple.domain

/**
  * Created by nicolasaubry on 09/12/2016.
  */
object Model {

  case class Dataset(id: String, words: List[String])

  case class DatasetScore(id: String, nbMatches: Int, score: Int)

}
