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
import java.util.*;
import java.lang.Math;

// Enumeration used for keeping track of whether an edge is pointing to a left or right child.
enum Child {
  LEFT, RIGHT
};


/**
 * 
 * Class to implement a Red-Black Tree and printing functionality.
 * 
 * @param <Key>   is the generic type of key
 * @param <Value> is the generic type of value
 */
public class BALST<Key extends Comparable<Key>, Value> implements BALSTADT<Key, Value> {

  private BSTNode<Key, Value> root; // Root node of tree

  private int numKeys; // Number for nodes in tree

  /**
   * Default constructor sets instance variables to default value.
   */
  public BALST() {
    this.root = null;
    numKeys = 0;
  }

  /**
   * Getter for key of root node.
   */
  @Override
  public Key getKeyAtRoot() {
    if (this.root != null) {
      return this.root.key;
    } else {
      return null;
    }
  }

  /**
   * Returns the key of the left child of a given node.
   */
  @Override
  public Key getKeyOfLeftChildOf(Key key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    if (findNodeHelper(root, key) == null) {
      throw new KeyNotFoundException();
    } else {
      return (Key) findNodeHelper(root, key).leftChild.key;
    }
  }

  /**
   * Returns the key of the right child of a given node.
   */
  @Override
  public Key getKeyOfRightChildOf(Key key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    if (findNodeHelper(root, key) == null) {
      throw new KeyNotFoundException();
    } else {
      return (Key) findNodeHelper(root, key).rightChild.key;
    }
  }

  /**
   * Helper method for determining height of tree
   * 
   * @param node beginning height count at
   * @return height up to the height of node
   */
  private int heightHelpter(BSTNode node) {
    // Height of null node is 0. Base case for recursive method.
    if (node == null) {
      return 0;
    } else {
      // Height of a node is 1 + the max of the height of its children
      return (1 + Math.max(heightHelpter(node.leftChild), heightHelpter(node.rightChild)));
    }
  }

  /**
   * Returns height of tree.
   */
  @Override
  public int getHeight() {
    return heightHelpter(this.root);
  }

  /**
   * Helper method for in order traversal
   * 
   * @param currentNode node to begin traversal at
   * @return List of keys in order
   */
  private List<Key> inOrderHelper(BSTNode currentNode) {
    LinkedList<Key> list = new LinkedList<Key>();
    // Base case: null nodes not added to list
    if (currentNode == null) {
      return list;
    }
    // Adds keys of left subtree, root, and then keys of right subtree:
    list.addAll(inOrderHelper(currentNode.leftChild));
    list.add((Key) currentNode.key);
    list.addAll(inOrderHelper(currentNode.rightChild));
    return list;
  }

  /**
   * Returns in order traversal list.
   */
  @Override
  public List<Key> getInOrderTraversal() {
    return inOrderHelper(this.root);
  }

  /**
   * Helper method for in order traversal. Identical to inOrderHelper, but returns list of nodes
   * instead of keys
   * 
   * @param currentNode node to begin traversal at
   * @return List of Nodes in order
   */
  private List<BSTNode> inOrderNodeHelper(BSTNode currentNode) {
    LinkedList<BSTNode> list = new LinkedList<BSTNode>();
    // Base case: null nodes not added to list
    if (currentNode == null) {
      return list;
    }
    // Adds keys of left subtree, root, and then keys of right subtree:
    list.addAll(inOrderNodeHelper(currentNode.leftChild));
    list.add(currentNode);
    list.addAll(inOrderNodeHelper(currentNode.rightChild));
    return list;
  }

  /**
   * Returns list of nodes in order.
   * 
   * @return
   */
  public List<BSTNode> getInOrderTraversalNodes() {
    return inOrderNodeHelper(this.root);
  }

  /**
   * Helper method for preorder traversal.
   * 
   * @param currentNode node to begin traversal at
   * @return list of keys according to pre order traversal order
   */
  private List<Key> preOrderHelper(BSTNode currentNode) {
    LinkedList<Key> list = new LinkedList<Key>();
    // Base case: null nodes not added to list
    if (currentNode == null) {
      return list;
    }
    // Adds root, keys of left subtree, and then keys of right subtree:
    list.add((Key) currentNode.key);
    list.addAll(preOrderHelper(currentNode.leftChild));
    list.addAll(preOrderHelper(currentNode.rightChild));
    return list;
  }

