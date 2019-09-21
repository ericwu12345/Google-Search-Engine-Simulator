/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerank;

/**
 *  this is the bucket class that we'll use to store popular keyword searches
 * @author wueri
 */
import java.util.*;
public class bucket {
    private ArrayList<String> list;
    private ArrayList<String>[] bucket;
    private ArrayList<String> result;
    /**
     * creates an bucket object that store an arraylist
     * @param A the arraylist we need to provide the object
     */
    public bucket(ArrayList<String> A) {
        this.list = A;
        bucket = new ArrayList[list.size()];
        result = new ArrayList<>();
    }
    /**
     * sorts the arraylist
     */
    public void bucketSort(){
        int n = list.size();
        for(int i = 0; i < n; i++) { //create an array that stores arraylists
            bucket[i] = new ArrayList<String>();
        }
        for(int i = 0; i < n; i++) {
            if(list.get(i).substring(5, 6).equals(":")) { //three cases 
                char loc = list.get(i).charAt(8);   //if website is https: we ignore that part 
                int location = (int)loc - 97;
                bucket[location].add(list.get(i).substring(8, list.get(i).length()));
            }   else if(list.get(i).substring(5, 6).equals("/")) {
                char loc = list.get(i).charAt(7);   //if website is http: we ignore that part 
                int location = (int)loc - 97;
                bucket[location].add(list.get(i).substring(7, list.get(i).length()));
            }   else {             //if website doesn't have http(s): we include the whole url
                char loc = list.get(i).charAt(0);
                int location = (int)loc - 97;       //a has ascii value of 97, so index 0 is a etc...
                bucket[location].add(list.get(i));  //add it to array
            }
        }
        for(int i = 0; i < n; i++) {
            if(bucket[i] != null) { //if bucket is not null we sort it
                Collections.sort(bucket[i]);
            }
        }
        for(int i = 0; i < n; i++) {    //concatenate the buckets into an arraylist
            if(bucket[i] != null) {
                for(int j = 0; j < bucket[i].size(); j++) {
                    result.add(bucket[i].get(j));
                }
            }
        }
    }
    /**
     * gets the arraylist that has the sorted concatenated results
     * @return the sorted arraylist
     */
    public ArrayList<String> getResult() {
        return result;
    }
}
