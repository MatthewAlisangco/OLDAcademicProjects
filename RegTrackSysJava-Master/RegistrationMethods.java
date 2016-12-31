import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;

/**
 * The JJElephant itself, creates two arrays, proccess input and output and based off the csv file, creates an array to process user input.
 * 
 * @author Matthew Alisangco
 * @version V1.5
 * @since 12/11/2013
 */
public class RegistrationMethods extends CarOwner implements RegistrationMethodsInterface
{
    //string instance variables to take input from Scanner.
    String inputFile ;
    String outputFile;

    /*Array that is generated based off  owners whos reg. will expire in 3 months or less
     * 
     * @return almstdue
     */
    public CarOwner[] flagAlmostDueOwners(CarOwner[] inArray)
    {
        //instance variables 
        int indexar1 = 0;
        int index = 0;

        //finds all drivers that need to re-register within 3 months, creates an array based on if condition itteration.         

        for(int i=0; i<inArray.length; i ++)
        {

            int year =inArray[i].getYear();
            int month =inArray[i].getMonth();
            int TotalMonths = REG_MONTH +((12*REG_YEAR));

            if(TotalMonths <= month+((year+1)*12) && TotalMonths >= (month+(((year+1)*12)-3)))
            {
                indexar1 ++;

            }

        }

        CarOwner [] almstdue = new CarOwner[indexar1];
        for(int i = 0; i<inArray.length; i ++)
        {

            int year =inArray[i].getYear();
            int month =inArray[i].getMonth();
            int TotalMonths = REG_MONTH +((12*REG_YEAR));

            if (TotalMonths <= month+((year+1)*12) && TotalMonths >= (month+(((year+1)*12)-3)))
            {
                almstdue [index] = inArray[i]; 
                index ++;

            }

        }
        return almstdue;
    }

    /*Find drivers that are over a year past due, create a whole array based on owners who are passed due. and DINQEM'
     * 
     * @return dinqowners
     */
    public CarOwner [] flagDinqOwners(CarOwner[] inArray)
    {

        int count = 0;
        int index = 0;

        for(int i=0; i<inArray.length; i ++)
        {

            int year =inArray[i].getYear();
            int month =inArray[i].getMonth();
            int TotalMonths = month+((year+1)*12);

            if(TotalMonths > month+(year*12)+9 && (TotalMonths < REG_MONTH+(REG_YEAR*12)))
            {
                count ++;

            }

        }

        CarOwner [] dinqowners = new CarOwner[count];
        for(int i = 0; i<inArray.length; i ++)
        {

            int year =inArray[i].getYear();
            int month =inArray[i].getMonth();
            int TotalMonths = month +(12*(year+1));

            if(TotalMonths > month+(year*12)+9 && (TotalMonths < REG_MONTH+(REG_YEAR*12)))
            {
                dinqowners [index] = inArray[i]; 
                index ++;

            }

        }
        return dinqowners;
    }

    /*Process Array to file respectfully.
     * 
     */
    public void printArrayToFile(CarOwner[] newArray, String inMsg)
    {
        //create a PrintWriter set null.
        PrintWriter outputStream= null;

        try
        {
            //   will append if true. ->>add in to existing file
            outputStream = new PrintWriter(new FileOutputStream (outputFile, true));

        }
        catch(FileNotFoundException e)
        {
            //catch errors.
            System.out.println("This file does not exist:     Shutting Down");
            System.exit(0);
        }       

        outputStream.println(inMsg);
        outputStream.println();
        outputStream.println("Name\t\t\t"+"License\t\t"+"Last Renewal Date");        

        for(int i=0; i < newArray.length; i++)
        {

            if(newArray[i].getMonth()!= 0)
                outputStream.println(newArray[i]);
        }

        outputStream.println();
        outputStream.close(); //close stream
    }

    /*Process userinput location creates an array based on ","
     * 
     */
    public void processText2Array(CarOwner[] inArray) 
    {

        Scanner Keyboard = new Scanner(System.in);

        Scanner inputStream = null;
        try
        {
            //new file set by userinput
            inputStream = new Scanner(new File(inputFile));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("This file does not exist: Shutting Down!!!!");
            System.exit(0);
        }

        int index = 0;

        while(inputStream.hasNextLine())
        {

            String line = inputStream.nextLine();
            //turns string into an array of things
            String [] indata = line.split(",");
            //assign variables in respect to split and index position.
            String firstName = indata [0];
            String lastName = indata[1];
            String licenseNumber = indata[2];

            int month = Integer.parseInt(indata[3]);
            int year = Integer.parseInt(indata[4]);

            inArray [index] = new CarOwner(firstName, lastName, licenseNumber, month, year);
            index++;
        }
    }

    /* Prompt user for file path, and output filename.
     * 
     */
    public void setFileNames() 
    {

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter a file location");
        inputFile = keyboard.next();
        System.out.println("Please enter output file name");
        outputFile = keyboard.next();
        
        System.out.println("\n"+ inputFile+" file"+ "\nhas been exported, we will now end program");

    }
}

