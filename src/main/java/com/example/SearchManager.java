package com.example;

import org.apache.lucene.index.*;
import org.apache.lucene.store.*;
import java.nio.file.*;
import java.io.IOException;

public class SearchManager {
    public String indexPath;
    
    // java constructor
    public SearchManager() {
        this.indexPath = "index";
    }

    void setupFolders() throws IOException {
        // creates folders if they dont exist
        Files.createDirectories(Paths.get(indexPath));
    }

    // gets num of docs in index
    int getDocCount() throws Exception {
        Directory dir = FSDirectory.open(Paths.get(indexPath));
        DirectoryReader reader = DirectoryReader.open(dir);
        int count = reader.numDocs();
        reader.close();
        dir.close();
        return count;
    }

} 