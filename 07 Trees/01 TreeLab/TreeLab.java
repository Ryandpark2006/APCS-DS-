// Name:Ryan Park
// Date:1/31/22 

import java.util.*;

public class TreeLab
{
   public static TreeNode root = null;
   public static String s = "XCOMPUTERSCIENCE";
   //public static String s = "XThomasJeffersonHighSchool"; 
   //public static String s = "XAComputerScienceTreeHasItsRootAtTheTop";
   //public static String s = "XA";   //comment out lines 44-46 below
   //public static String s = "XAF";  //comment out lines 44-46 below
   //public static String s = "XAFP";  //comment out lines 44-46 below
   //public static String s = "XDFZM";  //comment out lines 44-46 below 
   
   public static void main(String[] args)
   {
      root = buildTree( s );  //we are building trees of Strings only!
      System.out.print( display( root, 0) );
   
      System.out.print("\nPreorder: " + preorderTraverse(root));
      System.out.print("\nInorder: " + inorderTraverse(root));
      System.out.print("\nPostorder: " + postorderTraverse(root));
   
      System.out.println("\n\nNodes = " + countNodes(root));
      System.out.println("Leaves = " + countLeaves(root));
      System.out.println("Only children = " + countOnlys(root));
      System.out.println("Grandparents = " + countGrandParents(root));
   
      System.out.println("\nHeight of tree = " + height(root));
      System.out.println("Longest path = " + longestPath(root));
      System.out.println("Min = " + min(root));
      System.out.println("Max = " + max(root));	
   
      System.out.println("\nBy Level: ");
      System.out.println(displayLevelOrder(root));
   }
 
 /*  students, do not try to understand this method.
     */
   public static TreeNode buildTree(String s)
   {
      root = new TreeNode("" + s.charAt(1), null, null);
      for(int pos = 2; pos < s.length(); pos++)
         insert(root, "" + s.charAt(pos), pos, 
            (int)(1 + Math.log(pos) / Math.log(2)));
   
      insert(root, "AAA", 17, 5); 
      insert(root, "BBB", 18, 5); 
      insert(root, "CCC", 37, 6); //BBB's right child
      return root;
   }
   
    /*  students, do not try to understand this method.
     */
   public static void insert(TreeNode t, String s, int pos, int level)
   {
      TreeNode p = t;
      for(int k = level - 2; k > 0; k--)
      {
         if((pos & (1 << k)) == 0)
            p = p.getLeft();
         else
            p = p.getRight();
      }
      if((pos & 1) == 0)
         p.setLeft(new TreeNode(s, null, null));
      else
         p.setRight(new TreeNode(s, null, null));
   }
   
// tilt your head towards your left shoulder to see the tree
   public static String display(TreeNode t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
   
   public static String preorderTraverse(TreeNode t)
   { 
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += t.getValue() + " ";              //process root
      toReturn += preorderTraverse(t.getLeft());   //recurse left
      toReturn += preorderTraverse(t.getRight());  //recurse right
      return toReturn;
   }
   
   public static String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += inorderTraverse(t.getLeft());
      toReturn += t.getValue() + " ";
      toReturn += inorderTraverse(t.getRight());
      return toReturn;
      	       						 		//recurse left
            				 					//process root
                								//recurse right
   }
   
