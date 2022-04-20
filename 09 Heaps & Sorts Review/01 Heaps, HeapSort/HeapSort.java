// Name:Ryan Park
// Date:3/22/22
import java.text.DecimalFormat;

public class HeapSort
{
   public static int N;  //9 or 100
	
   public static void main(String[] args)
   {
      /* Part 1: Given a heap, sort it. Do this part first. */
      N = 4;  
      double heap[] = {-1, 7.2, 3.4, 6.4, 9.9};  // size of array = N+1
       
      display(heap);
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));
      
      /* Part 2:  Generate 100 random numbers, make a heap, sort it.  */
   //    N = 100;
   //    double[] heap = new double[N + 1];  // size of array = N+1
   //    heap = createRandom(heap);
   //    display(heap);
   //    makeHeap(heap, N);
   //    display(heap); 
   //    sort(heap);
   //    display(heap);
   //    System.out.println(isSorted(heap));
   }
   
	//******* Part 1 ******************************************
   public static void display(double[] array)
   {
      for(int k = 1; k < array.length; k++)
         System.out.print(array[k] + "    ");
      System.out.println("\n");	
   }
   
   public static void sort(double[] array)
   {
      /* enter your code here */
   
      for (int i = N; i > 2; i--) {
         swap(array, i, 1);
         heapDown(array, 1, i-1);
      }              
                    
      if(array[1] > array[2])   //just an extra swap, if needed.
         swap(array, 1, 2);
         
      if(array[array.length - 1] < array[array.length - 2])
         swap(array, array.length - 1, array.length - 2);
            
   }
  
   public static void swap(double[] array, int a, int b)
   {
      double temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
   
   public static void heapDown(double[] array, int k, int lastIndex)
   {
      int left = 2 * k;
      int right = 2 *k + 1;
      if(k > lastIndex || left > right && right > lastIndex)
         return;
      else{
         if(right <= lastIndex)
         {
            int max = 0; 
            if(array[left] > array[right])
               max = left;
            else if(array[1] == array[right])
               max = left;
            else 
               max = right;
            if(array[k] < array[max])
            {
               swap(array, k, max);
               heapDown(array, max, lastIndex);
            } 
         }
      }
   }
   
   public static boolean isSorted(double[] arr)
   {
      double temp = arr[1];
      for (int i = 2; i <= (N - 1) / 2; i++) {
         if (arr[i] < temp)
            return false;
         temp = arr[i];
      }
      return true;
   }
   
   //****** Part 2 *******************************************

   //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places) 
   public static double[] createRandom(double[] array)
   {  
      array[0] = -1;   //because it will become a heap
      DecimalFormat formatter = new DecimalFormat("#0.00");     
      for(int x = 1; x < N; x++)
         array[x] = Double.parseDouble(formatter.format(Math.random() * 100 + 1)); 
      return array;
   }
   
   //turn the random array into a heap
   public static void makeHeap(double[] array, int lastIndex)
   {
      for(int x = N/2; x >= 1; x--)
         heapDown(array, x, lastIndex);
   }
}

