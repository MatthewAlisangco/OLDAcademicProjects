/**
 * CPU class- will hold all our variables for the current CPU class.  
 * 
 * @author      Matthew Alisangco
 * @version     V1.0
 */
public class CPU
{
    // instance variables - replace the example below with your own
    public Job job;         //our current job
    public int sysTime;     //our quantum clock
    public int quantaClock; //our quanta remaining on process
    public boolean isBusy;  //busy flag

    /**
     * Constructor for objects of class CPU
     */
    public CPU()
    {
        job = null;
        sysTime = 0;
        isBusy = false;
    }
}
