// Name:Ryan Park
// Date:1/11/22
//uses PostfixEval

import java.util.*;
public class InfixPostfixEval
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
      /*build your list of Infix expressions here  */
      List<String> infixExp = new ArrayList<>();
         
      // infixExp.add("5 - 1 - 1");
      // infixExp.add("5 - 1 + 1");
      // infixExp.add("12 / 6 / 2");
      // infixExp.add("3 + 4 * 5");
      // infixExp.add("3 * 4 + 5");
      // infixExp.add("1.3 + 2.7 + -6 * 6");
      // infixExp.add("( 33 + -43 ) * ( -55 + 65 )");
      // infixExp.add("8 + 1 * 2 - 9 / 3");
      // infixExp.add("3 * ( 4 * 5 + 6 )");
      // infixExp.add("3 + ( 4 - 5 - 6 * 2 )");
      // infixExp.add("2 + 7 % 3");
      // infixExp.add("( 2 + 7 ) % 3");
         
      // infixExp.add("4 - 3 + 2 + 5 * 2 / 3 % 2"); //4 3 - 2 + 5 2 * 3 / 2 % +
      infixExp.add("3 + 4 * 5"); //3 4 5 * +
      
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);  //get the conversion to work first
         System.out.println(infix + "\t\t\t" + pf );  
         System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + PostfixEval.eval(pf));  //PostfixEval must work!
      }
   }
   
   public static String infixToPostfix(String infix)
   {
      List<String> nums = new ArrayList<String>(Arrays.asList(infix.split(" ")));
            /* enter your code here  */
      Stack<String> temp = new Stack<String>();
      String result = "";
      
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
               result = result + nums.get(x) + " ";
         }
         else
         {
            if(temp.isEmpty() || LEFT.contains(temp.peek()) || !isHigherOrEqual(temp.peek(), nums.get(x)))
               temp.push(nums.get(x));
            else{
               // for(int y = 0; y < temp.size(); y++)
               while(!temp.isEmpty() && !LEFT.contains(temp.peek()) && isHigherOrEqual(temp.peek(), nums.get(x)))
                  result = result + temp.pop() + " "; //fix 8 1 2
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
         else
            return true;
      }
      return false;
   }
   
}


/********************************************

Infix  	-->	Postfix		-->	Evaluate
 5 - 1 - 1			5 1 - 1 -			3.0
 5 - 1 + 1			5 1 - 1 +			5.0
 12 / 6 / 2			12 6 / 2 /			1.0
 3 + 4 * 5			3 4 5 * +			23.0
 3 * 4 + 5			3 4 * 5 +			17.0
 1.3 + 2.7 + -6 * 6			1.3 2.7 + -6 6 * +			-32.0
 ( 33 + -43 ) * ( -55 + 65 )			33 -43 + -55 65 + *			-100.0
 8 + 1 * 2 - 9 / 3			8 1 2 * + 9 3 / -			7.0
 3 * ( 4 * 5 + 6 )			3 4 5 * 6 + *			78.0
 3 + ( 4 - 5 - 6 * 2 )			3 4 5 - 6 2 * - +			-10.0
 2 + 7 % 3			2 7 3 % +			3.0
 ( 2 + 7 ) % 3			2 7 + 3 %			0.0
      
***********************************************/
