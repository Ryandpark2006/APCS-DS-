// Name:Ryan Park 
// Date:2/8/22  
/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
 
import java.util.*;

public class BXT
{
   public static final String operators = "+ - * / % ^ !";
   private TreeNode root;   
   
   public BXT()
   {
      root = null;
   }
   public TreeNode getRoot()   //for Codepost
   {
      return root;
   }
    
   public void buildTree(String str)
   {
      Stack<TreeNode> storage = new Stack<TreeNode>();
      if(str.length() == 1)
      {
         root = new TreeNode(str);
      }
      String[] array = str.split(" ");
      for(String s : array)
      {
         if(!isOperator(s))
         {
            storage.push(new TreeNode(s));
         }
         if(isOperator(s))
         {
            TreeNode first = storage.pop();
            TreeNode second = storage.pop();
            TreeNode temp = new TreeNode(s, second, first);
            storage.push(temp);
         }
      }     
      root = storage.pop();
   }  
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
      if(t == null)
         return 0;
      if(isOperator("" + t.getValue()))
         return computeTerm("" + t.getValue(), evaluateNode(t.getLeft()), evaluateNode(t.getRight()));
      else if(!isOperator("" + t.getValue()))
      {
         return Double.parseDouble("" + t.getValue());
      }
      else
         return 0;   
   }
   
   private double computeTerm(String s, double a, double b)
   {
      double answer = 0;
      if(s.contains("*"))
         answer = a * b;
      if(s.contains("/"))
         answer = a / b;
      if(s.contains("+"))
         answer = a + b;
      if(s.contains("-"))
         answer = a - b;
      if(s.contains("%"))
         answer = a % b;
      return answer; 
   }
   
   private boolean isOperator(String s)
   {
      if(operators.contains(s))
         return true;
      return false;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
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
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private  String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += inorderTraverse(t.getLeft());
      toReturn += t.getValue() + " ";
      toReturn += inorderTraverse(t.getRight());
      return toReturn;
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode root)
   {
      String toReturn = "";
      if(root == null)
         return "";
      toReturn += root.getValue() + " ";              //process root
      toReturn += preorderTraverse(root.getLeft());   //recurse left
      toReturn += preorderTraverse(root.getRight());  //recurse right
      return toReturn;
   }
   
  /* extension */
   // public String inorderTraverseWithParentheses()
   // {
      // return inorderTraverseWithParentheses(root);
   // }
//    
   // private String inorderTraverseWithParentheses(TreeNode t)
   // {
      // return "";
   // }
}