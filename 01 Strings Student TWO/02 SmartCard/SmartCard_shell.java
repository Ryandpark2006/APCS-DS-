//name:    date:

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
