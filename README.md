# Red-Black-Tree-Visualizer
Implements a Red-Black tree, including functionality for printing tree in console for easy visualization.

This is an extension of class project for UW-Madison CS 400. We were assigned to design and implement a Red-Black tree from scratch; all 
file names and relevant method headers were provided. We were also tasked with printing the information of the tree in console under ideal 
conditions; however, I was disappointed with the aesthetics and clarity of simple methods or those found online. The algorithm used in the 
print() method, along with its helper methods, is an algorithm which I developed for visualizing the Red-Black tree as clearly as possible. 
This clarity comes as the cost of losing compactness (despite my algorithm being a greedy one which displays the tree as compactly as 
possible while adhering to the desired properties), although I believe my algorithm is the best one for visualizing small-medium trees, 
which is the intended use-case.

Displays tree as shown:

Note: "*" denotes Red node, while "+" denotes Black node.

                    +04+
                   /    \
                  /      \
                 /        \
                /          \
               /            \
              /              \
             /                \
            /                  \
        +02+                    +06+
       /    \                  /    \
      /      \                /      \
     /        \              /        \
    /          \            /          \
+01+            +03+    +05+            \*08\*
                                    +07+    +09+
                                                \*10\*
