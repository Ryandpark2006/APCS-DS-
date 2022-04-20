// Name: Ryan Park              Date: 4/1/22
import java.util.*;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines)
   {
      TreeNode root = new TreeNode("");
      TreeNode temp = root;
      
      while(huffLines.hasNextLine())
      {
         int index = 0;
         String s = huffLines.nextLine();
         temp = root;
         String letter = s.substring(0,1);
         s = s.substring(1);
      
         for(int x = 0; x < s.length(); x++)
         {
            int direction = Integer.parseInt("" + s.charAt(x));
            if(direction == 0)
            {
               if(temp.getLeft() == null)
               {
                  TreeNode left = new TreeNode("");
                  temp.setLeft(left);
               }
               temp = temp.getLeft();
            }
            else if(direction == 1)
            {
               if(temp.getRight() == null)
               {
                  TreeNode right = new TreeNode("");
                  temp.setRight(right);
               }
               temp = temp.getRight();
            }
            
            if(x == s.length() - 1)
            {
               temp.setValue(letter);
            }
         }
      }
               
      return root;
      
   }
   public static String dehuff(String text, TreeNode root)
   {
      TreeNode temp = root;
      String dehuffed = "";
      
      for(int x = 0; x < text.length(); x++)
      {
         int direction = Integer.parseInt("" + text.charAt(x));
         if(temp.getValue() == "")
         {
            if(direction == 0)
            {
               temp = temp.getLeft();
            }
            else
            {
               temp = temp.getRight();
            }
         }
         if(temp.getValue() != "")
         {
            dehuffed += temp.getValue();
            temp = root;
         }
      
      }
      
      return dehuffed;
   }
}

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