  /**
   * Returns list of keys according to preorder traversal order.
   */
  @Override
  public List<Key> getPreOrderTraversal() {
    return preOrderHelper(this.root);
  }

  /**
   * Helper method for returning postorder traversal.
   * 
   * @param currentNode node traversal begins at
   * @return list of key in postorder traversal order
   */
  private List<Key> postOrderHelper(BSTNode currentNode) {
    LinkedList<Key> list = new LinkedList<Key>();
    // Base case: null nodes not added to list
    if (currentNode == null) {
      return list;
    }
    // Adds keys of left subtree, keys of right subtree, and then the root:
    list.addAll(postOrderHelper(currentNode.leftChild));
    list.addAll(postOrderHelper(currentNode.rightChild));
    list.add((Key) currentNode.key);
    return list;
  }

  /**
   * Returns list of keys in postorder traversal order.
   */
  @Override
  public List<Key> getPostOrderTraversal() {
    return postOrderHelper(this.root);
  }

  /**
   * Returns the keys of a given level.
   * 
   * @param currentNode node with respect to the levels are defined
   * @param level       how many levels beneath currentNode is to be returned
   * @return list of keys of the corresponding level
   */
  private List<Key> givenLevelKeys(BSTNode currentNode, int level) {
    List<Key> list = new LinkedList<Key>();
    if (currentNode == null) {
      return list;
    }
    if (level == 1) {
      list.add((Key) currentNode.key);
      return list;
    } else {
      list.addAll(givenLevelKeys(currentNode.leftChild, level - 1));
      list.addAll(givenLevelKeys(currentNode.rightChild, level - 1));
    }
    return list;
  }

  /**
   * Returns the nodes of a given level
   * 
   * @param currentNode node with respect to the levels are defined
   * @param level       how many levels beneath currentNode is to be returned
   * @return list of keys of the corresponding level
   */
  private List<BSTNode> givenLevelNodes(BSTNode currentNode, int level) {
    List<BSTNode> list = new LinkedList<BSTNode>();
    if (currentNode == null) {
      return list;
    }
    if (level == 1) {
      list.add(currentNode);
      return list;
    } else {
      list.addAll(givenLevelNodes(currentNode.leftChild, level - 1));
      list.addAll(givenLevelNodes(currentNode.rightChild, level - 1));
    }
    return list;
  }

  /**
   * Returns keys in order corresponding to level order traversal.
   */
  @Override
  public List<Key> getLevelOrderTraversal() {
    LinkedList<Key> list = new LinkedList<Key>();
    for (int i = 1; i <= this.getHeight(); ++i) {
      list.addAll(givenLevelKeys(this.root, i));
    }
    return list;
  }

  /**
   * Helper method for inserting node to tree.
   * 
   * @throws RuntTimeException if key is duplicate
   * @param currentNode node method has been called on
   * @param key         value of node's key to be inserted
   * @param value       value of node's data to be inserted
   * @return currentNode
   */
  private BSTNode insertHelper(BSTNode currentNode, BSTNode parentNode, Comparable key, Value value)
      throws DuplicateKeyException {
    // if tree is empty, creates new Black Node and sets it to be the root
    if (currentNode == null) {
      BSTNode newNode = new BSTNode(key, value, parentNode);
      if (this.root == null) {
        this.root = newNode;
      }
      return newNode;
    }
    // if key is less than currentNode.key, insertHelper is recursively called on currentNode's left
    // child
    else if (key.compareTo(currentNode.key) < 0) {
      currentNode.setLeftChild(insertHelper(currentNode.getLeftChild(), currentNode, key, value));
    }
    // if key is greater than currentNode.key, insertHelper is recursively called on currentNode's
    // right child
    else if (key.compareTo(currentNode.key) > 0) {
      currentNode.setRightChild(insertHelper(currentNode.getRightChild(), currentNode, key, value));
    } else {
      // is only reached if key.compareTo(currentNode.key) == 0. Throws exception indicating
      // duplicate key
      throw new DuplicateKeyException();
    }
    return currentNode;
  }

