package org.nico.search.simple.engine

import org.nico.search.simple.engine.helper.SearchCounters.CounterFunction
import org.nico.search.simple.engine.helper.SearchMatchers.MatcherFunction
import org.nico.search.simple.engine.helper.SearchScorers.ScorerFunction
import org.nico.search.simple.domain.Model.{Dataset, DatasetScore}

/**
  * Created by nicolasaubry on 08/12/2016.
  */
class SearchEngine(counter: CounterFunction, matcher: MatcherFunction, scorer: ScorerFunction) {

  def execute(searchTerms: List[String], data: List[Dataset], maxResults: Int): List[DatasetScore] = {
    // 1 - count the matches and create the score for each file
    val fileScores = data map { fileData =>
      val nbMatches = counter(searchTerms, fileData.words, matcher)
      DatasetScore(fileData.id, nbMatches, scorer(searchTerms.length, fileData.words.length, nbMatches))
    }

    // 2 - rank and limit amount of results
    fileScores.sortBy(_.score).reverse.take(maxResults)
  }


}
