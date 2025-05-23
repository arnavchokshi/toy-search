# Toy Search Engine

Dependancies
- Java 11 or higher
- Maven

To build run
:mvn clean package"

Commands
1. Index Documents. To creates and index 100 sample documents run
"java -jar target/toy-search-1.0-SNAPSHOT.jar index"

2. Search. To Search through documents run:
" java -jar target/toy-search-1.0-SNAPSHOT.jar search "your search query" "

3. View Stats. To Display index statistics run:
"java -jar target/toy-search-1.0-SNAPSHOT.jar stats"

These are the files that run the above commands.
- `SearchManager.java`: Handles index management and document operations
- `Searcher.java`: Implements search functionality
- `Main.java`: Provides the command-line interface