// Name: Ryan Park  
// Date: 9/14/21
  
public class Sentence_Driver
{
   public static void main(String[] args)
   {
      System.out.println("PALINDROME TESTER");
      Sentence s = new Sentence( "\"Hello there!\" she said." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
      s = new Sentence( "A Santa lived as a devil at NASA." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
     
   
      s = new Sentence( "Flo, gin is a sin! I golf." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
   
      s = new Sentence( "Eva, can I stab bats in a cave?" );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
   
      s = new Sentence( "Madam, I'm Adam." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
   // Lots more test cases.  Test every line of code.  Test
   // the extremes, test the boundaries.  How many test cases do you need?
   
   }
}

class Sentence
{
   private String mySentence;
   private int myNumWords;
   
   //Precondition:  str is not empty.
   //               Words in str separated by exactly one blank.
   public Sentence( String str )
   { 
      mySentence = str;
      String array[] = new String[str.split(" ", 0).length];
      myNumWords = array.length;
   }
   
   public int getNumWords()
   {  
      return myNumWords;  
   }
   
   public String getSentence()
   {
      return mySentence; 
   }
   
   //Returns true if mySentence is a palindrome, false otherwise.
   public boolean isPalindrome()
   {
      String palindrome = mySentence; 
      palindrome = lowerCase(mySentence);
      palindrome = removePunctuation(palindrome);
      palindrome = removeBlanks(palindrome);
   
      return isPalindrome(palindrome, 0, palindrome.length() - 1);
      
   }
   //Precondition: s has no blanks, no punctuation, and is in lower case.
   //Returns true if s is a palindrome, false otherwise.
   public static boolean isPalindrome( String s, int start, int end )
   {
      if(start >= end)
         return true;
      else if(s.charAt(start) == s.charAt(end))
         return isPalindrome(s, start + 1, end - 1);
         
      return false;
   }
   //Returns copy of String s with all blanks removed.
   //Postcondition:  Returned string contains just one word.
   public static String removeBlanks( String s )
   {  
      //s= s.replaceAll(" ", "");
        
      for(int x = 0; x < s.length(); x++)
      {
         if(s.substring(x, x+1).equals(" "))
         {
            s = s.substring(0, x) + s.substring(x+1, s.length());
            x--; 
         }
      }
         
      return s;
   
   }
   
   //Returns copy of String s with all letters in lowercase.
   //Postcondition:  Number of words in returned string equals
   //						number of words in s.
   public static String lowerCase( String s )
   {  
      String lower = "";
      lower = s.toLowerCase();
      return lower; 
   }
   
   //Returns copy of String s with all punctuation removed.
   //Postcondition:  Number of words in returned string equals
   //						number of words in s.
   public static String removePunctuation( String s )
   { 
      String punct = ".,'?!:;\"(){}[]<>"; 
      String copy = s;
      
      /*for(int x = 0; x < s.length(); x++)
      {
         if(punct.indexOf(s.charAt(x)) != -1)
         {
            s = s.replace("" + s.charAt(x), "");
            x--;
         }
      }*/
      
      for(int x = 0; x < s.length(); x++)
      {
         if(punct.indexOf(s.charAt(x)) != -1)
         {
            s = s.substring(0,x) + s.substring(x+1, s.length());
            x--;
         }
      }
      return s;
   }
}

 /*****************************************
   
 PALINDROME TESTER
 "Hello there!" she said.
 4
 false
 
 A Santa lived as a devil at NASA.
 8
 true
 
 Flo, gin is a sin! I golf.
 7
 true
 
 Eva, can I stab bats in a cave?
 8
 true
 
 Madam, I'm Adam.
 3
 true

 **********************************************/

