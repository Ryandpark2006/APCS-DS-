// Name:Ryan Park 
// Date:2/15/22 

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);
   //public void addBalanced(String obj);  //BST_AVL
   public void remove(String obj);    
   //public void removeBalanced(String obj); //extra lab Red_Black
   public String min();
   public String max();
   public String display();
   public String toString();
}

/*******************
BST. Implement the remove() method.
Test it with BST_Remove_Driver.java
**********************/
public class BST implements BSTinterface
{
   /*  copy your BST code here  */
   private TreeNode root;
   private int size;
   public BST()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
      return size;
   }
   public TreeNode getRoot()   //for Grade-It
   {
      return root;
   }
   /***************************************N
   @param s -- one string to be inserted
   ****************************************/
   public void add(String s) 
   {
      root = add(root, s);
      size++;
   }
   private TreeNode add(TreeNode t, String s) //recursive helper method
   {      
      if(t == null)
         return new TreeNode(s);
      else if(s.compareTo("" + t.getValue()) <= 0)  //s <= t
         t.setLeft(add(t.getLeft(), s));
      else if(s.compareTo("" + t.getValue()) > 0)
         t.setRight(add(t.getRight(), s));
      return t;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   private String display(TreeNode t, int level) //recursive helper method
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
   
   public boolean contains( String obj)
   {
      return contains(root, obj);
   }
   private boolean contains(TreeNode t, String x) //recursive helper method
   {
      if(t == null)
         return false;
      if(("" + t.getValue()).compareTo(x) > 0)
         return contains(t.getLeft(), x);
      else if(("" + t.getValue()).compareTo(x) < 0)
         return contains(t.getRight(), x);
      return true;
   }
   
   public String min()
   {
      if(root == null)
         return null;
      return min(root);
   }
   private String min(TreeNode t)  //use iteration
   {
      if(t == null)
         return null;
      while(t.getLeft() != null)
         t = t.getLeft();
      return "" + t.getValue();
   }
   
   public String max()
   {
      if(root == null)
         return null;
      return max(root);
   }
   private String max(TreeNode t)  //recursive helper method
   {
      if(t == null)
         return "";
      if(t.getRight() == null)
         return "" + t.getValue();
      return max(t.getRight());
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode t)  //an in-order traversal.  Use recursion.
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += toString(t.getLeft());
      toReturn += t.getValue() + " ";
      toReturn += toString(t.getRight());
      return toReturn;
   }
   
   /*  precondition:  target must be in the tree.
                      implies that tree cannot be null.
   */
   public void remove(String target)
   {
      root = remove(root, target);
      size--;
   }
   private TreeNode remove(TreeNode current, String target)
   {
      if(current == null)
         return null;
      if(target.equals((String)current.getValue()))
      {
         if(current.getLeft() == null && current.getRight() == null)
            return null;
         else if(current.getLeft() == null)
            return current.getRight();
         else if(current.getRight() == null)
            return current.getLeft();
                  
         String left = (String)max(current.getLeft());
         current.setValue(left);
         current.setLeft(remove(current.getLeft(), left));
      }
      else if(target.compareTo((String)current.getValue()) < 0)
         current.setLeft(remove(current.getLeft(), target));
      else
         current.setRight(remove(current.getRight(), target));
      return current;
   }
}


