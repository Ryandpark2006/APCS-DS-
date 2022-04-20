// Name: Ryan Park   
// Date: 11/30/21

/**
 * Implements the cheat sheet's List interface.  Implements generics.
 * The backing array is an array of (E[]) new Object[10];
 * @override toString()
 */ 
public class TJArrayList<E>
{
   private int size;							//stores the number of objects
   private E[] myArray;
   public TJArrayList()                
   {
      myArray = (E[]) new Object[10]; //default constructor instantiates a raw array with 10 cells
   
      size = 0;
   }
   public int size()
   {
      return size;
   }
 	/* appends obj to end of list; increases size;
      must be an O(1) operation when size < array.length, 
         and O(n) when it doubles the length of the array.
	  @return true  */
   public boolean add(E obj)
   {
      E[] array = (E[]) new Object[size + 1];
      for(int x = 0; x < size; x++)
         array[x] = myArray[x];
      array[size] = obj;
      myArray = array;
      size++;
      return true;
      
   }
   /* inserts obj at position index.  increments size. 
		*/
   public void add(int index, E obj) throws IndexOutOfBoundsException  //this the way the real ArrayList is coded
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
         
      E[] array = (E[]) new Object[size + 1];
      for(int x = 0; x < index; x++)
         array[x] = myArray[x];
      array[index] = obj;
      for(int x = index; x < size; x++)
         array[x + 1] = myArray[x];
      myArray = array;
      size++;
   
   }

   /* return obj at position index.  
		*/
   public E get(int index) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      return myArray[index];
   }
   /**
    * Replaces obj at position index. 
    * @return the object is being replaced.
    */  
   public E set(int index, E obj) throws IndexOutOfBoundsException  
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      E replaced = myArray[index];
      myArray[index] = obj;
      return replaced;
   }
 /*  removes the node from position index. shifts elements 
     to the left.   Decrements size.
	  @return the object that used to be at position index.
	 */
   public E remove(int index) throws IndexOutOfBoundsException  
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      E[] array = (E[]) new Object[size - 1];
      E removed = myArray[index];
      for(int x = 0; x < index; x++)
         array[x] = myArray[x];
      for(int x = index + 1; x < size; x++)
         array[x - 1] = myArray[x];
      myArray = array;
      size--;
      return removed;
   }
	   /*
		   This method compares objects.
         Must use .equals(), not ==
     	*/
   public boolean contains(E obj)
   {
      for(int x = 0; x < size; x++)
         if(myArray[x].equals(obj))
            return true;
      return false;
   }
	 /*returns a String of E objects in the array with 
       square brackets and commas.
     	*/
   public String toString()
   {
      String format = "[";
      for(int x = 0; x < size - 1; x++)
         format = format + myArray[x] + ", ";
      format = format + myArray[size-1] + "]";
      return format;
   }
}