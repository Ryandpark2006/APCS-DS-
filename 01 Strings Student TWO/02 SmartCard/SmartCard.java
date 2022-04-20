//name: Ryan Park   date: 8/26/21

import java.text.DecimalFormat;


public class SmartCard 
{
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;
   /* enter the private fields */
   private double balance;
   private Station current;
   private boolean onTrain;
   
   /* the one-arg constructor  */
   public SmartCard(double initBalance)
   {
      balance = initBalance;
      onTrain = false;
      current = null;
   }

   //these three getter methods only return your private data
   //they do not make any changes to your data
   public boolean getIsBoarded() 
   { 
      return onTrain;
   }
   
   public double getBalance()
   {
      return balance;
   }
         
   public Station getBoardedAt()
   {
      return current;
   }
    
   /* write the instance methods  */
  
   public String getFormattedBalance()
   {
      return df.format(balance);
   }
   
   public void board(Station s)
   {
      if(getBalance() < 0.5)
         System.out.println("Insufficient funds to board. Please add more money.");
         
      if(getIsBoarded() == true)
         System.out.println("Error: already boarded?!");
         
       
      if(getIsBoarded() == false && getBalance() > 0.5)
      {
         onTrain = true;
         current = s;
      }
      
   }
   
   public double cost(Station s)
   {
      int zoneDifference = Math.abs(current.getZone() - s.getZone());
      double cost = 0.5;
      cost = cost + (0.75 * zoneDifference);
     
      return cost;
   }
   
   public void exit(Station s)
   {
      if(getIsBoarded() == false || current == null)
         System.out.println("Error: Did not board?!");
      else{
         if(cost(s) > getBalance())
            System.out.println("Insufficient funds to exit. Please add more money.");
         if(getIsBoarded() == true && getBalance() > cost(s))
         {
            balance = balance - cost(s);
            onTrain = false;
            System.out.println("From " + current.getName() + " to " + s.getName() + " costs " + df.format(cost(s)) + ". Smart card has " + getFormattedBalance() + ".");
            current = null;
         }
      }
   }
   
   public void addMoney(double d)
   {
      balance = balance + d;
   }
   
   
      
     

}
   
// ***********  start a new class.  The new class does NOT have public or private.  ***/
class Station
{
   private String station;
   private int zone;
   
   Station(){
      station = "Downtown";
      zone = 1;
   }
   
   Station(String x, int y)
   {
      station = x;
      zone = y;
   }
   
   int getZone()
   {
      return zone;
   }
   
   String getName()
   {
      return station;
   }
   
}

/*******************  Sample Run, for buddy only ************

 getMoneyRemaining() 20.0
 getBalance() $20.00
 isBoarded() false
 getBoardedAt() null
 isBoarded() true
 getBoardedAt() Station@16b98e56     //sample run uses the default toString
 
 ************************************************/