  /**
   * Used to adjust tree after insertion to preserve Red-Black tree properties.
   * 
   * @param currentNode node where there is a potential Red-Black Tree property violation.
   */
  private void fixInsert(BSTNode currentNode) {
    // if node is null or is the root, return
    if (currentNode == null || currentNode.getParent() == null) {
      return;
    }
    // if parent is black, there is no possible violation, so return
    else if (currentNode.getParent().getColor().equals("B")) {
      return;
    } // else parent is red
    else {
      // if parent is a left child and uncle is black/null:
      if (currentNode.getParent().getParent().getLeftChild() == currentNode.getParent()
          && (currentNode.getParent().getParent().getRightChild() == null
              || currentNode.getParent().getParent().getRightChild().getColor().equals("B"))) {
        // If currentNode is a left child (Left-left case), perform right rotation on grandparent
        // and recolor nodes
        if (currentNode.getParent().getLeftChild() == currentNode) {
          // Perform right rotation on currentNode's grandparent:
          BSTNode sibling = currentNode.getParent().getRightChild();
          if (sibling != null) {
            sibling.setParent(currentNode.getParent().getParent());
          }

          currentNode.getParent().setRightChild(currentNode.getParent().getParent());
          currentNode.getParent().getParent().setLeftChild(sibling);

          // If currentNode's grandparent is the root
          if (currentNode.getParent().getParent().getParent() == null) {
            // Set new root to be currentNode's parent
            this.root = currentNode.getParent();
            currentNode.getParent().setParent(null);
          } else { // If currentNode's grandparent is not the root
            // Set parent of currentNode's parent to be currentNode's great-grandparent
            currentNode.getParent().setParent(currentNode.getParent().getParent().getParent());
            // If currentNode's old grandparent had been a left child
            if (currentNode.getParent().getParent().getLeftChild() == currentNode.getParent()
                .getRightChild()) {
              // Set currentNode's new grandparent to be left child of currentNode's great
              // grandparent
              currentNode.getParent().getParent().setLeftChild(currentNode.getParent());
            } else { // If currentNode's old grandparent had been a right child
              // Set currentNode's new grandparent to be right child of currentNode's great
              // grandparent
              currentNode.getParent().getParent().setRightChild(currentNode.getParent());
            }
          }
          // Set new sibling's parent to be currentNode's parent
          currentNode.getParent().getRightChild().setParent(currentNode.getParent());
          // Recolor nodes:
          currentNode.getParent().setColor("B");
          currentNode.getParent().getRightChild().setColor("R");
        } // Else if currentNode is a right child (Left-right case), perform left rotate on parent
          // of currentNode, and then call fixInsert again, which will perform Left-left case:
        else if (currentNode.getParent().getRightChild() == currentNode) {
          // Perform a left rotate on current node's parent:
          // Set currentNode's left child as current node's sibling
          currentNode.getParent().setRightChild(currentNode.getLeftChild());
          // Likewise set parent of currentNode's left child to be currentNode's parent
          if (currentNode.getLeftChild() != null) {
            currentNode.getLeftChild().setParent(currentNode.getParent());
          }

          currentNode.setLeftChild(currentNode.getParent());
          currentNode.setParent(currentNode.getParent().getParent());
          currentNode.getParent().setLeftChild(currentNode);
          currentNode.getLeftChild().setParent(currentNode);

          // Call again on left child to perform Left-left case
          fixInsert(currentNode.getLeftChild());
        }
      } // Else if parent is a left child and uncle is red perform recoloring
      else if (currentNode.getParent().getParent().getLeftChild() == currentNode.getParent()
          && currentNode.getParent().getParent().getRightChild().getColor().equals("R")) {
        // Recolor nodes:
        currentNode.getParent().setColor("B");
        currentNode.getParent().getParent().getRightChild().setColor("B");

        if (this.root != currentNode.getParent().getParent()) {
          currentNode.getParent().getParent().setColor("R");
        }
        // Call again on grandparent to fix potential cascading effects
        fixInsert(currentNode.getParent().getParent());

      } // Else if parent is a right child and uncle is black/null
      else if (currentNode.getParent().getParent().getRightChild() == currentNode.getParent()
          && (currentNode.getParent().getParent().getLeftChild() == null
              || currentNode.getParent().getParent().getLeftChild().getColor().equals("B"))) {
        // If currentNode is a right child (Right-right case), perform right rotate on grandparent a
        // recolor
        if (currentNode.getParent().getRightChild() == currentNode) {
          // Perform right rotate on grandparent:

          BSTNode sibling = currentNode.getParent().getLeftChild();
          if (sibling != null) {
            sibling.setParent(currentNode.getParent().getParent());
          }

          currentNode.getParent().setLeftChild(currentNode.getParent().getParent());
          currentNode.getParent().getParent().setRightChild(sibling);

          // If currentNode's grandparent is the root
          if (currentNode.getParent().getParent().getParent() == null) {
            // Set new root to be currentNode's parent
            this.root = currentNode.getParent();
            currentNode.getParent().setParent(null);
          } else { // If currentNode's grandparent is not the root
            // Set parent of currentNode's parent to be currentNode's great-grandparent
            currentNode.getParent().setParent(currentNode.getParent().getParent().getParent());
            // If currentNode's old grandparent had been a right child
            if (currentNode.getParent().getParent().getRightChild() == currentNode.getParent()
                .getLeftChild()) {
              // Set currentNode's new grandparent to be left child of currentNode's great
              // grandparent
              currentNode.getParent().getParent().setRightChild(currentNode.getParent());
            } else { // If currentNode's old grandparent had been a right child
              // Set currentNode's new grandparent to be right child of currentNode's great
              // grandparent
              currentNode.getParent().getParent().setLeftChild(currentNode.getParent());
            }
          }
          // Set new sibling's parent to be currentNode's parent
          currentNode.getParent().getLeftChild().setParent(currentNode.getParent());
          // Recolor node:
          currentNode.getParent().setColor("B");
          currentNode.getParent().getLeftChild().setColor("R");
        } // If currentNode is a left child (Right-left case), perform right rotate on currentNode's
          // parent, then call fixInsert() again which will perform Right-right case
        else if (currentNode.getParent().getLeftChild() == currentNode) {
          // Perform a right rotate on current node's parent:
          // Set currentNode's right child as current node's sibling
          currentNode.getParent().setLeftChild(currentNode.getRightChild());
          // Likewise set parent of currentNode's right child to be currentNode's parent
          if (currentNode.getRightChild() != null) {
            currentNode.getRightChild().setParent(currentNode.getParent());
          }

          currentNode.setRightChild(currentNode.getParent());
          currentNode.setParent(currentNode.getParent().getParent());
          currentNode.getParent().setRightChild(currentNode);
          currentNode.getRightChild().setParent(currentNode);
          // Call again on right child to perform Right-right case
          fixInsert(currentNode.getRightChild());
        }
      } // Else if parent is a right child and uncle is red perform recoloring
      else if (currentNode.getParent().getParent().getRightChild() == currentNode.getParent()
          && currentNode.getParent().getParent().getLeftChild().getColor().equals("R")) {
        // Recolor node:
        currentNode.getParent().setColor("B");
        currentNode.getParent().getParent().getLeftChild().setColor("B");

        if (this.root != currentNode.getParent().getParent()) {
          currentNode.getParent().getParent().setColor("R");
        }
        // Call again on grandparent to fix potential cascading effects
        fixInsert(currentNode.getParent().getParent());
      }
    }
  }

