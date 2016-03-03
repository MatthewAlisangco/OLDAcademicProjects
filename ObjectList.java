/**This will construct our Linear Linked list of Objects.   
 * 
 * @author Matthew Alisangco
 * @version V1.0x
 */
public class ObjectList {
    private ObjectListNode list;
    private ObjectListNode last;

    /**Constructs an empty list 
     */
    public ObjectList() {
        list = null;
        last = null;
    }

    /** Returns the first node in the list
     */
    public ObjectListNode getFirstNode() {
        return list;
    }

    /** Returns the last node in the list
     * 
     */
    public ObjectListNode getLastNode() {
        return last;
    }

    /** Returns the first object in the list
     * getter method
     */
    public Object getFirst() {
        if (list == null) {
            System.out.println("Runtime Error: getFirst()");
            System.exit(1);
        }
        return list.getInfo();
    }

    /**Returns the last object in the list 
     * getter method
     */
    public Object getLast() {
        if (list == null) {
            System.out.println("Runtime Error: getLast()");
            System.exit(1);
        }
        return last.getInfo();
    }

    /**Adds an object to the front of a list
     * 
     * @param Object o
     */
    public void addFirst(Object o) {
        ObjectListNode p = new ObjectListNode(o, list);
        if (list == null)
            last = p;
        list = p;
    }

    /**Adds a node to the front of the list
     * 
     * @param ObjectListNode p
     */
    public void addFirst(ObjectListNode p) {
        if (p == null) {
            System.out.println("Runtime Error: addFirst()");
            System.exit(1);
        }
        p.setNext(list);
        if (list == null)
            last = p;
        list = p;
    }

    /** Adds an object to the end of the list
     * 
     * @param Object o
     */
    public void addLast(Object o) {
        ObjectListNode p = new ObjectListNode(o);
        if (list == null) 
            list = p;
        else
            last.setNext(p);
        last = p;
    }

    /** Adds a node to the end of the list
     * 
     * @param ObjectListNode p
     */
    public void addLast(ObjectListNode p) {
        if (p == null) {
            System.out.println("Runtime Error: addLast()");
            System.exit(1);
        }
        p.setNext(null);
        if (list == null)
            list = p;
        else
            last.setNext(p);
        last = p;
    }

    /** Removes the first object from the list
     */
    public Object removeFirst() {
        if (list == null) {
            System.out.println("Runtime Error: removeFirst()");
            System.exit(1);
        }
        ObjectListNode p = list;
        list = p.getNext();
        if (list == null)
            last = null;
        return p.getInfo();
    }

    /** Removes the last object from the list
     */
    public Object removeLast() {
        if (list == null) {
            System.out.println("Runtime Error: removeLast()");
            System.exit(1);
        }
        ObjectListNode p = list;
        ObjectListNode q = null;
        while (p.getNext() != null) {
            q = p;
            p = p.getNext();
        }
        if (q == null) {
            list = null;
            last = null;
        }    
        else {
            q.setNext(null);
            last = q;
        }    
        return p.getInfo();
    }

    /**Inserts an object after the node referenced by p
     * 
     * @param ObjectListNode p, Object o
     * 
     */
    public void insertAfter (ObjectListNode p, Object o) {
        if (list == null || p == null) {
            System.out.println("Runtime Error: insertAfter()");
            System.exit(1);
        }
        ObjectListNode q = new ObjectListNode(o, p.getNext());
        p.setNext(q);
        if (q.getNext() == null)
            last = q;
    }

    /** Inserts a node after the node referenced by p
     * 
     * @param ObjectListNode p, ObjectListNode q
     */
    public void insertAfter(ObjectListNode p, ObjectListNode q) {
        if (list == null || p == null || q == null) {
            System.out.println("Runtime Error: insertAfter()");
            System.exit(1);
        }
        q.setNext(p.getNext());
        p.setNext(q);
        if (last.getNext() != null)
            last = q;
    }

    /** Deletes the node after the node referenced by p
     * 
     * @param ObjectListNode p
     * 
     * @return Object q.  
     */
    public Object deleteAfter(ObjectListNode p) {
        if (list == null || p == null || p.getNext() == null) {
            System.out.println("Runtime Error: deleteAfter()");
            System.exit(1);
        }
        ObjectListNode q = p.getNext();
        p.setNext(q.getNext());
        if (p.getNext() == null)
            last = p;
        return q.getInfo();
    }

    /** Inserts an item into its correct location within an ordered list
     * 
     * @param Object o
     */
    public void insert(Object o) {
        ObjectListNode p = list;
        ObjectListNode q = null;
        while (p != null && ((Comparable)o).compareTo(p.getInfo()) > 0) {
            q = p;
            p = p.getNext();
        }
        if (q == null)
            addFirst(o);
        else
            insertAfter(q, o);
    }

    /** Inserts a node into its correct location within an ordered list
     * 
     * @param ObjectListNode r
     */
    public void insert(ObjectListNode r) {
        ObjectListNode p = list;
        ObjectListNode q = null;
        while (p != null &&
        ((Comparable)r.getInfo()).compareTo(p.getInfo()) > 0) {
            q = p;
            p = p.getNext();
        }
        if (q == null)
            addFirst(r);
        else
            insertAfter(q, r);
    }

    /**Removes the first occurrence of an item in a list 
     * 
     * @param Object o
     */
    public Object remove(Object o) {
        ObjectListNode p = list;
        ObjectListNode q = null;
        while (p != null && ((Comparable)o).compareTo(p.getInfo()) != 0) {
            q = p;
            p = p.getNext();
        }
        if (p == null)
            return null;
        else return q == null ? removeFirst() : deleteAfter(q);
    }

    /** Returns true if the item is found in the list
     * 
     * @param Object o
     * 
     * @return ObjectListNode p
     */
    public boolean contains(Object o) {
        ObjectListNode p = list;
        while (p != null && ((Comparable)o).compareTo(p.getInfo()) != 0)
            p = p.getNext();
        return p != null;
    }

    /** Returns a reference to the node with the requested value; returns null otherwise
     * 
     * @param Object o
     * 
     * @return ObjectListNode 
     */
    public ObjectListNode select(Object o) {
        ObjectListNode p = list;
        while (p != null)
            if (((Comparable)o).compareTo(p.getInfo()) == 0)
                return p;
            else
                p = p.getNext();
        return null;
    }

    /** Determines whether or not a list is empty
     * 
     * @return boolean list
     */
    public boolean isEmpty() {
        return list == null;
    }

    /** Removes all elements from a list
     */
    public void clear() {
        list = null;
        last = null;
    }

    /**Returns the number of elements in the list
     */
    public int size() {
        int count = 0;
        ObjectListNode p = list;
        while (p != null) {
            ++count;
            p = p.getNext();
        }
        return count;
    }

    /** Makes a copy of a list
     */
    public ObjectList copyList() {
        ObjectListNode p = null; 
        ObjectListNode q = null; // to satisfy compiler;
        ObjectListNode r = list;

        if (isEmpty())
            return null;
        ObjectList newList = new ObjectList();
        while (r != null) {
            p = new ObjectListNode(r.getInfo());
            if (newList.isEmpty())
                newList.addFirst(p);
            else
                q.setNext(p); 
            q = p;
            r = r.getNext();
        }
        newList.last = p;
        return newList;
    }

    /** Reverses a list
     */
    public void reverse() {
        ObjectListNode p = list;
        ObjectListNode q = null;
        ObjectListNode r;

        while (p != null) {
            r = q;
            q = p;
            p = p.getNext();
            q.setNext(r);
        }
        last = list;
        list = q;
    }   
}

