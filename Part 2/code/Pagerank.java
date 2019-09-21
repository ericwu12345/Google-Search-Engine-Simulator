/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerank;

/**
 * This class implements and uses the pair, Node, BST, bucket, and spider classes.
 * @author wueric
 */
import java.util.*;

public class Pagerank {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean done = false;
        LinkedHashSet<String> list = new LinkedHashSet<>();
        while(!done) {
        System.out.print("What keyword do you want to search?: ");
        String keyWord = scan.next();
        list.add(keyWord);              //storing unique searches
        Spider crawler = new Spider();  //new spider object
        crawler.search("https://arstechnica.com/", keyWord);     //website we are getting URLs from, and keyword is science
        ArrayList<String> array = crawler.getPageList();    //set the string arraylist equal to the crawler's list of URLs
        ArrayList<Pair> A = new ArrayList<>();      
        
        for(int i = 0; i < array.size(); i++) {
            Pair pair = new Pair(array.get(i), makeRandomScore());  //assign each url a random score
            A.add(pair);    //store each url and ranodm score into the arraylist
        }
        System.out.println("URLs we have collected and their scores (UNSORTED)");
        Quicksort quicksort = new Quicksort(A);    //Making a new quicksort object with the A arraylist as the argument
        for(int i = 0; i < A.size(); i++) {
            System.out.println("Index [" + i + "] " + A.get(i).toString());  //prints the URLs unmodified
        }   System.out.println();
        System.out.println("URLs after QuickSort:");
        quicksort.quickSort(A, 0, A.size() - 1); //perform quicksort
        int rankcounter = A.size();
        for(int i = 0; i < A.size(); i++) {
            System.out.println("Index [" + i + "] " + "Rank: " + rankcounter + " " + A.get(i).toString());  //prints the URLs
            rankcounter--;
        }   System.out.println();
        
        
        System.out.print("Do you want to search for any other word? (Y/N): ");
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
        System.out.println();
        System.out.println("Testing Binary Search Tree:");
        done = false;
        while(!done) {
        System.out.print("What keyword do you want to search?: ");
        String keyWord = scan.next();
        list.add(keyWord);
        Spider crawler = new Spider();  //new spider object
        crawler.search("https://arstechnica.com/", keyWord);     //website we are getting URLs from, and keyword is science
        ArrayList<String> array = crawler.getPageList();    //set the string arraylist equal to the crawler's list of URLs
        BinarySearchTree bst = new BinarySearchTree();     //create a bst object 
        
        for(int i = 0; i < array.size(); i++) {
            Pair pair = new Pair(array.get(i), makeRandomScore());  //assign each url a random score
            Node node = new Node(pair);         //node has value of url and score
            bst.treeInsert(node);    //store each node into the tree
        }
        System.out.println("Binary Search Tree in order tree walk");
        bst.inOrderTreeWalk(bst.getRoot());
        bst.resetCounter();
        System.out.println();
        System.out.println("Adding two websites to the BST");
        Pair website = new Pair("MikeWu.com", 88888888); //website and score 1
        Pair website2 = new Pair("cs.com", 100);                //website and score 2
        Node mikeWu = new Node(website);
        Node cs = new Node(website2);
        bst.treeInsert(mikeWu);     //inserting the two websites
        bst.treeInsert(cs);
        
        System.out.println("Binary Search Tree in order tree walk with the added URLs");
        bst.inOrderTreeWalk(bst.getRoot());
        bst.resetCounter();
        System.out.println();
        
        System.out.println("Deleting cs.com from tree");
        bst.treeDelete(cs);
        
        System.out.println("Binary Search Tree in order tree walk after deleted URL");
        bst.inOrderTreeWalk(bst.getRoot());
        bst.resetCounter();
        System.out.println();
        
        System.out.print("Enter a pagerank to search for the URL: ");
        int rankToCheck = scan.nextInt();
        scan.nextLine();
        System.out.println("The score and Url with pagerank " + rankToCheck + " is " + bst.getUrl(rankToCheck));
        System.out.println();
        
        System.out.print("Do you want to search for any other word? (Y/N): ");
        String response = scan.nextLine().toUpperCase();
        if(response.equals("Y")) {
            done = false;
        } else {
            done = true;
        }
        }
        System.out.println();
        System.out.println("The Top 10 Unique Searches:");
        counter = 0;
        it = list.iterator();
        while(it.hasNext() && counter < 10) {
            System.out.println(it.next());
        } System.out.println();
        System.out.print("Which of the popular keyword would you like to store in a bucket? ");
        String answer = scan.nextLine();
        Spider crawler = new Spider();  //new spider object
        crawler.search("https://arstechnica.com/", answer);     //website we are getting URLs from, and keyword is science
        ArrayList<String> array = crawler.getPageList();    //set the string arraylist equal to the crawler's list of URLs
        bucket Bucket = new bucket(array);  //create a bucket object
        Bucket.bucketSort();                //sort the bucket with bucket sort
        ArrayList<String> result = new ArrayList<>();
        result = Bucket.getResult();
        Iterator it2 = result.iterator();
        System.out.println();
        System.out.println("The URL's sorted alphabetically via bucketsort");
        while(it2.hasNext()) {
            System.out.println(it2.next());
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
