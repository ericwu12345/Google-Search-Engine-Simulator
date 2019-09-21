/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerank;

/**
 * This class implements and uses the pair, heap, and spider classes.
 * @author wueric
 */
import java.util.*;

public class Pagerank {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean done = false;
        LinkedHashSet<String>list = new LinkedHashSet<String>();
        while(!done) {
        System.out.println("What keyword do you want to search?: ");
        String keyWord = scan.next();
        list.add(keyWord);
        Spider crawler = new Spider();      //new spider object
        crawler.search("https://arstechnica.com/", keyWord);     //website we are getting URLs from, and keyword is science
        ArrayList<String> array = crawler.getPageList();    //set the string arraylist equal to the crawler's list of URLs
        ArrayList<Pair> A = new ArrayList<>();      
        
        for(int i = 0; i < array.size(); i++) {
            Pair pair = new Pair(array.get(i), makeRandomScore());  //assign each url a random score
            A.add(pair);    //store each url and ranodm score into the arraylist
        }
        
        Heap heap = new Heap(A);    //Making a new heap object with the A arraylist as the argument
        System.out.println("The 30 URLs we have collected!");
        for(int i = 0; i < A.size(); i++) {
            System.out.println("Index: " + i + " " + A.get(i).toString());  //prints the URLs unmodified
        }   System.out.println();
        
        heap.maxHeapify(0);  //tests the maxHeapify method on the 0th index of the arraylist
        System.out.println("The 30 URLs after maxHeapify is ran on the 0th index!");
        for(int i = 0; i < A.size(); i++) {
            System.out.println("Index: " + i + " " + A.get(i).toString());  //prints the URLs
        }   System.out.println();
        
        heap.heapSort();    //tests the heapSort method, it should sort the URLs based on the scores in ascending order
        System.out.println("After we run the heapSort method, the 30 URLs are now sorted in ascending order!");
        for(int i = 0; i < A.size(); i++) {
            System.out.println("Index: " + i + " " + A.get(i).toString());  //prints the URLs sorted
        }   System.out.println();
        
        heap.buildMaxHeap();    //test the buildMaxHeap method, it should rearrange arraylist so it follows heap structure
        System.out.println("After we run the buildMaxHeap method, the 30 URLS are now rearranged so it follows the heap property!");
        for(int i = 0; i < A.size(); i++) {
            System.out.println("Index: " + i + " " + A.get(i).toString());  //print the URLs so that it follows the heap structure
        }   System.out.println();
        
        System.out.println("Do you want to change the score of any URL? (Y/N): ");  //prompts user to change any score if needed
        String result = scan.next().toUpperCase();
        while(result.equals("Y")) {
            System.out.println("Enter the Index of the URL that you want to change the score: ");
            int index = scan.nextInt();
            int rank = A.get(index).getValue();
            System.out.println("Enter a new score higher than " + rank + " : ");    //we are increasing key, not decreasing
            int newRank = scan.nextInt();
            heap.heapIncreaseKey(index, newRank); //tests the heapIncreaseKey method, the new arraylist should also follow the heap structure
            System.out.println("Do you want to change the score of any other URL? (Y/N): ");    //ask user if they want to continue changing scores
            result = scan.next().toUpperCase();
        }
        System.out.println("The heap after changing scores of any URL!:");
        for(int i = 0; i < A.size(); i++) {
            System.out.println("Index: " + i + " " + A.get(i).toString());  //print the URLs so that it follows the heap structure
        }   System.out.println();
        
        System.out.println("We are testing the maxHeapInsert method!");
        System.out.println("The website name is MikeWu.com and the score is the max integer!");
        Pair mike = new Pair("https://www.MikeWu.com/", Integer.MAX_VALUE);
        heap.maxHeapInsert(mike);   //test out the maxHeapInsert method, it should place this node to the top of the priority queue
        System.out.println("The 31 URLs after we add a new URL!:");
        for(int i = 0; i < A.size(); i++) {
            System.out.println("Index: " + i + " " + A.get(i).toString());  //print the URLs so that it follows the heap structure
        }   System.out.println();
        
        System.out.println("We are testing the heapMaximum method which should return MikeWu's website and score!");
        System.out.println("The max node is: " + heap.heapMaximum().toString()); //test the heapMaximum method
        System.out.println();                                                    //it should print out MikeWu's score b/c it is highest value
        
        ArrayList<Pair> priorityQueue = new ArrayList<>();  //make a new priority queue arraylist
        Heap priority = new Heap(priorityQueue);    //make a new priority heap that takes the previous arraylist as an argument
        for(int i = 0; i < 10; i++) {
            priority.maxHeapInsert(heap.heapExtractMax());  //test the heapExtractMax method and also the maxHeapInsert method
        }                                                   //the extracted node is not inserted into the new heap
        
        System.out.println("The Top 10 Search Results:");
        for(int i = 0; i < 10; i++) {
            System.out.println("Index: " + i + " PageRank: " + (i + 1) + " " + priority.heapExtractMax().toString());
        }   System.out.println();   //the line above extracts the max node from the priority heap and call the toString() method on it
        
        System.out.println("Do you want to search for any other word? (Y/N): ");
        String response = scan.next().toUpperCase();
        if(response.equals("Y")) {
            done = false;
        } else {
            done = true;
        }
        }
        
        System.out.println("The Top 10 Unique Searches:");
        Iterator it = list.iterator();
        int counter = 0;
        while(it.hasNext() && counter < 10) {
            System.out.println(it.next());
        }
    }
    public static int makeRandomScore() {   //creates a random score based on the 4 criterias and return it
        Random random = new Random();
        int randomFrequency = random.nextInt(100) + 1;  //random frequency score of the keyword 
        int randomHistory = random.nextInt(100) + 1;    //random time score of how long the website has been created
        int randomRelevancy = random.nextInt(100) + 1;  //random relevancy score of the website
        int randomAdvertisement = random.nextInt(100) + 1; //random score on how much website paid google
        int totalScore = randomFrequency + randomHistory + randomRelevancy + randomAdvertisement;   //add up the 4 scores
        return totalScore;  //return the total score
    }
}
