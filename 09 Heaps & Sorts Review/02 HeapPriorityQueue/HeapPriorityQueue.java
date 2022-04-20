 //Name:Ryan Park   
 //Date:3/24/22
 
import java.util.*;

/* implement the API for java.util.PriorityQueue
 *     a min-heap of objects in an ArrayList<E> in a resource class
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   public ArrayList<E> getHeap()   //for Codepost
   {
      return myHeap;
   }
   
   public int lastIndex()
   {
      return myHeap.size() - 1;
   }
   
   public boolean isEmpty()
   {
      return myHeap.size() < 2;
   }
   
   public boolean add(E obj)
   {
      myHeap.add(obj);
      if(myHeap.size() > 2)
         heapUp(myHeap.size() - 1);
      return true;
   }
   
   public E remove()
   {
      if(myHeap.isEmpty()) { 
         return null;
      }
      E removed = myHeap.get(1);
      swap(1, myHeap.size() - 1); 
      myHeap.remove(myHeap.size() - 1); 
      heapDown(1, myHeap.size() - 1); 
      return removed;
   }
   
   public E peek()
   {
      if(myHeap.isEmpty()) { 
         return null;
      }
      else if(myHeap.size() < 2)
         return myHeap.get(0);
      return myHeap.get(1);
   }
   
   //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapUp(int k)
   {
      int p = k/2;
      if(p == 0)
         return;
      if((myHeap.get(k)).compareTo(myHeap.get(p)) < 0) {
         swap(k,p);
         heapUp(p);
      }  
   }
   
   private void swap(int a, int b)
   {
      E temp = myHeap.get(a);
      myHeap.set(a,myHeap.get(b));
      myHeap.set(b,temp);
   }
   
  //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapDown(int k, int lastIndex)
   {
      int left = 2 * k;
      int right = 2 * k + 1; 
      int min = left;
      
      if(left < lastIndex)
      {
         if(right < lastIndex) { 
            if((myHeap.get(left)).compareTo(myHeap.get(right)) > 0) {
               min = right;
            }
         }
         if((myHeap.get(min)).compareTo(myHeap.get(k)) < 0) { 
            swap(k, min);
            heapDown(min, lastIndex);
         }
      }
      else
         return;
   
   }
   
   public String toString()
   {
      return myHeap.toString();
   }  
}