  /**
   * Inserts nodes into tree.
   * 
   * @param key   of node to be inserted
   * @param value of node to be inserted
   * @throws IllegalNullKeyException if key is null
   * @throws DuplicateKeyException   if key is already present in tree
   */
  @Override
  public void insert(Key key, Value value) throws IllegalNullKeyException, DuplicateKeyException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    // Inserts new node according to standard binary search tree algorithm
    insertHelper(this.root, null, key, value);
    try {
      // Rebalances tree to preserve Red-Black Tree properties
      fixInsert(findNodeHelper(this.root, key));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    ++this.numKeys;
  }

  /**
   * Helper method for removeHelpter to find smallest node in subtree defined by a given node. Used
   * to find in-order successor.
   * 
   * @param currentNode which forms the root of the subtree
   * @return Node which is the smallest in the subtree
   */
  private BSTNode minNodeInTree(BSTNode currentNode) {
    // if root has null left child, then the root is the smallest value, and it is returned
    if (currentNode.leftChild == null) {
      return currentNode;
    } else {
      // continues down the line of left children until a node is found whose left child is null
      while (currentNode.leftChild != null) {
        currentNode = currentNode.leftChild;
      }
      return currentNode;
    }
  }

  /**
   * Helper method for remove. Removes node and decrements size if successful.
   * 
   * @param currentNode Node which method has been called on
   * @param key         key value of node to be removed
   * @throws KeyNotFoundException if no node with key is present in tree
   * @return Node which is to replace the spot of currentNode after Node is removed (will still be
   *         currentNode if it was not the one removed)
   */
  private BSTNode removeHelper(BSTNode currentNode, Comparable key) throws KeyNotFoundException {
    // if currentNode is null, then Node with key is not present, so throw exception
    if (currentNode == null) {
      throw new KeyNotFoundException();
    }
    // // if key is less than currentNode.key, removeHelper is recursively called on currentNode's
    // left child
    if (key.compareTo(currentNode.key) < 0) {
      currentNode.setLeftChild(removeHelper(currentNode.leftChild, key));
    }
    // if key is greater than currentNode.key, removeHelper is recursively called on currentNode's
    // right child
    else if (key.compareTo(currentNode.key) > 0) {
      currentNode.setRightChild(removeHelper(currentNode.rightChild, key));
    } // if currentNode is the Node to be removed
    else {
      // if currentNode has null left child, then it can be removed by replacing its reference with
      // its right child
      if (currentNode.getLeftChild() == null) {
        --this.numKeys;
        return currentNode.getRightChild();
      } // if currentNode has null right child, then it can be removed by replacing its reference
        // with its left child
      else if (currentNode.getRightChild() == null) {
        --this.numKeys;
        return currentNode.getLeftChild();
      } // if neither right nor left child are null
      else {
        // in order successor is the minimum Node in the subtree defined by currentNode's right
        // child
        BSTNode inOrderSuccessor = minNodeInTree(currentNode.getRightChild());
        // replaces information of currentNode with that of its in order successor
        currentNode.key = inOrderSuccessor.key;
        currentNode.value = inOrderSuccessor.value;
        // removes in order successor (whose left child must be null- therefore program will
        // terminate)
        currentNode.setRightChild(removeHelper(currentNode.rightChild, (Key) inOrderSuccessor.key));
      }
    }
    // Returns node with updated instance fields
    return currentNode;
  }


