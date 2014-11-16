package exercise3;

import common.html.GazetaHtmlDocument;
import common.html.HtmlDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main 
{
    public static void main(String[] args) throws Exception 
    {
        HtmlDocument rootDocument = new GazetaHtmlDocument("http://wiadomosci.gazeta.pl/");
        Set<String> links = rootDocument.getLinks();
        String wordToFound = "gdyby";

        // Create ExecutorService
        ExecutorService executorService = Executors.newCachedThreadPool();

        // Create list of results of type List<Future<Integer>>
        List<Future<Integer>> futures = new ArrayList<Future<Integer>>(links.size());

        for (String link : links) 
        {
            // Create new WordCounter and submit it to executorService
        	WordCounter wordCounter = new WordCounter(link, wordToFound);
        	Future<Integer> future = executorService.submit(wordCounter);
            // Store Future object in list of results
        	futures.add(future);
        }

        // shutdown executor
        executorService.shutdown();

        int numberOfWords = 0;
        // Iterate over list of results and for each Future invoke get() method
        for (Future<Integer> future : futures) 
        {
        	// add value returned from get() method to numberOfWords variable
        	numberOfWords += future.get();
        }

        System.out.printf("Number of words '%s': %d", wordToFound, numberOfWords);
    }
}
