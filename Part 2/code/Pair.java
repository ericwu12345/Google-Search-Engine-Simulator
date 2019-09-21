/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerank;
import java.util.*;
/**
 * This is a pair object class
 * @author wueric
 */
public class Pair {
    private String url;
    private int value;
    /**
     * This creates a Pair object which has a string and a value
     * @param url   The URL of the pair
     * @param value The value associated with the URL
     */
    public Pair(String url, int value) {
        this.url = url;     //store user inputted url into the object's url
        this.value = value; //store user inputted value into the object's value
    }
    /**
     * Gets the URL
     * @return the URL
     */
    public String getUrl() {
        return url;         //return the object's url
    }
    /**
     * Gets the value
     * @return the value
     */
    public int getValue() {
        return value;       //return the object's value
    }
    /**
     * Combine the URL and Value into a string
     * @return the combined URL and value string
     */
    public String toString() {
        return "Score: " + value + " URL: " + url;  //return a string contraining the url and value
    }
}
