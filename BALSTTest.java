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


import static org.junit.Assert.fail;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


@SuppressWarnings("rawtypes")
public class BALSTTest {

  BALST<String, String> balst1;
  BALST<Integer, String> balst2;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    balst1 = createInstance();
    balst2 = createInstance2();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    balst1 = null;
    balst2 = null;
  }

  protected BALST<String, String> createInstance() {
    return new BALST<String, String>();
  }

  protected BALST<Integer, String> createInstance2() {
    return new BALST<Integer, String>();
  }

  /**
   * Insert three values in sorted order and then check the root, left, and right keys to see if
   * rebalancing occurred.
   */
  @Test
  void testBALST_001_insert_sorted_order_simple() {
    try {
      balst2.insert(10, "10");
      if (!balst2.getKeyAtRoot().equals(10))
        fail("Red-Black insert at root does not work");

      balst2.insert(20, "20");
      if (!balst2.getKeyOfRightChildOf(10).equals(20))
        fail("Red-Black insert to right child of root does not work");

      balst2.insert(30, "30");
      Integer k = balst2.getKeyAtRoot();
      if (!k.equals(20))
        fail("Red-Black rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception Red-Black 001: " + e.getMessage());
    }
  }


  /**
   * Insert three values in reverse sorted order and then check the root, left, and right keys to
   * see if rebalancing occurred in the other direction.
   */
  @Test
  void testBALST_002_insert_reversed_sorted_order_simple() {

    try {
      balst2.insert(30, "30");
      balst2.insert(20, "20");
      balst2.insert(10, "10");
      if (!balst2.getKeyAtRoot().equals(20))
        fail("Red-Black rotate does not work. Wrong root.");
      if (!balst2.getKeyOfRightChildOf(20).equals(30))
        fail("Red-Black rotate does not work. Wrong right child root.");
      if (!balst2.getKeyOfLeftChildOf(20).equals(10))
        fail("Red-Black rotate does not work. Wrong left child root.");

      Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception Red-Black 002: " + e.getMessage());
    }

  }

  /**
   * Insert three values so that a right-left rotation is needed to fix the balance.
   * 
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred in the other
   * direction.
   */
  @Test
  void testBALST_003_insert_smallest_largest_middle_order_simple() {

    try {
      balst1.insert("30", "30");
      balst1.insert("50", "50");
      balst1.insert("40", "40");
      if (!balst1.getKeyAtRoot().equals("40"))
        fail("Red-Black rotate does not work. Wrong root.");
      if (!balst1.getKeyOfRightChildOf("40").equals("50"))
        fail("Red-Black rotate does not work. Wrong right child root.");
      if (!balst1.getKeyOfLeftChildOf("40").equals("30"))
        fail("Red-Black rotate does not work. Wrong left child root.");

      Assert.assertEquals(balst1.getKeyAtRoot(), "40");
      Assert.assertEquals(balst1.getKeyOfLeftChildOf("40"), "30");
      Assert.assertEquals(balst1.getKeyOfRightChildOf("40"), "50");
      
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception Red-Black 003: " + e.getMessage());
    }

  }

  /**
   * Insert three values so that a left-right rotation is needed to fix the balance.
   * 
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred in the other
   * direction.
   */
  @Test
  void testBALST_004_insert_largest_smallest_middle_order_simple() {

    try {
      balst1.insert("60", "60");
      balst1.insert("20", "20");
      balst1.insert("30", "30");
      if (!balst1.getKeyAtRoot().equals("30"))
        fail("Red-Black rotate does not work. Wrong root.");
      if (!balst1.getKeyOfRightChildOf("30").equals("60"))
        fail("Red-Black rotate does not work. Wrong right child root.");
      if (!balst1.getKeyOfLeftChildOf("30").equals("20"))
        fail("Red-Black rotate does not work. Wrong left child root.");

      Assert.assertEquals(balst1.getKeyAtRoot(), "30");
      Assert.assertEquals(balst1.getKeyOfLeftChildOf("30"), "20");
      Assert.assertEquals(balst1.getKeyOfRightChildOf("30"), "60");

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception Red-Black 004: " + e.getMessage());
    }

  }

  @Test
  void testBALST_005_test_height() {

    try {
      balst2.insert(60, "60");
      Assert.assertEquals(balst2.getHeight(), 1);
      balst2.insert(20, "20");
      Assert.assertEquals(balst2.getHeight(), 2);
      balst2.insert(30, "30");
      Assert.assertEquals(balst2.getHeight(), 2);
      balst2.insert(10, "10");
      Assert.assertEquals(balst2.getHeight(), 3);

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception Red-Black 005: " + e.getMessage());
    }
  }

  @Test
  void testBALST_006_one_node() {
    try {
      balst2.insert(60, "60");
      Assert.assertEquals(balst2.getHeight(), 1);
      Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(60));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception Red-Black 006: " + e.getMessage());
    }
  }

  @Test
  void testBALST_007_many_inserts_still_get() {
    try {
      for (Integer i = 10; i <= 99; ++i) {
        balst1.insert(i.toString(), i.toString());
      }
      for (Integer i = 10; i <= 99; ++i) {
        Assert.assertEquals(balst1.get(i.toString()), i.toString());
      }

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception Red-Black 007: " + e.getMessage());
    }
  }

  @Test
  void testBALST_008_delete() {
    try {
      for (Integer i = 10; i <= 99; ++i) {
        balst1.insert(i.toString(), i.toString());
      }
      for (Integer i = 10; i <= 99; ++i) {
        balst1.remove(i.toString());
      }
      Assert.assertEquals(balst1.getHeight(), 0);
      Assert.assertEquals(balst1.numKeys(), 0);

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception Red-Black 007: " + e.getMessage());
    }
  }

}
