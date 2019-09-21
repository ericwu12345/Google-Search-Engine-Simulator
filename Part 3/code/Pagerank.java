/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerank;

/**
 * This class implements and uses the pair, Node, BST, bucket, and spider
 * classes.
 *
 * @author wueric
 */
import java.util.*;

public class Pagerank {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean done = false;
        LinkedHashSet<String> list = new LinkedHashSet<>();
        Iterator it = list.iterator();
        System.out.println("Testing Red Black Tree:");
        done = false;
        while (!done) {
            System.out.print("What keyword do you want to search?: ");
            String keyWord = scan.next();
            list.add(keyWord);
            Spider crawler = new Spider();  //new spider object
            crawler.search("https://arstechnica.com/", keyWord);     //website we are getting URLs from
            ArrayList<String> array = crawler.getPageList();    //set the string arraylist equal to the crawler's list of URLs
            RedBlackTree bst = new RedBlackTree();     //create a bst object 

            System.out.println("Printing out the URLs in the order that is inserted into the Red BLack Tree");
            for (int i = 0; i < array.size(); i++) {
                Pair pair = new Pair(array.get(i), makeRandomScore());  //assign each url a random score
                Node node = new Node(pair);         //node has value of url and score
                bst.redBlackTreeInsert(node);    //store each node into the tree
                System.out.println(node.getKey());
            }   System.out.println();
            System.out.println("Red Black Tree in pre order tree walk **(Used to check the structure of the RB Tree)**");
            bst.preOrderTreeWalk(bst.getRoot());
            System.out.println();
            System.out.println("Red Black Tree in order tree walk **(Sorted List)**");
            bst.inOrderTreeWalk(bst.getRoot());
            bst.resetCounter();
            System.out.println();
            
            boolean loopEnd = false;
            while(loopEnd != true) {
                System.out.println("What is the URL you want to add? ");
                String url = scan.next();
                System.out.println("What is the score for the URL? ");
                int score = scan.nextInt();
                scan.nextLine();
                Pair website = new Pair(url, score);
                Node newWebsite = new Node(website);
                bst.redBlackTreeInsert(newWebsite);
                System.out.println("Do you want to add more websites? (Y/N)");
                String response = scan.next();
                if(response.equalsIgnoreCase("n")) {
                    loopEnd = true;
                }
            }

            System.out.println("Red Black Tree in preorder tree walk with the added URLs **(Used to check structure of the RB tree)**");
            bst.preOrderTreeWalk(bst.getRoot());
            System.out.println();
            System.out.println("Red Black Tree in order tree walk with the added URLS **(Sorted List)**");
            bst.inOrderTreeWalk(bst.getRoot());
            bst.resetCounter();

            System.out.println("Enter the Rank of the URL that you want to delete: ");
            int urlToDelete = scan.nextInt();
            scan.nextLine();
            bst.redBlackTreeDelete(bst.getUrl(urlToDelete));
            System.out.println();

            System.out.println("Binary Search Tree in preorder tree walk after deleted URL **(Used to check structure of the RB tree)**");
            bst.preOrderTreeWalk(bst.getRoot());
            System.out.println();
            
            System.out.println("Binary Search Tree in order tree walk after deleted URL **(Sorted List)**");
            bst.inOrderTreeWalk(bst.getRoot());
            bst.resetCounter();
            System.out.println();  

            System.out.print("Enter a pagerank to search for the URL: ");
            int rankToCheck = scan.nextInt();
            scan.nextLine();
            System.out.println("The score and Url with pagerank " + rankToCheck + " is " + bst.getUrl(rankToCheck).getKey().getUrl() + " with a score of " + bst.getUrl(rankToCheck).getKey().getValue());
            System.out.println();

            System.out.print("Do you want to search for any other word? (Y/N): ");
            String response = scan.nextLine().toUpperCase();
            if (response.equals("Y")) {
                done = false;
            } else {
                done = true;
            }
        }
        System.out.println();
        System.out.println("The Top 10 Unique Searches:");
        int counter = 0;
        it = list.iterator();
        while (it.hasNext() && counter < 10) {
            System.out.println(it.next());
        }
        System.out.println();
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
