package org.nico.search.simple.engine

import org.nico.search.simple.engine.helper.SearchCounters._
import org.nico.search.simple.engine.helper.SearchMatchers._
import org.nico.search.simple.engine.helper.SearchScorers._

/**
  * Created by nicolasaubry on 08/12/2016.
  */
object SearchEngines {

  val LooseSearch = new SearchEngine(LoopOnePointMaxPerTerm, LooseMatcher, SimpleScorer)

  val ExactSearch = new SearchEngine(RecursiveOnePointMaxPerTerm, ExactMatcher, SimpleScorer)

}
