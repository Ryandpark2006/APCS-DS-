// Name: Ryan Park   
// Date: 8/31/21
import java.util.*;
import java.io.*;
class PigLatin
{
   public static void main(String[] args) 
   {
      part_1_using_pig();
      // part_2_using_piglatenizeFile();
      
      /*  extension only    */
      // String pigLatin = pig("What!?");
      // System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin));   //Yahwta!?
      // pigLatin = pig("{(Hello!)}");
      // System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //{(Yaholle!)}
      // pigLatin = pig("\"McDonald???\"");
      // System.out.println("\n" + pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???"
   }

   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if(s.equals("-1"))
         {
            System.out.println("Goodbye!"); 
            System.exit(0);
         }
         String p = pig(s);
         System.out.println( p );
      }		
   }

   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUaeiou";
   public static String pig(String s)
   {
      if(s.length() == 0)
         return "";
      //remove and store the beginning punctuation 
      String beginningPunct = ""; 
     
      for(int x = 0; x < s.length(); x++)
         for(int y = 0; y < punct.length(); y++)
            if(s.contains(punct.charAt(y)) && !letters.contains(s.charAt(x))) 
               beginningPunct = beginningPunct + punct.charAt(y);
           
      //remove and store the ending punctuation 
      String endPunct = ""; 
      
      for(int x = s.length()-1; x >= 0; x++)
         if(s.contains(punct.charAt(x)) && !letters.contains(s.charAt(x))) 
            endPunct = endPunct + s.charAt(x);
   
      //START HERE with the basic case:
      //     find the index of the first vowel
      //     y is a vowel if it is not the first letter
      //     qu
      String original = s;
      int indexOfVowel = 0;
      int counter = 0; 
      
      for(int x = 0; x < s.length(); x++)
         if(counter == 0)
            for(int y = 0; y < vowels.length(); y++)
               if(s.charAt(x).contains(vowels.charAt(y)))
               {
                  indexOfVowel = x;
                  counter++;
               }         
      
      int lowerY = s.indexOf("y");
      int upperY = s.indexOf("Y");
      if(s.contains("y") || s.contains("Y"))
      {
         if(indexOfVowel > lowerY && lowerY != 0)
         {
            indexOfVowel = lowerY;
            
         }
         else if(indexOfVowel > upperY && upperY != 0)
         {
            indexOfVowel = upperY;
         }
      }
         
      if(s.contains("qu") && indexOfVowel > s.indexOf("q"))
      {
         s = s.substring(s.indexOf("u") + 1, s.length()) + s.substring(0,s.indexOf("u") + 1) + "ay";
         return s;
      }
      if(s.contains("Qu") && indexOfVowel > s.indexOf("Q"))
      {
         s = s.substring(s.indexOf("u") + 1, s.length()) + s.substring(0,s.indexOf("u") + 1) + "ay";
         return s;
      }
   
      
      //if no vowel has been found
      
      if(indexOfVowel)
         return "**** NO VOWEL ****";
      
      //is the first letter capitalized?
      
      boolean upper = original.isUpperCase(0);
      
      if(upper)
      {
         s.charAt(0).toUpperCase();    //maybe rewrite string if error
         int change = s.indexOf(original.charAt(0));   //changes original first letter to lower case
         s.charAt(change).toLowerCase();
      }
      
      //return the piglatinized word 
     if(vowels.contains(s.charAt(indexOfVowel))) 
      {
         s = s.substring(indexOfVowel, s.length()) + s.substring(0, indexOfVowel) + "ay";
      }
      else if(letters.contains(s.charAt(indexOfVowel)))
      {
         s = s.substring(indexOfVowel, s.length()) + s.substring(0, indexOfVowel) + "way";
      }
     
     //capitalization and add punctuations
      
      
      
      
      return s;
   }


   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }

/****************************** 
*  piglatinizes each word in each line of the input file
*    precondition:  both fileNames include .txt
*    postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
   	//process each word in each line
      
      
      
   
      outfile.close();
      infile.close();
   }
   
   /** EXTENSION: Output each PigLatin word in reverse, preserving before-and-after 
       punctuation.  
   */
   public static String pigReverse(String s)
   {
      if(s.length() == 0)
         return "";
         
         
   }
}






