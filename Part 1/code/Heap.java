/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerank;
import java.util.ArrayList;

/**
 *  This is the Heap class
 * @author Eric
 */
public class Heap {
    private int heapSize = 0;
    private ArrayList<Pair> A;
    /**
     * This is constructor which takes in an array and makes a Heap object
     * @param array 
     */
    public Heap(ArrayList array) {
        heapSize = array.size() - 1;    //makes the heapsize the size of the array
        A = array;  //make the object's arraylist A equal to the user input array
    }
    /**
     * This gets the index of the parent of a given node
     * @param i the index of a node
     * @return the index of the parent
     */
    public int getParent(int i) {
        return (i - 1) / 2;     //formula to get parent of a node in a binary tree
    }
    /**
     * This gets the index of the left child of a given node
     * @param i the index of a node
     * @return the index of the left child
     */
    public int getLeft(int i) {
        return (2 * i) + 1;     //formula to get left child of a node in a binary tree
    }
    /**
     * This gets the index of the right child of a given node
     * @param i the index of a node
     * @return the index of the right child
     */
    public int getRight(int i) {
        return (2 * i) + 2;     //formula to get right child of a node in a binary tree
    }
    /**
     * This maxheapify the value at index i of an arraylist
     * @param i The index of the arraylist that we wish to perform maxheapify on
     */
    public void maxHeapify(int i) {
        int largest;        //largest variable
        int l = getLeft(i); //l is the left child of the node i
        int r = getRight(i);//r is the right child of the node i
        if(l <= heapSize && A.get(l).getValue() > A.get(i).getValue()) {
            largest = l;    //if heapsize is >= l and left value is greater than i score
        } else {
            largest = i;    //if heapsize is < l and left value is less than i score
        }
        if(r <= heapSize && A.get(r).getValue() > A.get(largest).getValue()) {
           largest = r;     //if heapsize is >= r and r value is greater than largest value
        }
        if(largest != i) {  //if the largest variable is not i
            Pair temp = A.get(i);       //temp variable to store i
            A.set(i, A.get(largest));   //change position of i into the largest
            A.set(largest, temp);       //change position of largest into i
            maxHeapify(largest);        //repeat previous steps on the largest
        }
    }
    /**
     * This method rearranges the values of an arraylist into one that follows the maxheap format
     */
    public void buildMaxHeap() {
        heapSize = A.size() - 1;    //heapsize is set to the size of the array
        for(int i = (A.size() - 1) / 2; i >= 0; i--) {
            maxHeapify(i);  //call the maxheapify method from half the size of the array to 0
        }
    }
    /**
     * Sorts the arraylist in ascending order
     */
    public void heapSort() {
        buildMaxHeap(); //call the build max heap method
        for(int i = A.size() - 1; i >= 1; i--) {    //start from array size down to 1
            Pair temp = A.get(0);   //temp variable to store value at 0
            A.set(0, A.get(i));     //set the 0 index to the value at i
            A.set(i, temp);         //set the i index to the value at 0
            heapSize--;             //decrease heapsize by 1
            maxHeapify(0);          //call maxheapify method on the 0 index
        }
    }
    /**
     * gets the max value in the heap
     * @return the max value in the heap
     */
    public Pair heapMaximum() {
        return A.get(0); //return the node with the max score
    }
    /**
     * gets the max value in the heap and remove it, then perform maxheapify on the top node
     * @return the max value in the heap
     */
    public Pair heapExtractMax() {
        Pair max = A.get(0);        //set the max variable to be the the value at index 0
        A.set(0, A.get(heapSize));  //set the index 0 into the value at the index heapsize
        heapSize--;                 //decrease heapsize by 1
        maxHeapify(0);              //call maxheapify method on the 0 index
        return max;                 //return the max variable
    }
    /**
     * Increase the priority of a node in the heap
     * @param i the index of the node that we wish to increase the priority
     * @param key the new value of the priority
     */
    public void heapIncreaseKey(int i, int key) {
        Pair newPair = new Pair(A.get(i).getUrl(), key);    //new pair object with the i index url and new key
        A.set(i, newPair);  //replace the i index with the new pair
        while(i > 0 && A.get(getParent(i)).getValue() < A.get(i).getValue()) {
            Pair temp = A.get(i);   //temp variable is the i index
            A.set(i, A.get(getParent(i)));  //set the i index to the parent of i value
            A.set(getParent(i), temp);      //set the parent of i index to temp
            i = getParent(i);               //i is set to the value at parent of i
        }
    }
    
    /**
     * We can insert a new node into the heap and keep the heap property
     */
    public void maxHeapInsert(Pair newNode) {
        heapSize = A.size() - 1;    //heapsize is the size of array
        heapSize++;                 //increase the heapsize by 1
        Pair node = newNode;        //set node equal to the newNode input
        A.add(node);                //add the new node to the array
        heapIncreaseKey(heapSize, node.getValue()); //call the heapIncreaseKey method on the value of the node
    }
}

