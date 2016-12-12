package org.nico.search.simple.domain.datasource

import java.io.File

import org.nico.search.simple.Messages.Errors._

import scala.util.{Failure, Success, Try}

/**
  * Created by nicolasaubry on 09/12/2016.
  */
object DatasourceFactory {

  def getInstance(pathArg: Option[String]): Either[Error, Datasource] = {
    pathArg match {
      case Some(path) => {
        Try(new File(path)) match {
          case Success(dir) if isValidDirectory(dir) => Right(new FileDatasource(dir))
          case _ => Left(InvalidPath)
        }
      }
      case None => Left(MissingArg)
    }
  }

  private def isValidDirectory(dir: File): Boolean = dir.isDirectory && dir.listFiles().exists(_.isFile)

}
