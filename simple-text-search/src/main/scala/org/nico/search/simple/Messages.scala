package org.nico.search.simple

/**
  * Created by nicolasaubry on 09/12/2016.
  */
object Messages {

  // Infos
  object Infos {

    val Help =
      """
        |--------
        |Commands
        |--------
        |  :quit, :q  - Exit the app
        |  :help, :h  - Display this help
        |  :data  - Display loaded file names
        |  :max   - Set the max amount of results to return
        |  :search, :s <optional-search-type> <search-terms> - Execute a search. Look for the given search terms from the loaded data.
        |
        |--------------
        |Search example
        |--------------
        |  ':search abc def ghi' - search in the loaded data for partial or full match for the terms "abc, def, ghi"
        |  ':search -e abc def ghi' - search in the loaded data for exact match for the terms "abc, def, ghi"
        |
        |------------
        |Search types
        |------------
        |  -loose, -l (default) - Term matching executed against only part or full word
        |  -exact, -e - Term should match exactly full word """.stripMargin

    val Quit = "Bye"

    val LoadedDatasets = (nbDatasets: Int) => s"$nbDatasets files loaded successfully !"

    val SearchTerms = (searchTerms: List[String]) => s"Searching for '${searchTerms.mkString(", ")}' "

    val FileScoreResult = (fileName: String, fileScore: Int) => s"$fileName: $fileScore%"

    val MaxResultUpdate = (max: Int) => s"Max result value set to $max"

    val ElapsedTime = (start: Long, end: Long) => s"elapsed time: ${end - start} ms"

  }

  // Warnings
  object Warns {

    sealed trait Warn {
      def message: String
    }

    case class InvalidFile(fileName: String) extends Warn {
      val message = s"Failed to load $fileName"
    }

    case object InvalidCommand extends Warn {
      val message = "Please provide some valid command. Type :help for assistance"
    }

    case object InvalidSearch extends Warn {
      val message = "Please provide term to look for"
    }

    case object NoLastSearchAvailable extends Warn {
      val message = "No last search available. Try one first :)"
    }

  }

  // Errors.
  // Lead to an exit of the app
  object Errors {

    sealed trait Error {
      def message: String
    }

    case object MissingArg extends Error {
      val message = "Missing argument(s)"
    }

    case object InvalidPath extends Error {
      val message = "Invalid path. Path not found ?, path not a dir ?, dir empty ?"
    }

    case object InvalidData extends Error {
      val message = "No valid data to work with"
    }

  }



}