   public static String postorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += postorderTraverse(t.getLeft());
      toReturn += postorderTraverse(t.getRight());
      toReturn += t.getValue() + " ";
      return toReturn;
   }
   
   public static int countNodes(TreeNode t)
   {
      int nodes = 0;
      if(t == null)
         return 0;
      nodes += countNodes(t.getLeft());
      nodes += 1;
      nodes += countNodes(t.getRight());
      return nodes;
   }
   
   public static int countLeaves(TreeNode t)
   {
      int leaves = 0;
      if(t == null)
         return 0;
      leaves += countLeaves(t.getLeft());
      if(t.getLeft() == null && t.getRight() == null)
         leaves += 1;
      else 
         leaves += 0;
      leaves += countLeaves(t.getRight());
      return leaves;
   }
   
   /*  hard way: use t.getLeft().getLeft(), etc.
       clever way:  use height(t)
       */   
   public static int countGrandParents(TreeNode t)
   {
      int grand = 0;
      if(t == null)
         return 0;
      grand += countGrandParents(t.getLeft());
      if(t.getLeft() != null)
         if(t.getLeft().getLeft() != null || t.getLeft().getRight() != null)
            grand += 1;
         else if(t.getRight() != null)
            if(t.getRight().getLeft() != null || t.getRight().getRight() != null)
               grand += 1;
      grand += countGrandParents(t.getRight());
      return grand;
   }
   
   public static int countOnlys(TreeNode t)
   {
      int onlys = 0;
      if(t == null) 
         return 0;
      onlys += countOnlys(t.getLeft());
      if(t.getLeft() != null && t.getRight() == null)
         onlys += 1;
      else if(t.getLeft() == null && t.getRight() != null)
         onlys += 1;
      onlys += countOnlys(t.getRight());
      return onlys;
   }
   
  /** returns the max of the heights to the left and the heights to the right  
      returns -1 in case the tree is null
    */
   public static int height(TreeNode t)
   {
      if(t == null)
         return -1;
      else
      {
         int left = 0;
         int right = 0;
         left = height(t.getLeft());
         right = height(t.getRight());
      
         int height = Math.max(left, right);
         return height + 1;
      }
   }
   
   /* The length of the longest path connecting any two nodes.  
      Usually connects two bottom-most leaves in the tree.  
      Often goes through root, but not always. 
   */
   public static int longestPath(TreeNode t)
   {
      int left = 0;
      int right = 0;
      int path = 2;
//       path += longestPath(t.getLeft());
//       path += longestPath(t.getRight());
      if(t.getLeft() != null)
      {
         if(height(t.getLeft()) == 0)
            left = 1;
         else
            left = height(t.getLeft());
      }
      if(t.getRight() != null)
      {
         if(height(t.getRight()) == 0)
            right = 1;
         else 
            right = height(t.getRight());
      }
      path += left + right;
      return path;
   }
   
   @SuppressWarnings("unchecked")//this removes the warning message
   /*  Objects in a TreeNode must be cast to String or Comparable 
           in order to call .compareTo  
       */
   public static String min(TreeNode t)
   {
      return min(t, ""+t.getValue());  //calls the private recursive methdod
   }
   private static String min(TreeNode t, String min)
   {
      String minimum1 = "";
      String minimum2 = "";
      if(t == null)
         return min;
      if((t.getValue() + "").compareTo(min) < 0)
        min = "" +  t.getValue();
      minimum1 = min(t.getLeft(), min);
      minimum2 = min(t.getRight(), min);
      if(minimum1.compareTo(minimum2) < 0)
         return minimum1;
      else
         return minimum2;   //just to compile
   }
   
   @SuppressWarnings("unchecked")//this removes the warning message
   /*  Objects in a TreeNode must be cast to String or Comparable 
           in order to call .compareTo  */
   public static String max(TreeNode t)
   {
      return max(t, ""+t.getValue());
   }
   private static String max(TreeNode t, String max)
   {
      String maximum1 = "";
      String maximum2 = "";
      if(t == null)
         return max;
      if((t.getValue() + "").compareTo(max) > 0)
        max = "" +  t.getValue();
      maximum1 = max(t.getLeft(), max);
      maximum2 = max(t.getRight(), max);
      if(maximum1.compareTo(maximum2) > 0)
         return maximum1;
      else
         return maximum2;
   }
      
  /* this method is not recursive.  Use a local queue
     to store the children, if they exist, of the current TreeNode.
     */
   public static String displayLevelOrder(TreeNode t)
   {
      Queue<TreeNode> tree = new LinkedList<TreeNode>();
      String display = "";
      if(t == null)
         return "";
      tree.add(t);
      int size = tree.size();
      while(!tree.isEmpty())
      {
         size = tree.size();
         TreeNode n = tree.remove();
         display += n.getValue() + " ";
         if(n.getLeft() != null)
            tree.add(n.getLeft());
         if(n.getRight() != null)
            tree.add(n.getRight());
         size -= 1;
      }
      return display;
   }
}

/***************************************************
  
       			E
 		E
 			C
 	M
 			N
 		T
 			E
 C
 			I
 		U
 			C
 	O
 			S
 					CCC
 				BBB
 		P
 				AAA
 			R
 
 Preorder: C O P R AAA S BBB CCC U C I M T E N E C E 
 Inorder: R AAA P BBB CCC S O C U I C E T N M C E E 
 Postorder: AAA R CCC BBB S P C I U O E N T C E E M C 
 
 Nodes = 18
 Leaves = 8
 Only children = 3
 Grandparents = 5
 
 Height of tree = 5
 Longest path = 8
 Min = AAA
 Max = U
 
 By Level: 
 C O M P U T E R S C I E N C E AAA BBB CCC 
     
 /*******************************************************/

