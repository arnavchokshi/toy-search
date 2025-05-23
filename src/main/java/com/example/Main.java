package com.example;

import java.nio.file.Paths;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.store.*;

public class Main {
    public static void main(String[] args) throws Exception {

        //Creating a manager to do index operations
        SearchManager manager = new SearchManager();
        //Creates a searcher to search
        Searcher searcher = new Searcher(manager);
        //Sets up folders
        manager.setupFolders();

        //swithc for the options of commands I have described in the readme
        switch (args[0]) {
            //the first partt opens a dir to store the index. It then create s swriter that adds to the index
            //It then loops over 100 so that it can add 100 sample domcuments
            // It then adds these to the index
            case "index":
                Directory dir = FSDirectory.open(Paths.get(manager.indexPath));
                IndexWriter writer = new IndexWriter(dir, new IndexWriterConfig(new StandardAnalyzer()).setOpenMode(IndexWriterConfig.OpenMode.CREATE));
                
                for (int i = 1; i <= 100; i++) {
                    Document doc = new Document();
                    String text = "doc " + i + " - test content";
                    String path = "doc" + i + ".txt";
                    doc.add(new TextField("contents", text, Field.Store.YES));
                    doc.add(new StringField("path", path, Field.Store.YES));
                    writer.addDocument(doc);
                }
                writer.close();
                dir.close();
                break;

            //Uses searcher to find matchign docmuments using the users search token. it then prints it out
            case "search":
                SearchResult[] results = searcher.search(args[1]);
                System.out.println("found " + results.length + " results:");
                for (SearchResult result : results) {
                    System.out.println(result.path);
                }
                break;

            //just prints the num of docs that are in index
            case "stats":
                System.out.println("docs in index: " + manager.getDocCount());
                break;
        }
    }
} 