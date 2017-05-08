# Solution

The solution I came up with to write a class that represents a single event report and separate loader classes that could load the CSV, JSON, and XML input files, and finally a class that could write CSV files. The main class that drives the solution is EventFilter.java.

The code was written with the assumption that the input files would be small in size, so the entire files were loaded into memory before being processed. For much larger files, you would need to parse them pieces at a time as if they were in a stream.

## How to run the solution

1. Install Eclipse with Java SE 1.8 features.
2. Open the project within the solution folder of the repo.
3. Once the dependencies are loaded, run the project.
4. The output file (output.csv) will it written along side the other reports.* input files.
5. The Eclipe Console view will show the summary of the number of records associated with each service-guid.

## Explanation of tools and libraries used

I used Eclipse Neon as my Java IDE. This made editing, compiling, and documentation lookup fast and simple.

I used Maven for dependency management. Maven helps avoid shipping binary files along with the source. The Maven dependencies are downloaded and project build time.

I used Java SE 1.8 so that I could make use of its features such as lambda expressions, which makes code syntax simpler and is fun to write.

For JSON parsing, I used the GSON library from Google (see https://github.com/google/gson). It was easy to use, quite popular and well tested. The code needed to use it was nice and small.

For CSV parsing, I used the Apache Commons CSV library (see https://commons.apache.org/proper/commons-csv/). This library provided support for reading and writing CSV files. This is also a popular and well tested library.

And finally for XML parsing I used the javax.xml library that comes along with Java SE. This library was easy to use and didn't require external dependencies.
