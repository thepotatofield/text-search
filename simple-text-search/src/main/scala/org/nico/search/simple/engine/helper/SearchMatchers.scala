package org.nico.search.simple.engine.helper

/**
  * Created by nicolasaubry on 08/12/2016.
  */
private[engine] object SearchMatchers {

  type MatcherFunction = (String, List[String]) => Int

  val LooseMatcher: MatcherFunction = (term, words) => if (words.exists(w => w.contains(term))) 1 else 0
  val ExactMatcher: MatcherFunction = (term, words) => if (words.contains(term)) 1 else 0

}
