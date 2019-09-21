/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerank;

/**
 *  This is the quicksort class that creates an object where you can perform quicksort on it
 * @author wueri
 */
import java.util.*;
public class Quicksort {
    ArrayList<Pair> A;
    /**
     * Creates a quicksort object that holds an arraylist of type pair
     * @param A the array you need to provide the object
     */
    public Quicksort(ArrayList<Pair> A) {
        this.A = A;
    }
    /**
     * Perform the quicksort
     * @param A the arraylist we are sorting
     * @param p the first index that we want to sort
     * @param r the last index that we want to sort
     */
    public void quickSort(ArrayList<Pair> A, int p, int r) {
        if(p < r) {
            int q = partition(A, p, r);
            quickSort(A, p, q - 1);
            quickSort(A, q + 1, r);
        }
    }
    /**
     * the partition process of quicksort
     * @param A the arraylist we want to sort
     * @param p the first index that we want to sort
     * @param r the last index that we want to sort or the pivot
     * @return  the position on the pivot
     */
    public int partition(ArrayList<Pair> A, int p, int r) {
        int x = A.get(r).getValue();    //pivot is at last index
        int i = p - 1;
        for(int j = p; j < r; j++) {//split array into 4 parts, less/equal/greater than pivot and unsorted
            if(A.get(j).getValue() <= x) {
                i++;
                Pair temp = A.get(i);       //swap
                A.set(i, A.get(j));
                A.set(j, temp);
            }
        }
        Pair temp = A.get(i + 1);   //swap
        A.set(i + 1, A.get(r));
        A.set(r, temp);
        return i + 1;
    }
}
