# Simple Text Search

### Description
 * Allows to search for words in a definite set of files and rank those based on their matching scores.
 * The notion of search engine is here composable, allowing to easily create different "flavors" of search engines.
 * Thus, composing a "search engine" consists in combining 3 higher-order functions:
  * a Matcher function, defining when 2 words are a match, or not
```scala
// input: the term to look for, the dataset to search from
// output: 1 if match, 0 otherwise
type MatcherFunction = (String, List[String]) => Int
```
  * a Counter function, defining how we should count the match (e.g. if only one occurence of a term in a dataset weighs the same than multiple occurences of that same term)
```scala
// input: the terms to look for, the dataset to search from, the matcher method
// output: the amount of point collected by the terms on the given dataset
type CounterFunction = (List[String], List[String], MatcherFunction) => Int
```
  * a Scorer function, defining the way that the score of a dataset should be calculated. It determinates the final ranking
```scala
// input: nb search terms, nb of words in dataset, nb of matches
// output: the computed score
type ScorerFunction = (Int, Int, Int) => Int
```  
 * Currently the scoring method is based on a "very simple" weighing mechanism:
   * each search term gives the same amount of points
   * the amount of points for each term is simply defined on a base 100 (e.g. if 5 search terms are provided, each term matching will weigh 20)
 * The files are selected based on the directory path given at application startup

### Pre-requisites
 * Install [Scala 2.11.x](https://www.scala-lang.org/download/)
 * (Optional) Install [SBT 0.13.x](http://www.scala-sbt.org/download.html)
  * **Note:** SBT is needed only if you want to compile the sources and run the tests

### Setup

#### 1 - Clone this repository

#### 2 - Setup & start
 * **Option1 - From the binaries**
  * Unzip the compiled classes from the [**binaries**](https://github.com/thepotatofield/text-search/tree/master/binaries) folder   
  * Start the application from the **classes** folder:
```bash
$ scala org.nico.search.simple.app.Main <dir_path_containing_the_files_to_load_and_search_from>
```
 * **Option2 - From the sources**
  * Compile with SBT from the project folder:
```bash
$ sbt compile
```
  * Start the application from the **simple-text-search/target/scala-2.11/classes** folder:   
```bash
$ scala org.nico.search.simple.app.Main <dir_path_containing_the_files_to_load_and_search_from>
```

### Commands

| Command       | Description   |
| ------------- | ------------- |
| :quit, :q | Exit the app |
| :help, :h | Show this help |
| :data | Show the loaded file names |
| :max | Set the max amount of results to return |
| :search -loose, -l <terms> | Execute a **loose** search of with given terms. Matching applies to only part or full word. Default. |
| :search -exact, -e <terms> | Execute an **exact** search of with given terms. Search term and word should be identical applies |

 * Examples
```bash
cmd> :search -exact abc def ghi // search for "abc, def, ghi"
cmd> :search abc def ghi        // loose search for "abc, def, ghi"
cmd> :search -l abc def ghi     // loose search for "abc, def, ghi"
```

### Testing

 * Testing is based on [Scalatest](http://www.scalatest.org)
 * Note that only a minimal basic test spec is available for demo and discussion purposes :)
 * From the project folder, run:   
 ```bash
 $ sbt test
 ```
