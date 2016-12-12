package org.nico.search.simple.engine.helper

import org.nico.search.simple.engine.helper.SearchMatchers.MatcherFunction

import scala.annotation.tailrec

/**
  * Created by nicolasaubry on 08/12/2016.
  */
private[engine] object SearchCounters {

  type CounterFunction = (List[String], List[String], MatcherFunction) => Int

  val LoopOnePointMaxPerTerm: CounterFunction = (terms, words, matcher) => {
    terms.map(term => matcher(term, words)).sum
  }

  val RecursiveOnePointMaxPerTerm: CounterFunction = (terms, words, matcher) => {
    @tailrec
    def searchMatches(terms: List[String], result: Int): Int = {
      if (terms.isEmpty) result
      else searchMatches(terms.tail, result + matcher(terms.head, words))
    }
    searchMatches(terms, 0)
  }

}
