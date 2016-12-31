/**
 * ObjectQueue Interface
 * 
 * @author   Matthew Alisangco  
 * @version V1.0
 */
public interface ObjectQueueInterface
{
    /** 
     * 
     * @return      returns true if the queue is empty, false otherwise
     */
    public boolean isEmpty();
    
    /**
     * 
     * 
     * @return      returns true if the queue is full, false otherwise
     */
    public boolean isFull();
    
    /**
     * clear        removes all objects from the queue
     */
    public void clear();
    
    /** 
     * @param       the object to be places onto the queue
     */
    public void insert(Object o);
    
    /**  
     * @return      the object removed
     */
    public Object remove();
    
    /** 
     * @return      the object at the front of the queue
     */
    public Object query();
}
