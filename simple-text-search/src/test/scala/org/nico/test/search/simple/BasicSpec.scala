package org.nico.test.search.simple


import org.nico.search.simple.app.Controller
import org.nico.search.simple.domain.datasource.{Datasource, DatasourceFactory}
import org.scalatest._
import org.nico.search.simple.engine.SearchEngines._

import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by nicolasaubry on 12/12/2016.
  */
class BasicSpec extends FlatSpec with Matchers {

  val currentPath =  System.getProperty("user.dir")
  val sampleDirPath = s"$currentPath/simple-text-search/src/test/resources/samples"
  val sampleFileName = "lorem_20.txt"
  lazy val sampleDatasource: Datasource = {
    DatasourceFactory.getInstance(Some(sampleDirPath)).fold(
      error => fail(error.message),
      datasource => datasource
    )
  }
  val searchTerms = List("am", "at", "non", "xx")
  val expectedLooseResult = 75
  val expectedExactResult = 25


  s"Loading files from $sampleDirPath" should s"return a single dataset named $sampleFileName and containing 20 words" in {
    Await.ready(sampleDatasource.loadDatasets, 5 seconds)
    sampleDatasource.datasets.size shouldBe 1
    sampleDatasource.datasets.head.id shouldBe sampleFileName
    sampleDatasource.datasets.head.words.size  shouldBe 20
  }


  s"Searching for $searchTerms with LooseSearch engine" should s"result in $expectedLooseResult% score" in {
    Await.ready(sampleDatasource.loadDatasets, 5 seconds)
    val controller = new Controller(sampleDatasource)
    val scores = controller.executeSearch(LooseSearch, searchTerms)
    scores should not be empty
    scores.head.id shouldBe sampleFileName
    scores.head.score shouldBe expectedLooseResult
  }


  s"Searching for $searchTerms with ExactSearch engine" should s"result in $expectedExactResult% score" in {
    Await.ready(sampleDatasource.loadDatasets, 5 seconds)
    val controller = new Controller(sampleDatasource)
    val scores = controller.executeSearch(ExactSearch, searchTerms)
    scores should not be empty
    scores.head.id shouldBe sampleFileName
    scores.head.score shouldBe expectedExactResult
  }

}
