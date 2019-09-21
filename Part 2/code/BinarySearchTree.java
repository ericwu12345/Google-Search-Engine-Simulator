/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerank;

/**
 * this is the binary search tree that we'll use for pagerank
 * @author wueri
 */
import java.util.*;
public class BinarySearchTree {
    Node root;
    private int counter = -1;
    private int counter2 = 0;
    /**
     * Creates a bst object
     */
    public BinarySearchTree() {
        root = null;
    }
    /**
     * print out the tree nodes in sorted order
     * @param x the node where we want to start at usually the root of the tree
     */
    public void inOrderTreeWalk(Node x){
        if(x != null) {
            inOrderTreeWalk(x.getLeft());
            counter++;
            System.out.println("Index [" + counter + "] " + "Rank: " + (counter2 - counter) + " " + x.getKey().toString());
            inOrderTreeWalk(x.getRight());
        }
    }
    /**
     * resets the counter for the inordertreewalk that keeps track of index and pagerank
     */
    public void resetCounter()  {
        counter = -1;
    }
    /**
     * gets the url and score of a node given a pagerank
     * @param rank the page rank of the url we want to search
     * @return the url and score
     */
    public String getUrl(int rank) {
        int rankCounter = 1;
        Node x = getMax(getRoot());
        if(rank == 1) { //rank 1 is max in tree
            return x.getKey().getUrl() + " with the score " + x.getKey().getValue();
        } else { //start from max and go to predecessor until we hit desired pagerank
            while(rankCounter != rank) {
                x = treePredecessor(x);
                rankCounter++;
            }
        }
        return x.getKey().getUrl() + " with the score " + x.getKey().getValue();
    }
    /**
     * gets the root of the tree
     * @return the root of the tree
     */
    public Node getRoot() {
        return root;
    }
    /**
     * get the min value of a tree could be a subtree
     * @param min the node that we want to get the min of
     * @return the min node
     */
    public Node getMin(Node min){
        Node x = min;
        while(x.getLeft() != null) {
            x = x.getLeft();
        }   return x;
    } 
    /**
     * get the max value of a tree could be a subtree
     * @param max the node that we want the max of
     * @return the max node
     */
    public Node getMax(Node max) {
        Node x = max;
        while(x.getRight() != null) {
            x = x.getRight();
        }   return x;
    }
    /**
     * gets the predecessor of a node or the next smallest one
     * @param predecessor the node we want the predecessor of
     * @return the predecessor of the given node
     */
    public Node treePredecessor(Node predecessor) {
        Node x = predecessor;
        if(x.getLeft() != null) {
            return getMax(x.getLeft());
        }
        Node y = x.getParent();
        while(y != null && x == y.getLeft()) {
            x = y;
            y = y.getParent();
        }   return y;
    }
    /**
     * gets the successor of a node or the next biggest one
     * @param successor the node we want the successor of
     * @return the successor of the given node
     */
    public Node treeSuccessor(Node successor) {
        Node x = successor;
        if(x.getRight() != null) {
        return getMin(x.getRight());
        }
        Node y = x.getParent();
        while(y != null && x == y.getRight()) {
            x = y;
            y = y.getParent();
        }   return y;
    }
    /**
     * inserting a node to the tree and keeping its bst structure
     * @param z the node that we want to insert to the tree
     */
    public void treeInsert(Node z) {
        Node y = null;
        Node x = root;
        while(x != null) {
            y = x;
            if(z.getKey().getValue() < x.getKey().getValue()) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }
        z.setParent(y);
        if(y == null) {                                         //case 1
            root = z;
        }
        else if(z.getKey().getValue() < y.getKey().getValue()) { //case 2
            y.setLeft(z);
        } else {                                                //case 3
            y.setRight(z);
        }
        counter2++; //keep track of the counter
    }
    /**
     * the transplant part of the delete method
     * @param u  the node we want to replace
     * @param v  the node we want to transplant
     */
    public void transplant(Node u, Node v) {
        if (u.getParent() == null) {
            root = v;
        } else if(u == u.getParent().getLeft()) {
            u.getParent().setLeft(v);
        } else {
            u.getParent().setRight(v);
        }
        if(v != null) {
            v.setParent(u.getParent());
        }
    }
    /**
     * deleting a node from the bst
     * @param z the node we want to delete
     */
    public void treeDelete(Node z) {
        if(z.getLeft() == null) { //case 1
            transplant(z, z.getRight());
        } else if(z.getRight() == null) { //case 2
            transplant(z, z.getLeft());
        } else {                        //case 3
            Node y = getMin(z.getRight());
            if(y.getParent() != z) {
                transplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }
            transplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
        }
        counter2--; //keep track of the counter
    }
}
