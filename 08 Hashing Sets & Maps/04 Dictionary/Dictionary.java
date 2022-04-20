// Name: Ryan Park 
// Date: 3/8/22

import java.io.*;
import java.util.*;

public class Dictionary
{
   public static void main(String[] args) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
         System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
      }
      catch(Exception e)
      {
      }
      
      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
   
      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
   }
   
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {
      Map<String, Set<String>> dict = new TreeMap<String, Set<String>>();
      while(infile.hasNext())
         add(dict, infile.next(), infile.next());  
      return dict; 
   }
   
   public static void add(Map<String, Set<String>> dictionary, String word, String translation)
   {
      Set<String> translate = new TreeSet<String>();
      if(dictionary.get(word) != null)
      {
         translate = dictionary.get(word);
         translate.add(translation);
         dictionary.put(word, translate);
      }
      else
      {
         translate.add(translation);
         dictionary.put(word, translate);
      }
   }
   
   public static void display(Map<String, Set<String>> m)
   {
      Set<String> keys = m.keySet();
      String formatted = "";
      for(String s : keys)
         formatted += s + " " + m.get(s).toString() + "\n";
      System.out.println(formatted);
   }
   
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {
      TreeMap<String, Set<String>> reversedTreeMap = new TreeMap<String, Set<String>>();
      for (String key : dictionary.keySet()){
         
         String temp = dictionary.get(key).toString();
         temp = temp.substring(1, temp.length() - 1);
         temp = temp.replaceAll(" ", "");
         String[] input = temp.split(",");   
         
         for(int x = 0; x < input.length; x++)
         {
            add(reversedTreeMap, input[x], key);
         }
      }
      return reversedTreeMap;
   }
}


   /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/