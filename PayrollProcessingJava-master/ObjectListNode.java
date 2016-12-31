
/**
 * ObjectListNode class.  places all of  ObjectList values into a a node
 *  
 * 
 * @author Matthew Alisangco
 * @version V1.0x
 */
public class ObjectListNode 
{
    //instance variables
    private Object info;
    private ObjectListNode next;

    /** Default constructor
     * 
     */  
    public ObjectListNode() {
        info = null;
        next = null;
    }

    /** Constructor
     *
     *@param Object o 
     */
    public ObjectListNode (Object o) {
        info = o;
        next = null;
    }    

    /**Constructor
     * 
     * @param Object o, ObjectlistNode p 
     */
    public ObjectListNode (Object o, ObjectListNode p) {
        info = o;
        next = p;
    }       

    /** Sets info field
     *
     *@param Object o
     */
    public void setInfo(Object o) {
        info = o;
    }    

    /** Returns object in info field
     * 
     * @return Object info
     */
    public Object getInfo() {
        return info;
    }

    /** Sets next field
     * 
     * @param ObjectListNode p
     */
    public void setNext(ObjectListNode p) {
        next = p;
    }

    /** Returns object in info field
     * 
     * @return ObjectListNode next 
     */
    public ObjectListNode getNext() {
        return next;
    }
}
