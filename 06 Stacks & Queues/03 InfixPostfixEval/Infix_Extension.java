// Name:Ryan Park
// Date:1/11/22
//uses PostfixEval

import java.util.*; 
public class Infix_Extension
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args) throws Exception
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
      /*build your list of Infix expressions here  */
      List<String> infixExp = new ArrayList<>();
         
      // infixExp.add("( 3.0 + -1.0 ) ^ 3.0");    
      // infixExp.add("2 ^ 3 + 3");
      // infixExp.add("3 * 2 ^ 3");     
      // infixExp.add("( 1 + 3 ) !");    
      // infixExp.add("1 + 3 !");     
      // infixExp.add("1 * 3 !");     
      infixExp.add("3 ? 2");       
      infixExp.add("3 @ 2");       
      infixExp.add("( 3 + 2");     
      infixExp.add("3 + 2 ]");     
      infixExp.add("( 3 + 2 ]");   
      
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);  //get the conversion to work first
         System.out.println(infix + "\t\t\t" + pf );  
         // System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + PostfixEval.eval(pf));  //PostfixEval must work!
      }
   }
   
   public static String infixToPostfix(String infix) throws Exception
   {
      List<String> nums = new ArrayList<String>(Arrays.asList(infix.split(" ")));
            /* enter your code here  */
      Stack<String> temp = new Stack<String>();
      String result = "";
      
      boolean par = ParenMatch.checkParen(infix);
      if(!par)
      {
         throw new Exception(infix + " ERROR in parentheses");
      }
      
      for(int x = 0; x < nums.size(); x++)
      {
         if(!operators.contains(nums.get(x)))
         {
            if(LEFT.contains(nums.get(x)))
               temp.push(nums.get(x));
            else if(RIGHT.contains(nums.get(x)))
            {
               while(!LEFT.contains(temp.peek()))   
                  result = result + temp.pop() + " ";
               temp.pop();   
            }
            else
               try{
                Double symbol = Double.parseDouble(nums.get(x));
               }
               catch(Exception e)
               {
                throw new Exception(infix + " ERROR non-algebraic symbol");
               }
               result = result + nums.get(x) + " ";
         }
         else
         {
            if(temp.isEmpty() || LEFT.contains(temp.peek()) || !isHigherOrEqual(temp.peek(), nums.get(x)))
               temp.push(nums.get(x));
            else{
               while(!temp.isEmpty() && !LEFT.contains(temp.peek()) && isHigherOrEqual(temp.peek(), nums.get(x)))
                  result = result + temp.pop() + " "; 
               temp.push(nums.get(x));
            }
         }
      }
      
      while(!temp.isEmpty())
         result = result + temp.pop() + " ";
       
      result = result.trim(); 
      return result;
   }
   
   //enter your precedence method below
   public static boolean isHigherOrEqual(String top, String next)
   {
      if(top.equals("*"))
      {
         if(next.equals("+"))
            return true;
         else if(next.equals("-"))
            return true;
         else if(next.equals("*"))
            return true;
         else if(next.equals("!"))
            return false;
         else if(next.equals("^"))
            return false;
         else
            return true;
      }
      else if(top.equals("-"))
      {
         if(next.equals("+"))
            return true;
         else if(next.equals("-"))
            return true;
         else if(next.equals("*"))
            return false;
         else if(next.equals("!"))
            return false;
         else if(next.equals("^"))
            return false;
         else
            return false;
      }
      else if(top.equals("+"))
      {
         if(next.equals("+"))
            return true;
         else if(next.equals("-"))
            return true;
         else if(next.equals("*"))
            return false;
         else if(next.equals("!"))
            return false;
         else if(next.equals("^"))
            return false;
         else
            return false;
      }
      else if(top.equals("%"))
      {
         if(next.equals("+"))
            return true;
         else if(next.equals("-"))
            return true;
         else if(next.equals("*"))
            return true;
         else if(next.equals("!"))
            return false;
         else if(next.equals("^"))
            return false;
         else
            return true;
      }
      else if(top.equals("/"))
      {
         if(next.equals("+"))
            return true;
         else if(next.equals("-"))
            return true;
         else if(next.equals("*"))
            return true;
         else if(next.equals("!"))
            return false;
         else if(next.equals("^"))
            return false;
         else
            return true;
      }
      else if(top.equals("!"))
      {
         if(next.equals("+"))
            return true;
         else if(next.equals("-"))
            return true;
         else if(next.equals("*"))
            return true;
         else if(next.equals("!"))
            return true;
         else if(next.equals("^"))
            return true;
         else
            return true;
      }
      else if(top.equals("^"))
      {
         if(next.equals("+"))
            return true;
         else if(next.equals("-"))
            return true;
         else if(next.equals("*"))
            return true;
         else if(next.equals("!"))
            return false;
         else if(next.equals("^"))
            return true;
         else
            return true;
      }
      return false;
   }
   
}


/********************************************

   Infix  -->   Postfix    -->  Evaluate
   ( 3.0 + -1.0 ) ^ 3.0    3.0 -1.0 + 3.0 ^     8.0
   2 ^ 3 + 3     2 3 ^ 3 +   11.0
   3 * 2 ^ 3     3 2 3 ^ *   24.0
   ( 1 + 3 ) !      1 3 + !  24.0
   1 + 3 !    1 3 ! +  7.0
   1 * 3 !    1 3 ! *  6.0
   3 ? 2      3 ? 2 ERROR non-algebraic symbol
   3 @ 2      3 @ 2 ERROR non-algebraic symbol
   ( 3 + 2    ( 3 + 2 ERROR in parentheses
   3 + 2 ]    3 + 2 ] ERROR in parentheses
   ( 3 + 2 ]  ( 3 + 2 ] ERROR in parentheses

      
***********************************************/
