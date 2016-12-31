import java.io.*;
/**
 *  Driver class, executes all files 
 * 
 * @author      Matthew Alisangco
 * @version     V1.0x
 */


public class Driver
{
    public static void main(String[] args) throws IOException
    {
        //PrintWriter pw = new PrintWriter(new FileWriter("csis.txt"));
        SuperOutput out = new SuperOutput("csis.txt");
        
        MFQ mfq = new MFQ(out);
        mfq.getJobs();
        mfq.outputHeader();
        mfq.runSimulation();
        mfq.outStats();
        out.close();
    }
}