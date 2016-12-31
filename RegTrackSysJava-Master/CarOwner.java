
/**
 * Car owner extends to Citizen and implements CarOwnerInterface.  
 * 
 * 
 * @author Matthew Alisangco
 * @version v1.0
 * @since 12/11/2013
 */
public class CarOwner extends Citizen implements CarOwnerInterface
{
    //instance variables
    int month =0;
    int Year = 0;
    String license; 

    /*Default Constructor
     * 
     * default assignment
     */
    CarOwner()
    {
        super();
        month = 0;
        Year = 0;
        license = ""; 
    }

    /*OVERLOAD constructor.
     * 
     * @param innFirst
     * @param innLast
     * @param LicenseNumer
     * @param innMonth
     * @param innYear 
     */
    CarOwner(String innFirst, String innLast, String licenseNumber, int innMonth, int innYear )
    {
        super(innFirst, innLast);
        license = licenseNumber;
        month = innMonth;
        Year = innYear;
    }

    /*Compares object with another.
     * 
     * @param o
     * 
     * @return x
     * 
     */
    public int compareTo(Object o)
    {
        
        int x=0; 
        // check to see if not null and is a instance of CarOwner
        if((o != null)&&(o instanceof CarOwner))

        {
            //if true create carOwner class, cast it.
            CarOwner TestObject =(CarOwner) o;
            // sort by using the month and dates so when we use sortArray it will sort by this method.
            int thisclass = getMonth() +(getYear()*12);
            int totalmonth = TestObject.getMonth()+(TestObject.getYear()*12);

            int totalDate = RegistrationMethodsInterface.REG_MONTH +(RegistrationMethodsInterface.REG_YEAR*12);
            //assign our values below or above each other in order.
            if(totalmonth > thisclass  && totalmonth < totalDate)
            {
                x = -1;

            }
            else if (totalmonth < thisclass && thisclass < totalDate)
            {
                x = 1;

            }
            else
            {
                x = 0;

            }

        }
        return x;


    }

    /*Getter Method for year of registration
     * 
     * @return Year
     */
    public int getYear()
    {
        return Year;
    }

    /*getter method for month of registration
     * 
     * @return month;
     */
    public int getMonth()
    {
        return month;
    }

    /*getter method for license place number
     *     * 
     * @return license
     */
    public String getNumber()
    {
        return license;
    }

    /*String method.  Overide parent toString from Citizen  
     * 
     * @return s;
     * 
     */
    public String toString()
    {
        String s = super.toString()+"\t\t" +license+"\t\t"+month+"/"+Year;
        return s;
    }
}
