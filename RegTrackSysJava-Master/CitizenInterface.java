
/** Citizen Interface implented by class.
 * @author Matthew Alisangco
 * @since 12/11/2013
 * @version v1.0
 */
public interface CitizenInterface{

    /**
     * @return the firstName
     */
    String getFirstName();

    /**
     * @return the lastName
     */
    String getLastName();

    /**
     * @param firstName the firstName to set
     */
    void setFirstName(String infirstName);

    /**
     * @param lastName the lastName to set
     */
    void setLastName(String inlastName);

    /**
     * Overridden toString()
     *
     * @return returns formatted name/plates/years&months.
     */
    
    public String toString();
}