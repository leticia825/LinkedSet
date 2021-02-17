import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.LinkedList;
   
/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order to increase efficiency.
 *
 * @author Leticia Garcia 
 * @version 2.8.2021
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {
      
   // References to the first and last node of the list. 
   Node front;
   Node rear;

   // The number of nodes in the list. 
   int size;
   

   /**
    * Instantiates an empty LinkedSet.
    */
   public LinkedSet() {
      front = null;
      rear = null;
      size = 0;
   }


   /**
    * Return a string representation of this LinkedSet.
    *
    * @return a string representation of this LinkedSet
    */
   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }


   /**
    * Returns the current size of this collection.
    *
    * @return  the number of elements in this collection.
    */
   public int size() {
      return size;
   }


   /**
    * Tests to see if this collection is empty.
    *
    * @return  true if this collection contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return (size == 0);
   }


   /**
    * Ensures the collection contains the specified element. Neither duplicate
    * nor null values are allowed. This method ensures that the elements in the
    * linked list are maintained in ascending natural order.
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
   public boolean add(T element) {
   
      if (element == null) {
         throw new NullPointerException();
      }
      
      if(this.contains(element)) {
         return false;
      }
      
      Node e = new Node(element);
     
      if(isEmpty()) {
         front = e;
         rear = e;
         ++size;
         return true;
      }
      
      Node next = front;
      Node prev = null;
      
      while(next != null) {
         if(e.element.compareTo(next.element) < 0) { 
            break;
         }
         prev = next;
         next = next.next;
      }
       
      if(prev == null) {
         front = e;   
      }
      else {
         prev.next = e;
      }
      
      if(next == null) {
         rear = e;   
      }
      else {
         next.prev = e;
      }
      e.next = next;
      e.prev = prev;
           
      size++;
      return true;
   }


   /**
    * Ensures the collection does not contain the specified element.
    * If the specified element is present, this method removes it
    * from the collection. This method, consistent with add, ensures
    * that the elements in the linked lists are maintained in ascending
    * natural order.
    *
    * @param   element  The element to be removed.
    * @return  true if collection is changed, false otherwise.
    */
   public boolean remove(T element) {
      Iterator<T> it = new CustomIterator<T>();
      
      T nextT = null;
      while(it.hasNext()) {
         nextT = it.next();
         
         if(element.equals(nextT)) {
            it.remove();
            size--;
            return true;
         }
      }
      
      return false;
   }


   /**
    * Searches for specified element in this collection.
    *
    * @param   element  The element whose presence in this collection is to be tested.
    * @return  true if this collection contains the specified element, false otherwise.
    */
   public boolean contains(T element) {
      
      Node n = front;
      while (n != null) {
         
         if (n.element.equals(element)) {
            return true;
         }
         n = n.next;
      }
      
      return false;
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    * the parameter set, false otherwise
    */
   public boolean equals(Set<T> s) { 
   
      if(this.size() == s.size() && complement(s).size() == 0) {
         return true;
      }
   
      return false;
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(LinkedSet<T> s) {
   
      if(this.size() == s.size() && complement(s).size() == 0) {
         return true;
      }
      
      return false;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(Set<T> s){ 
      
      LinkedSet<T> all = new LinkedSet<T>();
      Node n = front;
      
      while (n != null) {
         all.add((T)n.element);
         n = n.next;
      }
      
      Iterator ci = s.iterator();
      while(ci.hasNext()) {
         all.add((T)ci.next());
      }
      return all;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(LinkedSet<T> s){
      
      LinkedSet<T> all = new LinkedSet<T>();
      Node n = front;
      
      while (n != null) {
         all.add((T)n.element);
         n = n.next;
      }
      
      Iterator ci = s.iterator();
      while(ci.hasNext()) {
         all.add((T)ci.next());
      }
      return all;
   }


   /**
    * Returns a set that is the intersection of this set and the parameter set.
    *
    * @return  a set that contains elements that are in both this set and the parameter set
    */
   public Set<T> intersection(Set<T> s) { 
      
      LinkedSet same = new LinkedSet<>();
      Node n = front; 
      
      while (n != null) {
         n = n.next;
      }
      
      return same;
   }

   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in both
    * this set and the parameter set
    */
   public Set<T> intersection(LinkedSet<T> s) { 
   
      LinkedSet same = new LinkedSet<>();
      Node n = front; 
      
      while (n != null) {
         if(s.contains((T)n.element)) { 
            same.add((T)n.element);
         }
         n = n.next;
      }
      
      return same;
   }


   /**
    * Returns a set that is the complement of this set and the parameter set.
    *
    * @return  a set that contains elements that are in this set but not the parameter set
    */
   public Set<T> complement(Set<T> s) { 
      
      LinkedSet<T> not = new LinkedSet<T>();
      
      Node n = front; 
      
      while(n != null) {
         if(!(s.contains((T)n.element))) {
            not.add((T)n.element);
         }
         n = n.next;
      }
      
      return not;
   }


   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    * set but not the parameter set
    */
   public Set<T> complement(LinkedSet<T> s) {
   
      LinkedSet<T> not = new LinkedSet<T>();
      
      Node n = front; 
      
      while(n != null) {
         if(!(s.contains((T)n.element))) {
            not.add((T)n.element);
         }
         n = n.next;
      }
      
      return not;
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in ascending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> iterator() {
      Iterator<T> it = new CustomIterator<>();
      
      return it;
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in descending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> descendingIterator() {
   
      LinkedList<T> list = new LinkedList<T>();
      
      Node runner = front;
      
      while(runner != null) {
         list.add(0,(T)runner.element);
         runner = runner.next;
      }
   
      return list.iterator();
   }


   /**
    * Returns an iterator over the members of the power set
    * of this LinkedSet. No specific order can be assumed.
    *
    * @return  an iterator over members of the power set
    */
   public Iterator<Set<T>> powerSetIterator() {
   
      LinkedList<Set<T>> combine = new LinkedList<Set<T>>();
   
      for(int i = 0; i < Math.pow(2, size); i++) {
         Set<T> items = new LinkedSet<T>();
         Node runner = front;
      
         for(int j = 0; j < size; j++) {
            
            if(((i >> j) & 1) == 1) {
               items.add((T)runner.element);
            }
            runner = runner.next;
         }
         
         combine.add(items);
      }
      
      return combine.iterator();
   }

   //// Nested classes /////////////////////
   
   public class CustomIterator<TT> implements Iterator<TT> {
      
      Node next;
      Node prev = null;
      
      
      public CustomIterator() {
         next = front;
        
      }
      public boolean hasNext(){
         return next != null;
      }
      
      public TT next() {
         TT nextT = null;
         if (next != null) {
            nextT = (TT) next.element;
            prev = next;
            next = next.next;  
         }
         return nextT; 
      }
      
      public void remove() {
         if(prev == null) {
            return;
         }
            
         if(prev.prev == null) {
            front = prev.next;
               
         }
         else {
            prev.prev.next = prev.next; 
            
         }
         if(prev.next == null) {
            rear = prev.prev;
         }
         else {
            prev.next.prev = prev.prev; 
            
         }
         if(next == null) {
            prev = null;
         }
         
         else {
            prev = next.prev;
         }
         
      }  
   
   }


   /**
    * Defines a node class for a doubly-linked list.
    */
   class Node {
      // the value stored in this node. 
      T element;
      // a reference to the node after this node. 
      Node next;
      // a reference to the node before this node. 
      Node prev;
   
      /**
       * Instantiate an empty node.
       */
      public Node() {
         element = null;
         next = null;
         prev = null;
      }
   
      /**
       * Instantiate a node that containts element
       * and with no node before or after it.
       */
      public Node(T e) {
         element = e;
         next = null;
         prev = null;
      }
   }

}
