/**
 * The ObjectList class implements the ObjectListInterface.  
 * 
 * @author Matthew Alisangco
 * @version V1.0 x
 */
public interface ObjectListInterface
{
    /**Returns the first node in the list. 
     * 
     */
    public ObjectListNode getFirstNode();     

    /** Returns the last node in the list
     */
    public ObjectListNode getLastNode(); 

    /** Returns the first object in the list
     */
    public Object getFirst() ;

    /** Returns the last object in the list
     */
    public Object getLast() ;

    /** Adds an object to the front of a list
     */
    public void addFirst(Object o) ;

    /** Adds a node to the front of the list
    */
    public void addFirst(ObjectListNode p) ;

    /** Adds an object to the end of the list
     */
    public void addLast(Object o) ;
    
    /** Adds a node to the end of the list
     */
    public void addLast(ObjectListNode p) ;

    /** Removes the first object from the list
     */
    public Object removeFirst();

    /** Removes the last object from the list
     */
    public Object removeLast() ;
    
    /** Inserts an object after the node referenced by p
     */
    public void insertAfter (ObjectListNode p, Object o) ;

    /** Inserts a node after the node referenced by p
     */
    public void insertAfter(ObjectListNode p, ObjectListNode q) ;

    /** Deletes the node after the node referenced by p
     */
    public Object deleteAfter(ObjectListNode p) ;

    
    /** Traverses a list and outputs data from the info fields
     */
    public void traverse();

    /** Inserts an item into its correct location within an ordered list
     */
    public void insert(Object o); 

    /** Inserts a node into its correct location within an ordered list
     */
    public void insert(ObjectListNode r); 

    /** Removes the first occurrence of an item in a list 
     */
    public Object remove(Object o); 

    /** Returns true if the item is found in the list
     */
    public boolean contains(Object o) ;

    /** Returns a reference to the node with the requested value; returns null otherwise
     */
    public ObjectListNode select(Object o);

    /** Determines whether or not a list is empty
     */
    public boolean isEmpty() ;

    /** Removes all elements from a list
     */
    public void clear() ;

    /** Returns the number of elements in the list
     */
    public int size() ;

    /** Makes a copy of a list
     */
    public ObjectList copyList() ;

    /** Reverses a list
     */
    public void reverse() ;
}