  /**
   * Removes node with specified key.
   * 
   * @param key of node to be removed
   * @throws IllegalNullKeyException if key is null
   * @throws KeyNotFoundException    if no node with key is present in tree
   * @return true if node is successfully removed, false otherwise.
   */
  @Override
  public boolean remove(Comparable key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    } else {
      // saves sizes before removeHelper is called
      int beginningSize = this.numKeys;
      // calls removeHelper on root Node
      this.root = removeHelper(this.root, key);
      // returns true if size was decremented, and false otherwise
      if (this.numKeys < beginningSize) {
        return true;
      } else {
        return false;
      }
    }
  }

  /**
   * Helper method for finding node in tree.
   * 
   * @param currentNode node currently being looked at
   * @param key         key being searched for
   * @throws KeyNotFoundException if node with key value is not found
   * @return node being searched for, or null if not found
   */
  private BSTNode findNodeHelper(BSTNode currentNode, Comparable key) throws KeyNotFoundException {
    // return null if currentNode is null, which happens iff no Node with "key" value is present
    if (currentNode == null) {
      throw new KeyNotFoundException();
    } // if key is less than currentNode.key, findNodeHelper is recursively called on currentNode's
    // left child
    else if (key.compareTo(currentNode.key) < 0) {
      return findNodeHelper(currentNode.leftChild, key);
    } // if key is greater than currentNode.key, findNodeHelper is recursively called on
      // currentNode's
    // right child
    else if (key.compareTo(currentNode.key) > 0) {
      return findNodeHelper(currentNode.rightChild, key);
    } // reached iff currentNode's key is equal to key
    else {
      return currentNode; // returns current Node
    }
  }

  /**
   * Returns value associated with key
   * 
   * @param key of node whose value is to be returned
   * @throws IllegalNullKeyException if key is null
   * @throws KeyNotFoundException    if node with key value is not found
   * @returns value of node with given key
   */
  @Override
  public Value get(Key key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    return (Value) findNodeHelper(this.root, key).value;
  }

  /**
   * Returns whether or not a node with the given key is present in the tree
   * 
   * @param key of node being searched for
   * @returns true if node with given key is present, false otherwise
   */
  @Override
  public boolean contains(Key key) throws IllegalNullKeyException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    try {
      findNodeHelper(this.root, key);
    } catch (KeyNotFoundException e) {
      return false;
    }
    return true;
  }

  /**
   * Getter for the size of the tree
   * 
   * @returns number of keys in tree
   */
  @Override
  public int numKeys() {
    return this.numKeys;
  }

  /**
   * Given that the spacing factor of descendants have been filled in, determines how many spaces to
   * the left descendants of the node go. Due to ordering property of representation of tree, this
   * is equal to summing the spacing factor of each left descendants.
   * 
   * @param currentNode node in question
   * @return right spacing factor of currentNode
   */
  private int rightFactor(BSTNode currentNode, int charsPerKey) {
    // If the currentNode is null, then it has 0 right descendants
    if (currentNode == null) {
      return 0;
    }
    int count = currentNode.spacingFactor + charsPerKey;
    // For each right descendant, add spacing factor to count
    while (currentNode.rightChild != null && currentNode.rightChild.hasRightChild()) {
      currentNode = currentNode.rightChild;
      count += currentNode.spacingFactor + charsPerKey;
    }
    return count;
  }

  /**
   * Given that the spacing factor of descendants have been filled in, determines how many spaces to
   * the left descendants of the node go. Due to ordering property of representation of tree, this
   * is equal to summing the spacing factor of each left descendants.
   * 
   * @param currentNode node in question
   * @return left spacing factor of currentNode
   */
  private int leftFactor(BSTNode currentNode, int charsPerKey) {
    // If the currentNode is null, then it has 0 left descendants
    if (currentNode == null) {
      return 0;
    }
    int count = currentNode.spacingFactor + charsPerKey;
    // For each left descendant, add spacing factor plus number of characters printed to count
    while (currentNode.leftChild != null && currentNode.leftChild.hasLeftChild()) {
      currentNode = currentNode.leftChild;
      count += currentNode.spacingFactor + charsPerKey;
    }
    return count;
  }


  /**
   * Determines length of String used to represent each node in tree. Equal the maximum length of
   * String representation of keys, plus 2 for the characters which will denote Red or Black.
   * 
   * @return length of String used to represent each node in tree
   */
  int charsPerKey() {
    final int NUM_OF_SPECIAL_CHARS = 2; // Number of chars used to indicate Red or Black
    List<BSTNode> inOrderList = getInOrderTraversalNodes(); // List of nodes in order
    // Determines maximum length of keys
    int maxKeyLength = 0;
    for (BSTNode node : inOrderList) {
      if (node.key.toString().length() > maxKeyLength) {
        maxKeyLength = node.key.toString().length();
      }
    }
    return maxKeyLength + NUM_OF_SPECIAL_CHARS; // Returns max length plus 2
  }

  /**
   * Updates the spacing factor for each node. "Spacing factor" refers to the minimal amount of
   * horizontal/vertical space (these are equal by assumption) between the node and its children to
   * prevent the node's descendant from crossing underneath the node. Furthermore, for pretty print,
   * the spacing factor must be the same for each node on the same level, so the spacing factor is
   * actually the maximum of each level.
   * 
   * @return length of String used to represent each node in tree
   */
  int fillSpacingFactors() {
    int charsPerKey = this.charsPerKey(); // length of String used to represent each node in tree.
    // Iterates through each level of tree, from bottom to root.
    for (int level = this.getHeight(); level >= 1; --level) {
      // Puts current level into list
      List<BSTNode> currentLevel = givenLevelNodes(this.root, level);
      int maxSpacing = 0;
      // Iterates through each node in level:
      for (BSTNode node : currentLevel) {
        // If node is a leaf node, or has neither a left child with a right child nor a right child
        // with the a left child, automatically sets spacingFactor to 0. Ensures that spacing factor
        // is non zero only when necessary.
        if (node.noSpacing()) {
          node.spacingFactor = 0;
        } else {
          // Sets spacing factor to be maximum of rightFactor of leftChild and leftFactor of
          // rightChild. This is the minimum value which allows for symmetry (both children are
          // equally spaced from parent) while ensuring the no descendant from either side crosses
          // underneath.
          node.spacingFactor = Math.max(rightFactor(node.leftChild, charsPerKey),
              leftFactor(node.rightChild, charsPerKey));
        }
        // Keeps track of maximum spacing factor
        if (node.spacingFactor > maxSpacing) {
          maxSpacing = node.spacingFactor;
        }
        node.height = this.getHeight() - level; // Updates height, be used to determine leftSpacing
      }
      // Sets the spacing factor of each node in level to be the maximum. Is the minimum value that
      // ensures both that no descendant crosses underneath and that all nodes on a given level are
      // the same distance from their parents.
      for (BSTNode node : currentLevel) {
        node.spacingFactor = maxSpacing;
      }
    }
    return charsPerKey;
  }

  /**
   * Updates the left spacing for each node. "Left spacing" of a node is how many characters to the
   * left of the node there should be already be when the key is added to the appropriate line.
   * 
   * @return length of String used to represent each node in tree
   */
  int leftSpacing() {
    int charsPerKey = this.fillSpacingFactors(); // Fills spacing factors of each node

    List<BSTNode> inOrderList = getInOrderTraversalNodes(); // List of nodes in order
    BSTNode<Key, Value> previous = null; // Previous node
    BSTNode<Key, Value> current = null; // Current node
    ListIterator<BSTNode> listIt = inOrderList.listIterator();
    // Iterates through nodes in order
    while (listIt.hasNext()) {
      // If node is not the first in order
      if (listIt.hasPrevious()) {
        // Save reference to previous node, and continue iterating through list
        previous = (BSTNode) listIt.previous();
        listIt.next();
        current = listIt.next();
        // Assign left spacing based upon relationship between current and previous node (note that
        // these four options are exhaustive if the tree is a valid binary search tree):
        if (previous.getParent() == current) {
          // If current node is parent of previous node, then set current's leftSpacing to be
          // previous's leftSpacing, plus current's spacingFactor, plus the number of characters per
          // key
          current.leftSpacing = previous.leftSpacing + current.spacingFactor + charsPerKey;
        } else if (current.getParent() == previous) {
          // If previous node is parent of current node, then set current's leftSpacing to be
          // previous's leftSpacing, plus previous's spacingFactor, plus the number of characters
          // per
          // key
          current.leftSpacing = previous.leftSpacing + previous.spacingFactor + charsPerKey;
        } else if (current.height < previous.height) {
          // If previous and current are not parent and children and previous is higher in the tree
          // than current, then set current's leftSpacing to be previous's leftSpacing, plus the
          // difference between previous's spacingFactor and the leftFactor of previous's right
          // child, plus the number of characters per key
          current.leftSpacing = previous.leftSpacing
              + (previous.spacingFactor - leftFactor(previous.rightChild, charsPerKey))
              + charsPerKey;
        } else if (current.height > previous.height) {
          // If previous and current are not parent and children and current is higher in the tree
          // than previous, then set current's leftSpacing to be previous's leftSpacing, plus the
          // difference between previous's spacingFactor and the leftFactor of previous's right
          // child, plus the number of characters per key
          current.leftSpacing = previous.leftSpacing + charsPerKey
              + (current.spacingFactor - rightFactor(current.leftChild, charsPerKey));
        } else {
          throw new RuntimeException("Failed filling left spacing: tree misstructured.");
        }
      } else {
        // If node is the first in order, then left spacing is 0. Continue to next node.
        current = listIt.next();
        current.leftSpacing = 0;
      }
    }
    return charsPerKey;
  }


  /**
   * Returns desired String representation of node by appending the appropriate number of 0's the
   * key and "*"s Red nodes and "+"s to Black nodes.
   * 
   * @param node        to be printed
   * @param charsPerKey desired total length of String to be returned
   * @return String representation of key of node in desired format
   */
  String nodeToString(BSTNode node, int charsPerKey) {
    String keyPrint = node.key.toString(); // Gets String representation of key
    // Appends the appropriate number of 0s to String
    for (int i = 0; i < (charsPerKey - keyPrint.length() - 2); ++i) {
      keyPrint = "0" + keyPrint;
    }
    // Adds the appropriate symbols to denote Red vs. Black node:
    if (node.color.equals("R")) { // If Red node
      keyPrint = "*" + keyPrint + "*";
    } else { // If Black node
      keyPrint = "+" + keyPrint + "+";
    }
    return keyPrint;
  }

  /**
   * Prints the tree in the most compact possible way with the following properties: all nodes on
   * the same level in the tree are printed on the same line, children of a given node are
   * equidistant from it, parents are connected by as many dashes as they are spaced apart
   * horizontally/vertically, and nodes can be read in order from left to right such that node A is
   * displayed to the right of node B if and only if node A's key is greater than node B's key. This
   * is achieved using a greedy algorithm.
   */
  @Override
  public void print() {
    // Prints key for interpreting results.
    System.out.print("Note: \"*\" denotes Red node, while \"+\" denotes Black node.\n\n");

    // Fills in leftSpacing of each node and saves length of String used to represent each node in
    // tree
    int charsPerKey = this.leftSpacing();

    List<BSTNode> currentLevel; // Current level of nodes being printed
    StringBuilder lineString; // StringBuilder to become current line
    StringBuilder edgeLine; // StringBuilder of current line of dashes
    BSTNode currentNode; // Current node to be added to lineString
    BSTNode previousNode; // Previous node added to lineString
    int prevLeftSpace; // Number of left spaces already printed
    int levelSpacingFactor; // Spacing factor (spacing between node and its children) of given line
    // List of edges currently being added, each represented as an array where the first index
    // indicates if edge leads to a left or right child, and second index indicates the left spacing
    ArrayList<Object[]> currentEdges;

    // Iterates through levels, beginning at the top (root) and working down
    for (int i = 1; i <= this.getHeight(); ++i) {
      lineString = new StringBuilder(); // Clear and initialize lineString
      currentLevel = givenLevelNodes(this.root, i);
      // List to add edges coming from current level to
      currentEdges = new ArrayList<Object[]>();
      // Iterates through nodes in level
      ListIterator listIt = currentLevel.listIterator();
      while (listIt.hasNext()) {
        currentNode = (BSTNode) listIt.next();
        // Adds edges to currentEdges, including whether edge corresponds to a left or right child
        // and the starting left spacing
        if (currentNode.hasLeftChild()) {
          currentEdges.add(new Object[] {Child.LEFT, currentNode.leftSpacing});
        }
        if (currentNode.hasRightChild()) {
          currentEdges.add(new Object[] {Child.RIGHT, currentNode.leftSpacing + charsPerKey - 1});
        }
        // If currentNode is not the first in the list
        if (listIt.previousIndex() > 0) {
          // Sets prevLeftSpace to be the leftSpacing of the previous node plus the number of
          // characters printed
          listIt.previous();
          previousNode = (BSTNode) listIt.previous();
          prevLeftSpace = previousNode.leftSpacing + charsPerKey;
          listIt.next();
          listIt.next();
        } else {
          // Nothing has yet been printed, so set prevLeftSpace to 0
          prevLeftSpace = 0;
        }
        // Append the appropriate number of spaces such that currentNode.leftSpacing characters have
        // been appended to the current line
        for (int j = 1; j <= (currentNode.leftSpacing - prevLeftSpace); ++j) {
          lineString.append(" ");
        }
        // Append String representation of currentNode
        lineString.append(nodeToString(currentNode, charsPerKey));
      }
      System.out.println(lineString); // Print line which has just been constructed

      // Gets spacing factor of line to determine number of dashes to print to represent edges.
      // Spacing factor is constant in a given line, so choice of node is arbitrary.
      levelSpacingFactor = currentLevel.get(0).spacingFactor;
      int currDashSpace; // Desired left spacing of current dash
      int numLeftSpace; // Number of characters thus far printed on current line
      // Iterates levelSpacingFactor number of times
      for (int j = 0; j < levelSpacingFactor; ++j) {
        edgeLine = new StringBuilder(); // Clear and initialize edge Line
        if (!currentEdges.isEmpty()) {
          // Iterates through edges determined from current line
          for (Object[] child : currentEdges) {
            // Decrements left children and increments right children, representing the direction
            // which they are "traveling"
            if (child[0] == Child.LEFT) {
              child[1] = (Integer) child[1] - 1;
            } else {
              child[1] = (Integer) child[1] + 1;
            }
          }
          // Initializes numLeftSpace to 0, as nothing as yet been printed on the current line
          numLeftSpace = 0;
          // Iterates through edges again
          for (Object[] child : currentEdges) {
            // Sets currDashSpace to be the desired left spacing of the current dash
            currDashSpace = (int) child[1];
            // Adds the appropriate number of spaces before dash
            while (numLeftSpace < currDashSpace) {
              edgeLine.append(" ");
              ++numLeftSpace;
            }
            // Adds the appropriate dash to represent given edge and increment numLeftSpace
            if (child[0] == Child.LEFT) {
              edgeLine.append("/");
              ++numLeftSpace;
            } else {
              edgeLine.append("\\");
              ++numLeftSpace;
            }
          }
          System.out.println(edgeLine); // Print the given edge line
        }
      }
    }
  }

  /**
   * DEMONSTRATION: Main driver used to create tree, add nodes, and print tree.
   * 
   * @param args command-line arguments not used
   */
  public static void main(String[] args) {
    // Creates tree using Integer key and value
    BALST<Integer, Integer> tree = new BALST<Integer, Integer>();
    // Adds natural numbers up through 15 to tree
    try {
      for (int i = 1; i <= 14; ++i) {
        tree.insert(i, i);
      }
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
    tree.print(); // Displays tree to console
  }
}
