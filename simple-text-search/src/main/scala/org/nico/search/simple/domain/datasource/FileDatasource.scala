package org.nico.search.simple.domain.datasource

import java.io.File

import org.nico.search.simple.Constants._
import org.nico.search.simple.Messages.Warns._
import org.nico.search.simple.app.Console
import org.nico.search.simple.domain.Model.Dataset

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.io.{Codec, Source}
import scala.util.{Failure, Success, Try}

/**
  * Created by nicolasaubry on 09/12/2016.
  */
class FileDatasource(val directory: File) extends Datasource {

  private var data: List[Dataset] = _

  override lazy val datasets: List[Dataset] = data

  override def loadDatasets: Future[Unit] = {
    // read & parse files from directory
    val futureResults = Future.sequence(
      directory
        .listFiles() // get list of files in that dir
        .filter(_.isFile) // filter out directories
        .map(readFile).toList // read each file
    )

    // partition results, set datasets and print warnings if any
    futureResults map { results =>
      val (datasets, warns) = results.partition(_.isRight)
      data = datasets.map(_.right.get)
      warns.map(_.left.get).foreach(Console.warn)
    }
  }

  private def readFile(file: File): Future[Either[Warn, Dataset]] = {
    Future {
      Try {
        val words = Source.fromFile(file)(Codec.UTF8)
          .getLines // read by lines
          .map(parseFileLine) // parse each line as List[String]
          .flatten // simple way to transform from List[List[String]] to List[String]
        Dataset(file.getName, words.toList)
      } match {
        case Success(fileData) => Right(fileData)
        case Failure(_) => Left(InvalidFile(file.getName))
      }
    }
  }

  private def parseFileLine(line: String): List[String] = {
    line
      // 1 - replace non alpha numeric chars by WordSeparator (e.g. simple-text-search -> simple text search)
      .map(c => if (c.isLetterOrDigit) c else WordSeparator)
      // 2 - split the words based on WordSeparator (i.e. white space)
      .split(WordSeparator)
      // 3 - normalize each word (lowercase and trim)
      .map(_.toLowerCase.trim)
      // 4 - filter info empty words
      .filterNot(_.isEmpty)
      // 5 - return an immutable List
      .toList
  }



}
