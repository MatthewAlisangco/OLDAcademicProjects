
/**
 * Citizen class implements Citizen Interface used methods are parent of CarOwner
 * 
 * 
 * @author Matthew Alisangco
 * @since 12/11/2013
 * @version v1.1
 * 
 */
public class Citizen implements CitizenInterface
{
    //instance variables

    String firstName = null;
    String lastName = "";

    /**default setter 
     * 
     */
    Citizen()
    {
        firstName = "";
        lastName= "";
    }

    /**default setter with paramters
     * 
     * @param infirstName
     * @param inlastName
     * 
     */    
    Citizen(String infirstName, String inlastName)
    {
        firstName = infirstName;
        lastName = inlastName;
    }

    /**Getter method
     * 
     * @return firstName
     */    
    public String getFirstName()
    {
        return firstName;
    }

    /**Getter Method
     * 
     * @return lastName
     */
    public String getLastName()
    {
       return lastName;
    }

    /**Setter Method for firstName 
     * 
     * @param infistName
     *  
     */    
    public void setFirstName(String infirstName)
    {
        firstName = infirstName;
    }

    /**Setter Method for lastName 
     * 
     *@param inlastNameName 
     */    
    public void setLastName(String inlastName)
    {
        lastName = inlastName;
    }

    /**To String method to print all variables
     * 
     * @return s 
     */    
    public String toString()
    {
        String s = firstName + " " +lastName;

        return s;
    }

}