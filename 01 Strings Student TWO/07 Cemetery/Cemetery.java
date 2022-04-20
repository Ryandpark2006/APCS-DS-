//rgalanos@fcps.edu   08.23.2018
//ejurj#fcps.edu 09.22.2018 added a 2nd constructor with age as a double
//                          added getAgeString method to be able to test this approach too
//							calculateAge changed to public to match student's shell
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class Cemetery
{
   public static void main (String [] args)
   {
      File file = new File("cemetery_short.txt");
      //File file = new File("cemetery.txt");
      int numEntries = countEntries(file);
      Person[] cemetery = readIntoArray(file, numEntries); 
      //see what you have
      for (int i = 0; i < cemetery.length; i++) 
         System.out.println(cemetery[i]);
         
      int min = locateMinAgePerson(cemetery);
      int max = locateMaxAgePerson(cemetery); 
      System.out.println("\nIn the St. Mary Magdelene Old Fish Cemetery --> ");
      System.out.println("Name of youngest person: " + cemetery[min].getName());
      System.out.println("Age of youngest person: " + cemetery[min].getAge());    
      System.out.println("Name of oldest person: " + cemetery[max].getName());
      System.out.println("Age of oldest person: " + cemetery[max].getAge());     
   }
   
   /*  counts and returns the number of entries in File f.
       Uses a try-catch block.   
       @param f -- the file object
   */
   public static int countEntries(File f)
   {
      int i=0;
      try
      {
         Scanner scan = new Scanner (f);
         while (scan.hasNextLine())
         {
            i++;
            scan.nextLine();
         }
      }                
      catch (Exception e)
      {
         System.out.println("Check filename.");
      } 
      return i;
   }

   /* Reads the data from file f (you may assume each line has same allignment).
      Fills the array with Person objects. If File f is not valid return null.
      @param f -- the file object 
      @param num -- the number of lines in the File f  
   */
   public static Person[] readIntoArray(File f, int num)
   {
      Person[] burials = new Person[num];
      try
      {
         Scanner scan = new Scanner(f);
         int i=0;
         while (scan.hasNextLine())
         {
            String line = scan.nextLine();
            burials[i]= makeObjects(line);
            i++;
         }
      }                
      catch (Exception e)
      {
         System.out.println("Check filename.");
      } 
      return burials; 
   }
   
   /* a helper method that instantiates one Person object.
      @param entry -- one line of the input file.
   */
   private static Person makeObjects(String entry)
   {
      String name = entry.substring (0,25);
      String burialDate = entry.substring(25, 37);
      String age = entry.substring(37,42);
      return new Person(name, burialDate, age);
   }  
   
  /* finds and returns the location (the index) of the Person
     who is the youngest.
     @param arr -- an array of Person objects.
   */
   public static int locateMinAgePerson(Person[] arr)
   {
      int min = 0;
      for (int i=1; i<arr.length;i++)
      {
         if (arr[i].getAge() < arr[min].getAge())
            min = i;
      }
      return min;
   }   
   
   /* finds and returns the location (the index) of the Person
     who is the oldest.
     @param arr -- an array of Person objects.
   */
   public static int locateMaxAgePerson(Person[] arr)
   {
      int max = 0;
      for (int i=1; i<arr.length;i++)
      {
         if (arr[i].getAge() > arr[max].getAge())
            max = i;
      }
      return max;
   }        
} 

class Person
{
   //constant that can be used for formatting purposes
   private static DecimalFormat df = new DecimalFormat("0.0###");
   /* private fields  */
   private String name;
   private String burialDate;
   private double numericalAge; 
         
    /* a three-arg constructor  
    @param name, burialDate may have leading or trailing spaces
    It creates a valid Person object in which each field has the leading and trailing
    spaces eliminated*/
   public Person(String name, String burialDate, String age)
   {
      this.name = name.trim();
      this.burialDate = burialDate.trim();
      numericalAge = calculateAge(age);
   }
    
   //ejurj added a 2nd constructor that has age as a double
   /* a three-arg constructor  */
   public Person(String n, String bd, double a)
   {
      name = n;
      burialDate = bd;
      numericalAge = calculateAge(""+ a);
   }
 
   
   public double calculateAge(String a)
   {
      a = a.trim();
      double age;
      if( a.contains("w") )
      {
         int pos = a.indexOf("w");
         double numWeeks = Double.parseDouble(a.substring(0,pos));
         age = numWeeks*7/365; //  --> 0.2685
         // age = numWeeks/52;  //  --> 0.2692
      }  
      else if( a.contains("d") )
      {
         int pos = a.indexOf("d");
         double numDays = Double.parseDouble(a.substring(0,pos));
         age = numDays/365;
      } 
      else
         age = Double.parseDouble(a);
      
      age = Double.parseDouble(df.format(age)); 
      return age; 
   }
   
      
   /* any necessary accessor methods */
   public String getName()
   {
      return name;
   } 
   public double getAge()
   {
      return numericalAge;
   }    
    
   public String getAgeString()
   {
      return "" + getAge();
   }
   public String toString()
   {
      return name + ", " + burialDate + ", " + numericalAge;
   }        
}
/**********************************************
   
 John William ALLARDYCE, 17 Mar 1844, 2.9
 Frederic Alex. ALLARDYCE, 21 Apr 1844, 0.17
 Philip AMIS, 03 Aug 1848, 1.0
 Thomas ANDERSON, 06 Jul 1845, 27.0
 Edward ANGEL, 20 Nov 1842, 22.0
 Lucy Ann COLEBACK, 23 Jul 1843, 0.2685
 Thomas William COLLEY, 08 Aug 1833, 0.011
 Joseph COLLIER, 03 Apr 1831, 58.0
 
 In the St. Mary Magdelene Old Fish Cemetery --> 
 Name of youngest person: Thomas William COLLEY
 Age of youngest person: 0.011
 Name of oldest person: Joseph COLLIER
 Age of oldest person: 58.0
  
 ******************************************/