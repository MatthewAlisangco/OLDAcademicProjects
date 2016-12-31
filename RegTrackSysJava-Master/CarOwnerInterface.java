

/**
 * @author Matthew Alisangco
 * @since 12/10/2013
 * @version v1.0
 */
public interface CarOwnerInterface extends Comparable {

    /**
     *  compareTo() for Arrays.sort
     * @param o Passed in object
     * @return -1 if calling object before o, 1 if calling object after o,
     * 0 if the same
     */
    @Override
    int compareTo(Object o);

    /**
     * @return the month
     */
    int getMonth();

    /**
     * @return the license
     */
    String getNumber();

    /**
     * @return the year
     */
    int getYear();

    /**
     * Overridden toString()
     * @return formatted toString that includes output for file.
     * 
     */
    String toString();
}
