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
public class RedBlackTree {
    Node root;
    Node nil;
    private int counter = -1;
    private int counter2 = 0;
    private int counter33 = 0;
    /**
     * Creates a bst object
     */
    public RedBlackTree() {
        Pair sentinel = new Pair("sentinel", 0);
        nil = new Node(sentinel);
        nil.setColor("BLACK");
        root = nil;
    }
    /**
     * print out the tree nodes in sorted order
     * @param x the node where we want to start at usually the root of the tree
     */
    public void inOrderTreeWalk(Node x){    //used to sort the nodes in ascending order
        if(x != nil) {
            inOrderTreeWalk(x.getLeft());
            counter++;
            System.out.println("Index [" + counter + "] " + "Rank: " + (counter2 - counter) + " Color: " + x.getColor() + " " + x.getKey().toString());
            inOrderTreeWalk(x.getRight());
        }
    }
    public void preOrderTreeWalk(Node x) {      //used to check if structure of tree is correct
        if(x == nil) {
            return;
        }
        System.out.println(" Color: " + x.getColor() + " " + x.getKey().toString());
        preOrderTreeWalk(x.getLeft());
        preOrderTreeWalk(x.getRight());
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
    public Node getUrl(int rank) {
        int rankCounter = 1;
        Node x = getMax(getRoot());
        if(rank == 1) { //rank 1 is max in tree
            return x;
        } else { //start from max and go to predecessor until we hit desired pagerank
            while(rankCounter != rank) {
                x = treePredecessor(x);
                rankCounter++;
            }
        }
        return x;
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
        while(x.getLeft() != nil) {
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
        while(x.getRight() != nil) {
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
        if(x.getLeft() != nil) {
            return getMax(x.getLeft());
        }
        Node y = x.getParent();
        while(y != nil && x == y.getLeft()) {
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
        if(x.getRight() != nil) {
        return getMin(x.getRight());
        }
        Node y = x.getParent();
        while(y != nil && x == y.getRight()) {
            x = y;
            y = y.getParent();
        }   return y;
    }
    /**
     * inserting a node to the tree and keeping its redblack structure
     * @param z the node that we want to insert to the tree
     */
    public void redBlackTreeInsert(Node z) {
        Node y = nil;
        Node x = root;
        while(x != nil) {
            y = x;
            if(z.getKey().getValue() < x.getKey().getValue()) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }
        z.setParent(y);
        if(y == nil) {                                         //case 1
            root = z;
        } else if(z.getKey().getValue() < y.getKey().getValue()) { //case 2
            y.setLeft(z);
        } else if(z.getKey().getValue() >= y.getKey().getValue()){  //case 3
            y.setRight(z);
        }
        counter2++; //keep track of the counter
        z.setLeft(nil);
        z.setRight(nil);
        z.setColor("RED");
        redBlackInsertFixUp(z);
    }
    /**
     * fixes up the redblacktree after an insertion takes place
     * @param z the node that we are fixing
     */
    public void redBlackInsertFixUp(Node z) {
        while(z.getParent().getColor().equals("RED")) {
            if(z.getParent() == z.getParent().getParent().getLeft()) {
                boolean done = false;
                Node y = z.getParent().getParent().getRight();
                if(y.getColor().equals("RED")) {
                    z.getParent().setColor("BLACK");
                    y.setColor("BLACK");
                    z.getParent().getParent().setColor("RED");
                    z = z.getParent().getParent();
                    done = true;
                } else if(z == z.getParent().getRight()) {
                    z = z.getParent();
                    leftRotate(z);
                }
                if(!done) {
                    z.getParent().setColor("BLACK");
                    z.getParent().getParent().setColor("RED");
                    rightRotate(z.getParent().getParent());
                }
            } else if(z.getParent() == z.getParent().getParent().getRight()){
                Node y = z.getParent().getParent().getLeft();
                boolean done = false;
                if(y.getColor().equals("RED")) {
                    z.getParent().setColor("BLACK");
                    y.setColor("BLACK");
                    z.getParent().getParent().setColor("RED");
                    z = z.getParent().getParent();
                    done = true;
                } else if(z == z.getParent().getLeft()) {
                    z = z.getParent();
                    rightRotate(z); 
                }
                if(!done) {
                    z.getParent().setColor("BLACK");
                    z.getParent().getParent().setColor("RED");
                    leftRotate(z.getParent().getParent()); 
                }
            }
        }
        root.setColor("BLACK");
    }
    /**
     * the transplant part of the delete method
     * @param u  the node we want to replace
     * @param v  the node we want to transplant
     */
    public void redBlackTransplant(Node u, Node v) {
        if (u.getParent() == nil) {
            root = v;
        } else if(u == u.getParent().getLeft()) {
            u.getParent().setLeft(v);
        } else {
            u.getParent().setRight(v);
        }
        v.setParent(u.getParent());
    }
    /**
     * deleting a node from the red black tree
     * @param z the node we want to delete
     */
    public void redBlackTreeDelete(Node z) {
        Node y = z;
        Node x;
        String originalColor = y.getColor();
        if(z.getLeft() == nil) { //case 1
            x = z.getRight();
            redBlackTransplant(z, z.getRight());
        } else if(z.getRight() == nil) { //case 2
            x = z.getLeft();
            redBlackTransplant(z, z.getLeft());
        } else {                        //case 3
            y = getMin(z.getRight());
            originalColor = y.getColor();
            x = y.getRight();
            if(y.getParent() == z) {
                x.setParent(y);
            } else {
                redBlackTransplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }
            redBlackTransplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            y.setColor(z.getColor());
        }
        if(originalColor.equals("BLACK")) {
            redBlackDeleteFixUp(x);
        }
        counter2--; //keep track of the counter
    }
    /**
     * fixes the red black tree after a node has been deleted from the tree
     * @param x the position fo the node that was deleted
     */
    public void redBlackDeleteFixUp(Node x) {
        while(x != root && x.getColor().equals("BLACK")) {
            if(x == x.getParent().getLeft()) {
                boolean done = false;
                Node w = x.getParent().getRight();
                if(w.getColor().equals("RED")) {
                    w.setColor("BLACK");
                    x.getParent().setColor("RED");
                    leftRotate(x.getParent());
                    w = x.getParent().getRight();
                } if(w.getLeft().getColor().equals("BLACK") && w.getRight().getColor().equals("BLACK")) {
                    w.setColor("RED");
                    x = x.getParent();
                    done = true;
                } else if(w.getRight().getColor().equals("BLACK")) {
                    w.getLeft().setColor("BLACK");
                    w.setColor("RED");
                    rightRotate(w);
                    w = x.getParent().getRight();
                }
                if(!done) {
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor("BLACK");
                    w.getRight().setColor("BLACK");
                    leftRotate(x.getParent());
                    x = root;
                }
            } else {
                Node w = x.getParent().getLeft();
                boolean done = false;
                if(w.getColor().equals("RED")) {
                    w.setColor("BLACK");
                    x.getParent().setColor("RED");
                    rightRotate(x.getParent());     
                    w = x.getParent().getLeft();
                } if(w.getRight().getColor().equals("BLACK") && w.getLeft().getColor().equals("BLACK")) {
                    w.setColor("RED");
                    x = x.getParent();
                    done = true;
                } else if(w.getLeft().getColor().equals("BLACK")) {
                    w.getRight().setColor("BLACK");
                    w.setColor("RED");
                    leftRotate(w);  
                    w = x.getParent().getLeft();
                }
                if(!done) {
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor("BLACK");
                    w.getLeft().setColor("BLACK");
                    rightRotate(x.getParent());
                    x = root;
                }
            }
        }
        x.setColor("BLACK");
    }
    /**
     * rotates left where the node is
     * @param x the node that we want to rotate left
     */
    public void leftRotate(Node x) {
        Node y = x.getRight();
        x.setRight(y.getLeft());
        if(y.getLeft() != nil) {
            y.getLeft().setParent(x);
        }
        y.setParent(x.getParent());
        if(x.getParent() == nil) {
            root = y;
        } else if(x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }
        y.setLeft(x);
        x.setParent(y);
    }
    /**
     * rotates right where the node is
     * @param x the node that we want to rotate right
     */
    public void rightRotate(Node x) {
        Node y = x.getLeft();
        x.setLeft(y.getRight());
        if(y.getRight() != nil) {
            y.getRight().setParent(x);
        }
        y.setParent(x.getParent());
        if(x.getParent() == nil) {
            root = y;
        } else if(x == x.getParent().getRight()) {
            x.getParent().setRight(y);
        } else {
            x.getParent().setLeft(y);
        }
        y.setRight(x);
        x.setParent(y);
    }
}


