package exercise4;

import java.util.concurrent.ForkJoinPool;

public class Main 
{
    public static void main(String[] args) 
    {
        String rootUrl = "http://wiadomosci.gazeta.pl/";
        String wordToFound = "gdyby";

        // Create new ForkJoinPool object
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // Create new WebCrawlingTask for rootUrl and wordToFound
        WebCrawlingTask webCrawlingTask = new WebCrawlingTask(rootUrl, wordToFound);
        // Invoke invoke method on ForkJoinPool object passing WebCrawlingTask
        // Assign result of invoke method to numberOfWords variable
        Integer numberOfWords = forkJoinPool.invoke(webCrawlingTask);

        System.out.printf("Number of words '%s': %d", wordToFound, numberOfWords);
    }
}
