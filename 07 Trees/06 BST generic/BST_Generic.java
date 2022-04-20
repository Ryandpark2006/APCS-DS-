// Name:Ryan Park
// Date: 2/22/22
import java.util.*;

interface BSTinterface<E>
{
   public int size();
   public boolean contains(E element);
   public E add(E element);
   //public E addBalanced(E element);
   public E remove(E element);
   public E min();
   public E max();
   public String display();
   public String toString();
   public List<E> toList();  //returns an in-order list of E
}

/*******************
  Copy your BST code.  Implement generics.
**********************/
public class BST_Generic<E extends Comparable<E>> implements BSTinterface<E>
{
   private TreeNode<E> root;
   private int size;
   public BST_Generic()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
      return size;
   }
   public TreeNode<E> getRoot()   //for Grade-It
   {
      return root;
   }
   /***************************************N
   @param s -- one string to be inserted
   ****************************************/
   public E add(E s) 
   {
      root = add(root, s);
      size++;
      return s;
   }
   
   public List<E> toList()
   {
      List<E> list = new ArrayList<E>();
      return toList(list, root);
   }
   
   private List<E> toList(List<E> l, TreeNode<E> t)
   {
      if(t == null)
         return null;
      toList(l, t.getLeft());
      l.add(t.getValue());
      toList(l, t.getRight());
      return l;
   }
   
   private TreeNode<E> add(TreeNode<E> t, E obj) //recursive helper method
   {      
      if(t == null)
         return new TreeNode<E>(obj);
      else if(obj.compareTo(t.getValue()) <= 0)  //s <= t
         t.setLeft(add(t.getLeft(), obj));
      else if(obj.compareTo(t.getValue()) > 0)
         t.setRight(add(t.getRight(), obj));
      return t;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   private String display(TreeNode<E> t, int level) //recursive helper method
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
   
   public boolean contains( E obj)
   {
      return contains(root, obj);
   }
   private boolean contains(TreeNode<E> t, E x) //recursive helper method
   {
      if(t == null)
         return false;
      if((t.getValue()).compareTo(x) > 0)
         return contains(t.getLeft(), x);
      else if((t.getValue()).compareTo(x) < 0)
         return contains(t.getRight(), x);
      return true;
   }
   
   public E min()
   {
      if(root == null)
         return null;
      return min(root);
   }
   private E min(TreeNode<E> t)  //use iteration
   {
      if(t == null)
         return null;
      while(t.getLeft() != null)
         t = t.getLeft();
      return t.getValue();
   }
   
   public E max()
   {
      if(root == null)
         return null;
      return max(root);
   }
   private E max(TreeNode<E> t)  //recursive helper method
   {
      if(t == null)
         return null;
      if(t.getRight() == null)
         return t.getValue();
      return max(t.getRight());
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode<E> t)  //an in-order traversal.  Use recursion.
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
   public E remove(E target)
   {
      root = remove(root, target);
      size--;
      return target;
   }
   private TreeNode remove(TreeNode<E> current, E target)
   {
      if(current == null)
         return null;
      if(target.equals(current.getValue()))
      {
         if(current.getLeft() == null && current.getRight() == null)
            return null;
         else if(current.getLeft() == null)
            return current.getRight();
         else if(current.getRight() == null)
            return current.getLeft();
                  
         E left = max(current.getLeft());
         current.setValue(left);
         current.setLeft(remove(current.getLeft(), left));
      }
      else if(target.compareTo(current.getValue()) < 0)
         current.setLeft(remove(current.getLeft(), target));
      else
         current.setRight(remove(current.getRight(), target));
      return current;
   }
}

/*******************
  Copy your TreeNode code.  Implement generics.
**********************/
class TreeNode<E>
{
   private E value; 
   private TreeNode<E> left, right;
   
   public TreeNode(E initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(E initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public E getValue()
   { 
      return value; 
   }
   
   public TreeNode<E> getLeft() 
   { 
      return left; 
   }
   
   public TreeNode<E> getRight() 
   { 
      return right; 
   }
   
   public void setValue(E theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode<E> theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode<E> theNewRight)
   { 
      right = theNewRight;
   }
}