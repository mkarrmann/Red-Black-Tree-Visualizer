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

/**
 * Defines behavior and constructor of nodes for BST.
 * 
 * @author Matthew Karrmann
 *
 * @param <Key>
 * @param <Value>
 */
class BSTNode<Key, Value> {

  // Instance variables:
  Key key; // Key used to order nodes
  Value value; // Value held in node
  BSTNode<Key, Value> leftChild; // Left child of node
  BSTNode<Key, Value> rightChild; // Right child of node
  BSTNode<Key, Value> parent; // Parent node (null if node is root)
  // How much "space" is needed by node when printing
  int spacingFactor;
  // Number of characters to the left of node when printing
  int leftSpacing;
  String color; // Node color: Red ("R") or Black ("B")
  int height; // Height of node in tree, updated before printing


  /**
   * Full constructor, which assigns instance values.
   * 
   * @param key
   * @param value
   * @param leftChild
   * @param rightChild
   */
  BSTNode(Key key, Value value, BSTNode<Key, Value> parent, BSTNode<Key, Value> leftChild,
      BSTNode<Key, Value> rightChild) {
    // Assigns instance fields to corresponding values of constructor
    this.key = key;
    this.value = value;
    this.parent = parent;
    this.leftChild = leftChild;
    this.rightChild = rightChild;
    // Spacing factor is determined at time of printing, and is defaulted to 0.
    this.spacingFactor = 0;
    // Spacing factor is determined at time of printing, and is defaulted to 0.
    this.leftSpacing = 0;
    // Height is only updated at time of printing, and is defaulted to 0.
    this.height = 0;
    // Sets color to red, unless parent is null (in which case node is the root, and it is set to
    // black)
    if (parent == null) {
      this.color = "B";
    } else {
      this.color = "R";
    }
  }

  /**
   * Bare constructor which only assigns key and value, and sets others to default value.
   * 
   * @param key
   * @param value
   */
  BSTNode(Key key, Value value) {
    this(key, value, null, null, null);
  }

  /**
   * Bare constructor which only assigns key, value, and parent, and sets others to default value.
   * 
   * @param key
   * @param value
   * @param parent
   */
  BSTNode(Key key, Value value, BSTNode<Key, Value> parent) {
    this(key, value, parent, null, null);
  }

  /**
   * Getter for parent node.
   * 
   * @return parent node
   */
  public BSTNode<Key, Value> getParent() {
    return this.parent;
  }

  /**
   * Setter for parent node.
   * 
   * @param node to set to parent.
   */
  public void setParent(BSTNode<Key, Value> node) {
    this.parent = node;
  }

  /**
   * Getter for left child.
   * 
   * @return left child
   */
  public BSTNode<Key, Value> getLeftChild() {
    return this.leftChild;
  }

  /**
   * Setter for left child
   * 
   * @param node to set to left child
   */
  public void setLeftChild(BSTNode<Key, Value> node) {
    this.leftChild = node;
  }

  /**
   * Getter for right child
   * 
   * @return right child
   */
  public BSTNode<Key, Value> getRightChild() {
    return this.rightChild;
  }

  /**
   * Setter for right child
   * 
   * @param node to set to right child
   */
  public void setRightChild(BSTNode<Key, Value> node) {
    this.rightChild = node;
  }

  /**
   * Getter for color
   * 
   * @return color
   */
  public String getColor() {
    return this.color;
  }

  /**
   * Setter for color
   * 
   * @param color
   */
  public void setColor(String color) {
    // Color should only be "B" or "R":
    if (!color.equals("B") && !color.equals("R")) {
      System.out.println("No such color. No change made.");
    } else {
      this.color = color;
    }
  }

  /**
   * Determines if node has left child
   * 
   * @return true if node has left child, false otherwise
   */
  public boolean hasLeftChild() {
    return this.leftChild != null;
  }

  /**
   * Determines if node has right child
   * 
   * @return true is node has right child, false otherwise
   */
  public boolean hasRightChild() {
    return this.rightChild != null;
  }

  /**
   * Determines if node is a leaf node
   * 
   * @return true if node is a leaf node, false otherwise
   */
  public boolean isLeafNode() {
    return !this.hasLeftChild() && !this.hasRightChild();
  }

  /**
   * Determines when node should have zero spacing factor: if it is a leaf node, or it has neither a
   * left child with a right child or a right child with a left child.
   * 
   * @return true if node should have 0 spacing factor, false otherwise
   */
  public boolean noSpacing() {
    if (this.isLeafNode()) {
      return true;
    } else if (this.hasLeftChild() && this.hasRightChild()) {
      return !this.rightChild.hasLeftChild() && !this.leftChild.hasRightChild();
    } else if (this.hasRightChild()) {
      return !this.rightChild.hasLeftChild();
    } else if (this.hasLeftChild()) {
      return !this.leftChild.hasRightChild();
    } else {
      throw new RuntimeException("Error occured when determining spacing factor.");
    }
  }
}

