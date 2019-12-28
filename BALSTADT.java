///////////////////////////////////////////////////////////////////////////////////////////////////
//
// Title: Red-Black Tree Visualizer
// Description: Defines a Red-Black Tree which implements interface BALSTADT, and defines JUnit
// tests. Additionally, includes method for printing tree in order to visualize Red-Black tree.
// Author: Matthew Karrmann
// Email: mkarrmann@wisc.edu
//
// Note: This is an extension of class project for UW-Madison CS 400. We were assigned to design and
// implement a Red-Black tree from scratch; all file names and relevant method headers were
// provided. We were also tasked with printing the information of the tree in console under ideal
// conditions; however, I was disappointed with the aesthetics and clarity of simple methods or
// those found online. The algorithm used in the print() method, along with its helper methods, is
// an algorithm which I developed for visualizing the Red-Black tree as clearly as possible.
// This clarity comes as the cost of losing compactness (despite my algorithm being a greedy one
// which displays the tree as compactly as possible while adhering to the desired properties),
// although I believe my algorithm is the best one for visualizing small-medium trees, which is
// the intended use-case.
//
///////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.List;

/**
 * Defines the additional operations required of student's BST class.
 * 
 * @author Deb Deppeler (deppeler@cs.wisc.edu)
 * @param <K> A Comparable type to be used as a key to an associated value.  
 * @param <V> A value associated with the given key.
 */
public interface BALSTADT<K extends Comparable<K>,V> {
    
    /**
     * Returns the key that is in the root node of this BST.
     * If root is null, returns null.
     * @return key found at root node, or null
     */
    K getKeyAtRoot() ;
    
    /**
     * Tries to find a node with a key that matches the specified key.
     * If a matching node is found, it returns the returns the key that is in the left child.
     * If the left child of the found node is null, returns null.
     * 
     * @param key A key to search for
     * @return The key that is in the left child of the found key
     * 
     * @throws IllegalNullKeyException if key argument is null
     * @throws KeyNotFoundException if key is not found in this BST
     */
    K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException;
    
    /**
     * Tries to find a node with a key that matches the specified key.
     * If a matching node is found, it returns the returns the key that is in the right child.
     * If the right child of the found node is null, returns null.
     * 
     * @param key A key to search for
     * @return The key that is in the right child of the found key
     * 
     * @throws IllegalNullKeyException if key is null
     * @throws KeyNotFoundException if key is not found in this BST
     */
    K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException;
    

    /**
     * Returns the height of this BST.
     * H is defined as the number of levels in the tree.
     * 
     * If root is null, return 0
     * If root is a leaf, return 1
     * Else return 1 + max( height(root.left), height(root.right) )
     * 
     * Examples:
     * A BST with no keys, has a height of zero (0).
     * A BST with one key, has a height of one (1).
     * A BST with two keys, has a height of two (2).
     * A BST with three keys, can be balanced with a height of two(2)
     *                        or it may be linear with a height of three (3)
     * ... and so on for tree with other heights
     * 
     * @return the number of levels that contain keys in this BINARY SEARCH TREE
     */
    int getHeight();
    
    
    /**
     * Returns the keys of the data structure in sorted order.
     * In the case of binary search trees, the visit order is: L V R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in-order
     */
    List<K> getInOrderTraversal();
    
    /**
     * Returns the keys of the data structure in pre-order traversal order.
     * In the case of binary search trees, the order is: V L R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in pre-order
     */
    List<K> getPreOrderTraversal();

    /**
     * Returns the keys of the data structure in post-order traversal order.
     * In the case of binary search trees, the order is: L R V 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in post-order
     */
    List<K> getPostOrderTraversal();

    /**
     * Returns the keys of the data structure in level-order traversal order.
     * 
     * The root is first in the list, then the keys found in the next level down,
     * and so on. 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in level-order
     */
    List<K> getLevelOrderTraversal();
    
    
    /** 
     * Add the key,value pair to the data structure and increase the number of keys.
     * If key is null, throw IllegalNullKeyException;
     * If key is already in data structure, throw DuplicateKeyException(); 
     * Do not increase the num of keys in the structure, if key,value pair is not added.
     */
    void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException;
    
    

    /** 
    * If key is found, remove the key,value pair from the data structure and decrease num keys.
    * If key is not found, do not decrease the number of keys in the data structure.
    * If key is null, throw IllegalNullKeyException
    * If key is not found, throw KeyNotFoundException().
    */
    boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException;

    /**
     *  Returns the value associated with the specified key
     *
     * Does not remove key or decrease number of keys
     * If key is null, throw IllegalNullKeyException 
     * If key is not found, throw KeyNotFoundException().
     */
    V get(K key) throws IllegalNullKeyException, KeyNotFoundException;

    /** 
     * Returns true if the key is in the data structure
     * If key is null, throw IllegalNullKeyException 
     * Returns false if key is not null and is not present 
     */
    boolean contains(K key) throws IllegalNullKeyException;

    /**
     *  Returns the number of key,value pairs in the data structure
     */
    int numKeys();
    
    
    /**
     * Print the tree. 
     *
     */
    public void print();
    
}
