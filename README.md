# Simple Text Search

### Description
 * Allows to search for words in a definite set of files
 * Files having the most matches are ranked based on a "very simple" weighing mechanism:
   * each search term gives the same amount of points
   * the amount of points for each term is simply defined on a base 100 (e.g. if 5 search terms are provided, each term matching will weigh 20)
 * The files are selected based on the directory path given at application startup

### Pre-requisites
 * Install [Scala 2.11.x](https://www.scala-lang.org/download/)
 * (Optional) Install [SBT 0.13.x](http://www.scala-sbt.org/download.html)
  * **Note:** SBT is needed only if you want to compile the sources and run the tests

### Setup & Usage

 1. Clone this repository

 2. Setup & start
  * **Option1 - From the binaries**
   * Download and unzip the compiled classes from  [here](https://github.com/thepotatofield/text-search/raw/master/binaries/simple-text-search.zip)
   * Start the application from the **classes** folder:
```bash
$ scala org.nico.search.simple.app.Main <dir_path_containing_the_files_to_load_and_search>
```
  * **Option2 - From the sources**
   * Compile with SBT from the project folder:
 ```bash
 $ sbt compile
 ```
   * Start the application from the **simple-text-search/target/scala-2.11/classes** folder:   
```bash
$ scala org.nico.search.simple.app.Main <dir_path_containing_the_files_to_load_and_search>
```

 3. Command-line interface

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
cmd> :search abc def ghi        // loose search on "abc, def, ghi"
cmd> :search -l abc def ghi     // loose search on "abc, def, ghi"
```

### Testing

 * Testing is based on [Scalatest](http://www.scalatest.org)
 * From the project folder, run:   
 ```bash
 $ sbt test
 ```
