// Name: Ryan Park   
// Date: 8/31/21
import java.util.*;
import java.io.*;
class PigLatin
{
   public static void main(String[] args) 
   {
      //part_1_using_pig();
      part_2_using_piglatenizeFile();
      
      /*  extension only    */
       String pigLatin = pig("What!?");
       System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin));   //Yahwta!?
       pigLatin = pig("{(Hello!)}");
       System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //{(Yaholle!)}
       pigLatin = pig("\"McDonald???\"");
       System.out.println("\n" + pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???"
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
      int count = 0;
     
      for(int x = 0; x < s.length(); x++)
      {
         if(punct.indexOf(s.charAt(0)) != -1 && count == 0)
         {
            beginningPunct = beginningPunct + s.charAt(0);
            s = s.substring(1, s.length());
         }
         else
            count++;      
      }   
                    
      //remove and store the ending punctuation 
      String endPunct = ""; 
      count = 0;
     
      for(int x = s.length() - 1; x >= 0; x--)
      {
         if(punct.indexOf(s.charAt(x)) != -1 && count == 0)
         {
            endPunct = s.charAt(x) + endPunct;
            s = s.substring(0, x);
         }
         else
            count++;      
      }         
      
      
      //START HERE with the basic case:
      //     find the index of the first vowel
      //     y is a vowel if it is not the first letter
      //     qu
      String pig = "";
      
      if(vowels.indexOf(s.charAt(0)) != -1)
         return beginningPunct + s + "way" + endPunct;       
        
      int v = 1;
      
      while(v < (s.length() - 1) && vowels.indexOf(s.charAt(v)) == -1)
         v++;
    
      if(s.contains("y") || s.contains("Y"))
      {
         if(s.indexOf("y") < v && s.indexOf("y") > 0)
         {
            v = s.indexOf("y");   
         }
         else if(s.indexOf("Y") < v && s.indexOf("Y") > 0)
            v = s.indexOf("Y");
      }
                 
      if(s.charAt(v) == 'u')
      {
         if(s.charAt(v-1) == 'q')
         {
            v++;
            while(v < s.length() && vowels.indexOf(s.charAt(v)) == -1)
               v++;
         }
      }
   
      if(s.charAt(v) == 'u')
      {
         if(s.charAt(v-1) == 'Q')
         {
            v++;
            while(v < s.length() && vowels.indexOf(s.charAt(v)) == -1)
               v++;
         }
      }  
    
      //if no vowel has been found
      
      if(vowels.indexOf(s.charAt(v)) == -1 && s.charAt(v) != 'y' && s.charAt(v) != 'Y')
         return "**** NO VOWEL ****";
      
      //is the first letter capitalized?
      
      boolean firstCap = false;
      if(s.charAt(0) >= 'A' && s.charAt(0) <= 'Z')
         firstCap = true;
     
      //return the piglatinized word         
      if(firstCap)
         s = s.substring(0, 1).toLowerCase() + s.substring(1, v) + s.substring(v, v+1).toUpperCase() + s.substring(v+1, s.length());
     
      if(v > 0)
         pig = s.substring(v, s.length()) + s.substring(0, v) + "ay"; 
     
     //capitalization and add punctuations
           
      pig = beginningPunct + pig + endPunct;
      
      
      return pig;
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
       
       //loop going through every line
       //loop going through every word in line 
       //in this loop translate to piglatin
       
      while(infile.hasNextLine())   //for loop going through every line
      {
         String line = infile.nextLine();
         String array[] = new String[line.split(" ", 0).length]; 
         array = line.split(" ", 0);
       
         for(int x = 0; x < array.length; x++)
         {
            outfile.print(pig(array[x]) + " ");
         }
       
         outfile.println("");          
      }
       
             
    
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
          
       //remove and store the beginning punctuation 
      String beginningPunct = ""; 
      int count = 0;
     
      for(int x = 0; x < s.length(); x++)
      {
         if(punct.indexOf(s.charAt(0)) != -1 && count == 0)
         {
            beginningPunct = beginningPunct + s.charAt(0);
            s = s.substring(1, s.length());
         }
         else
            count++;      
      }   
                    
      //remove and store the ending punctuation 
      String endPunct = ""; 
      count = 0;
     
      for(int x = s.length() - 1; x >= 0; x--)
      {
         if(punct.indexOf(s.charAt(x)) != -1 && count == 0)
         {
            endPunct = s.charAt(x) + endPunct;
            s = s.substring(0, x);
         }
         else
            count++;      
      }         
       
       
       
       //s.substring from the back
      
      
      boolean firstCap = false;
      if(s.charAt(0) >= 'A' && s.charAt(0) <= 'Z')
         firstCap = true;
     
      if(firstCap)
         s = s.substring(0, 1).toLowerCase() + s.substring(1, s.length() - 1) + s.substring(s.length() - 1, s.length()).toUpperCase();
         
      String reverse = ""; 
      for(int x = s.length() - 1; x >= 0; x--)
      {
         reverse = reverse + s.charAt(x);
      }
      
      return beginningPunct + reverse + endPunct;
   }
}








