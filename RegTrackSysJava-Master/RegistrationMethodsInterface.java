

/**
 * @author Rick Cassoni, rcassoni@palomar.edu
 * @since 5/15/2013
 * @version v1.1
 */
public interface RegistrationMethodsInterface extends CarOwnerInterface{

    public static final int ARRAY_SIZE = 25;
    public static final int REG_MONTH = 4;
    public static final int REG_YEAR = 2013;

    /**
     * Method that generates and returns an array for vehicles whose
     * registration will expire in three months or less. The state of Looney
     * Tunes sends a reminder three months out to the car owner.
     *
     * @param inArray
     * @return CarOwner[] array based on above
     */
    CarOwner[] flagAlmostDueOwners(CarOwner[] inArray);

    /**
     * Method that generates and returns an array for vehicles whose
     * registration have expired defined as registration is over 12 months old
     * based on current REG_MONTH and REG_YEAR.
     *
     * @param inArray
     * @return CarOwner[] array based on above
     */
    CarOwner[] flagDinqOwners(CarOwner[] inArray);

    /**
     * Prints out array passed in based on toString() along with passed in
     * message
     *
     * @param inArray CarOwner[] array passed in
     * @param inMsg Message specific to the array being printed
     */
    void printArrayToFile(CarOwner[] inArray, String inMsg);

    /**
     * Takes a csv file input, parses out each record and creates a CarOwner
     * object and then puts into a CarOwner[] array that is passed into the
     * method
     *
     * @param inArray
     */
    void processText2Array(CarOwner[] inArray);

    /**
     * Prints messages to user to request input file name and then output 
     * file name and saves to instance vars inputFile and outputFile.  
     */
    void setFileNames();
}