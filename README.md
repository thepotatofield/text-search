# Simple Text Search

### Description
 * Allows to search for words in a definite set of files
 * Files having the most matches are ranked based on a "very simple" weighing mechanism:
   * each search term gives the same amount of points
   * the amount of points for each term is simply defined on a base 100 (e.g. if 5 search terms are provided, each term matching will weigh 20)
 * The files are selected based on the directory path given at application startup

### Pre-requisites
 * Install [Scala 2.11.x](https://www.scala-lang.org/download/)
 * Install compiling and testing [SBT 0.13.x](http://www.scala-sbt.org/download.html)
  * SBT is only needed if you want to compile and run the tests

### Usage

 1. Clone this repository

 2. Setup
  * Either get the binaries and unzip:   
  * Compile with SBT. From the **text-search** project folder:
 ```bash
 $ sbt compile
 ```

 3. Start the application
  * From the **/text-search/simple-text-search/target/scala-2.11/classes** project folder:
```bash
$ scala org.nico.search.simple.app.Main <dir_path_containing_the_files_to_search>
```

 4. Command-line interface

| Command       | Description   |
| ------------- | ------------- |
| :quit, :q | Exit the app |
| :help, :h | Display the help |
| :data | Display the data loaded from the files |
| :max | Set the max amount of results to return |
| :search -loose, -l <terms> | Trigger a **loose** search of with given terms. Matching applies to only part or full word. Default. |
| :search -exact, -e <terms> | Trigger an **exact** search of with given terms. Search term and word should be identical applies |

 * Examples
```bash
cmd> :search -exact abc def ghi // search for "abc, def, ghi"
cmd> :search abc def ghi        // loose search on "abc, def, ghi"
cmd> :search -l abc def ghi     // loose search on "abc, def, ghi"
```

### Testing

 * Testing is based on [Scalatest](http://www.scalatest.org)
 * Additional pre-requisite:
    Install[SBT](http://www.scala-sbt.org/download.html)
 * Usage
  * From the **text-search** project folder, run:   
 ```bash
 $ sbt test
 ```
