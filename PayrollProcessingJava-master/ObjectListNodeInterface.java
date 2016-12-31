
/**
 * ObjectListNode class implements the ObjectListNodeInterface.  
 * 
 * @author Matthew Alisangco
 * @version V1.0
 */
public interface ObjectListNodeInterface {
    /** Sets info field
     */
    public void setInfo(ObjectList o);

    /** Returns object in info field
     */
    public Object getInfo() ;

    /** Sets next field
     */
    public void setNext() ;

    /** Returns object in info field
     */
    public ObjectListNode getNext() ;
}
