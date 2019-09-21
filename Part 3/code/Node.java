/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerank;

/**
 *  node class needed for the binary search tree data structure
 * @author wueri
 */
public class Node {
    private Pair key;
    private Node left;
    private Node right;
    private Node parent;
    private String color;
    /**
     * creates a node object
     * @param key the url and corresponding score
     */
    public Node (Pair key){
        this.key = key;
        this.parent = null;
        this.left = null;
        this.right = null;
        this.color = null;
    }    
    public void setColor(String color) {
        this.color = color;
    }
    /**
     * sets the parent of a node
     * @param x the parent node
     */
    public void setParent(Node x) {
        this.parent = x;
    }
    /**
     * sets the left child of a node
     * @param x the left child
     */
    public void setLeft(Node x) {
        this.left = x;
    }
    /**
     * sets the right child of a node
     * @param x the right child
     */
    public void setRight(Node x) {
        this.right = x;
    }
    /**
     * gets the parent of a node
     * @return the parent of a node
     */
    public Node getParent(){
        return parent;
    }
    /**
     * gets the left child of a node
     * @return the left child
     */
    public Node getLeft(){
        return left;
    }
    /**
     * gets the right child of a node
     * @return the right child
     */
    public Node getRight(){
        return right;
    }
    /**
     * gets the key or the url and corresponding score of a node
     * @return the key of a node
     */
    public Pair getKey(){
        return key;
    }
    public String getColor() {
        return color;
    }
}
