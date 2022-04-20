//  Driver for the SmartCard class.
public class SmartCard_Driver_teacher
{
   public static void main(String[] args) 
   {
      Station downtown = new Station("Downtown", 1);
      Station center = new Station("Center City", 1);
      Station uptown = new Station("Uptown", 2);
      Station suburbia = new Station("Suburb", 4);
   
      SmartCard_teacher buddy = new SmartCard_teacher(20.00); 
      
      /*  test cases  */
      System.out.println("getBalance() "+ buddy.getBalance());
      System.out.println("getIsBoarded() "+ buddy.getIsBoarded());
      System.out.println("getBoardedAt() "+ buddy.getBoardedAt());
      System.out.println("getFormattedBalance() "+ buddy.getFormattedBalance());
    
      buddy.board(downtown);
      System.out.println("getIsBoarded() "+ buddy.getIsBoarded());
      System.out.println("getBoardedAt() "+ buddy.getBoardedAt());
      System.out.println();
      
      buddy.board(suburbia);             // See the output below
      System.out.println();
      
      SmartCard_teacher kim = new SmartCard_teacher(.25);    
      kim.board(uptown);            		// See the output below
      System.out.println();
       
      SmartCard_teacher susie = new SmartCard_teacher(1.00); 
      susie.board(uptown);            		
      susie.exit(suburbia);				   // See the output below
      System.out.println();
    
      SmartCard_teacher jimmy = new SmartCard_teacher(20.00); 
      jimmy.board(center);               
      jimmy.exit(suburbia);              // See the output below
      jimmy.exit(uptown);			   	  // See the output below
      System.out.println();
   	
      SmartCard_teacher b = new SmartCard_teacher(10.00);   
      b.board(center);            		    
      b.exit(downtown);					      // See the output below
      System.out.println();
        
      SmartCard_teacher mc = new SmartCard_teacher(10.00);  
      mc.board(suburbia);            		// See the output below
      mc.exit(downtown);					   // See the output below
      System.out.println();
    
      //What other tests can you think of that will break the code?
      
   }
} 	


 /*******************  Sample Run ************

 getBalance() 20.0
 getIsBoarded() false
 getBoardedAt() null
 getFormattedBalance() $20.00
 getIsBoarded() true
 getBoardedAt() Station@5fd0d5ae    //The Sample Run uses Station's default toString().
                                    //Be able to explain how that works.    
 Error: already boarded?!
 
 Insufficient funds to board. Please add more money.
 
 Insufficient funds to exit. Please add more money.
 
 From Center City to Suburb costs $2.75. SmartCard has $17.25
 Error: Did not board?!
 
 From Center City to Downtown costs $0.50. SmartCard has $9.50
 
 From Suburb to Downtown costs $2.75. SmartCard has $7.25
 
 ************************************************/