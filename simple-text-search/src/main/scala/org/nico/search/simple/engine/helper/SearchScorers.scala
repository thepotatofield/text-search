package org.nico.search.simple.engine.helper

/**
  * Created by nicolasaubry on 08/12/2016.
  */
private[engine] object SearchScorers {

  type ScorerFunction = (Int, Int, Int) => Int

  val SimpleScorer: ScorerFunction = (nbTerms, nbWords, nbMatches) => {
    if (nbTerms == nbMatches) 100
    else nbMatches * (100 / nbTerms)
  }

}
