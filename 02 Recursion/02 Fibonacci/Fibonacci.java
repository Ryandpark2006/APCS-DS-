// Name: Ryan Park
// Date: 9/23/21
  
import java.util.*;
public class Fibonacci
{
   public static void main(String[] args)
   {
      long start, end, fib; //why long?
      int lastFibNumber = 43;
      int[] fibNumber = {1};
      System.out.println("\tFibonacci\tBy Iteration\tTime\tby Recursion\t Time");
      for(int n = fibNumber[0]; n <= lastFibNumber; n++)
      { 
         start = System.nanoTime();
         fib = fibIterate(n);
         end = System.nanoTime();
         System.out.print("\t\t" + n + "\t\t" + fib + "\t" + (end-start)/1000.);
         start = System.nanoTime();   	
                  fib = fibRecur(n);      
                  end = System.nanoTime();
                  System.out.println("\t" + fib + "\t\t" + (end-start)/1000.);
      }
   }
   
   /**
    * Calculates the nth Fibonacci number by interation
    * @param n A variable of type int representing which Fibonacci number
    *          to retrieve
    * @returns A long data type representing the Fibonacci number
    */
   public static long fibIterate(int n)
   {
      long temp = 0; 
      long a = 0;
      long b = 1;
      
      for(int x = 0; x < n - 1; x++)
      {
         temp = b;
         b += a;
         a = temp;
      }
      
      return b;    
   }

   /**
    * Calculates the nth Fibonacci number by recursion
    * @param n A variable of type int representing which Fibonacci number
    *          to retrieve
    * @returns A long data type representing the Fibonacci number
    */
   public static long fibRecur(int n)
   {
      long fib = 0;
      if(n == 0)
         return 0;
      if(n == 1)
         return 1;
      return fibRecur(n-1) + fibRecur(n-2);
   }
}