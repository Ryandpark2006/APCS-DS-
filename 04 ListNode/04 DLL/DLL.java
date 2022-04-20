// Name: Ryan Park
// Date: 11/18/21

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL        //DoubleLinkedList
{
   private int size = 0;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   //no constructor needed
   
   /* two accessor methods  */
   public int size()
   {
      return size;
   }
   public DLNode getHead()
   {
      return head;
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index (the list is zero-indexed).  
      increments size. 
      no need for a special case when size == 0.
	   */
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode ref = getHead();
      for(int x = 0; x < index; x++)
         ref = ref.getNext();
      DLNode add = new DLNode(obj, ref, ref.getNext());
      ref.getNext().setPrev(add);
      ref.setNext(add);
      size += 1;                    
      
   }
   
    /* return obj at position index (zero-indexed). 
    */
   public Object get(int index) throws IndexOutOfBoundsException    //check if this works
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode ref = head;
      for(int x = 0; x < index; x++)
         ref = ref.getNext();
      return ref.getNext().getValue();              
   }
   
   /* replaces obj at position index (zero-indexed). 
        returns the obj that was replaced.
        */
   public Object set(int index, Object obj) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode ref = getHead();
      for(int x = 0; x < index; x++)
         ref = ref.getNext();
      Object temp = ref.getNext().getValue();
      ref.getNext().setValue(obj);
      return temp;  
   }
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object in the node that was removed. 
        */
   public Object remove(int index) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode ref = head;
      for(int x = 0; x < index; x++)
         ref = ref.getNext();
      Object temp = ref.getNext().getValue();
      ref.setNext(ref.getNext().getNext());
      ref.getNext().setPrev(ref);
      size -= 1;
      return temp;
   }
   
  	/* inserts obj to front of list, increases size.
	    */ 
   public void addFirst(Object obj)
   {
      add(0, obj);
   }
   
   /* appends obj to end of list, increases size.
       */
   public void addLast(Object obj)         //fix this method
   {
      add(size(), obj);
   }
   
   /* returns the first element in this list  
      */
   public Object getFirst()
   {
      return get(0);
   }
   
   /* returns the last element in this list  
     */
   public Object getLast()
   {
      return get(size() - 1);
   }
   
   /* returns and removes the first element in this list, or
      returns null if the list is empty  
      */
   public Object removeFirst()
   {
      if(size() == 0)
         return null;
      Object ref = head.getNext().getValue();
      remove(0);
      return ref;
   }
   
   /* returns and removes the last element in this list, or
      returns null if the list is empty  
      */
   public Object removeLast()   //fix
   {
      if(size() == 0)
         return null;
      Object ref = head.getPrev().getValue();
      remove(size() - 1);
      return ref;
   }
   
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
   public String toString()
   {
      DLNode ref = head;
      String format = "[";
      for(int x = 0; x < size - 1; x++)
      {
         format = format + get(x) + ", ";
      }
      format = format + get(size() - 1) + "]";
      
      return format;
   }
}