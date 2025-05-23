package com.example;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.*;
import org.apache.lucene.index.*;
import java.nio.file.Paths;

public class Searcher {
    private SearchManager manager;

    //basic java constructor
    public Searcher(SearchManager manager) {
        this.manager = manager;
    }
    
    // takes an input as a search text
    public SearchResult[] search(String searchText) throws Exception {
        //opens dir of index. creates a searcher to find the index
        Directory dir = FSDirectory.open(Paths.get(manager.indexPath));
        DirectoryReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        
        //does search and gets top 10 results
        Query query = new QueryParser("contents", new StandardAnalyzer()).parse(searchText);
        TopDocs results = searcher.search(query, 10);
        
        //makes an array to hold the reults
        SearchResult[] searchResults = new SearchResult[results.scoreDocs.length];
        for (int i = 0; i < results.scoreDocs.length; i++) {
            Document doc = searcher.doc(results.scoreDocs[i].doc);
            searchResults[i] = new SearchResult(doc.get("path"), doc.get("contents"));
        }
        
        reader.close();
        dir.close();
        return searchResults;
    }
}

// created a class to hold the results
class SearchResult {
    public final String path;
    public final String content;
    
    public SearchResult(String path, String content) {
        this.path = path;
        this.content = content;
    }
} 