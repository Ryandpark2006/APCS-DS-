// Torbert, 8.24.2004
// Billington, 6.24.2016   modified for GradeIt
// Rudwick                 modified for GradeIt
// Billington, 9.15.2016   try-catch
import java.util.*;
import java.io.*;
public class PigLatin_Recursive
{
   public static void main(String[] args) 
   {
      part_1_using_pig();
      part_2_using_piglatenizeFile();
      
     String pigged = pig("McDonald");
     System.out.println(pigged);
     System.out.println(pigReverse(pigged));
     pigged = pig("{(Hello!)}");
     System.out.println(pigged);
     System.out.println(pigReverse(pigged));
     pigged = pig("\"McDonald??\"");
     System.out.println(pigged);
     System.out.println(pigReverse(pigged));
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

   static String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static String pig(String s)
   {
      if(s.length() == 0)
         return "";
   
      int len = s.length();
      
      char first = s.charAt(0);
      
      if(".,-:;'!?/<>[](){}\\\"".indexOf(first) != -1) // check for beginning punctuation
        {System.out.println(first + pig(s.substring(1)));
         return first + pig(s.substring(1));    //recursion!
        }
        
      if("aeiouAEIOU".indexOf(first) != -1)    //if first is not a vowel
         return s + "way";
         
      char last = s.charAt(len - 1);
      if(".,-:;'!?/<>[](){}\\\"".indexOf(last) != -1) // check for ending punctuation
         return pig(s.substring(0, len - 1)) + last;  //recursion!
         
      int k = 1;
      boolean cap = (int)first >= 65 && (int)first <= 90;
      while(k < s.length() && "aeiouyAEIOUY".indexOf(s.charAt(k)) == -1) //count up to a vowel
         k++;
      if(k == s.length())                    //if no vowel in word
         return "**** NO VOWEL ****";
         
      if(s.charAt(k) == 'u' && s.charAt(k-1) == 'q')   //qu
         ++k;
      if(s.charAt(k) == 'u' && s.charAt(k-1) == 'Q')   //Qu
         ++k;   
         
      if(!cap)														//hello --> ello + h + ay
         return s.substring(k) + s.substring(0, k) + "ay";
         // return s.substring(k) +"-"+ s.substring(0, k) + "ay";
      else
         return s.substring(k, k+1).toUpperCase() + s.substring(k+1) + s.substring(0, 1).toLowerCase() + s.substring(1, k) + "ay";
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
*  precondition:  both Strings include .txt
*  postcondition:  output a piglatinized .txt file 
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
   	
      while(infile.hasNext())
      {
         String s = infile.nextLine();
         while(true)
         {
            int pos = s.indexOf(" ");  //find a space
            if(pos == -1)
            {
               outfile.print(pig(s));  //finish the final word in the file
               break;
            }
            else
            {
               String t = s.substring(0, pos);
               s = s.substring(pos + 1);
               outfile.print(pig(t) + " ");  //output the word
            }
         }
         outfile.println(); // assumes two spaces between paragraphs, nextLine will not take an empty newline
      }
      outfile.close();
      infile.close();
   }
   
   public static String pigReverse(String s)
   {
      if(s.length() == 0)
         return "";
      
      int i = 0;
      while( ".,-:;'!?/<>[](){}\\\"".indexOf(s.charAt(i)) != -1) // find beginning punctuation
         i++;
      String begin = s.substring(0,i);
      s = s.substring(i);
      i = 0;
      while( i<s.length() && ".,-:;'!?/<>[](){}\\\"".indexOf(s.charAt(i)) == -1) // find the word
         i++;
      String word = s.substring(0,i);
      String end = s.substring(i);                       // store ending punctuation
      
   //   String pig = pig(word);
      String reversedPig = "";
      for( int k=0; k<word.length();k++)
         reversedPig = word.charAt(k)+reversedPig;
      return begin + reversedPig + end;
   }
}
