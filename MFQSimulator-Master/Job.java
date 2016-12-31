/**
 * Job class    holds all variable for any job
 * 
 * @author      Matthew Alisangco
 * @version     v.10
 */
public class Job
{
    // instance variables - replace the example below with your own
    public int arrivalTime;     //time arrived into the system
    public int cpuTime;         //time required to complete process
    public int pid;             //process identifier
    public int qLevel;          //which queue level the job is located in
    public int timeRemaining;   //time remaining to complete

    /**
     * Constructor for objects of class Job
     */
    public Job(int arrT, int id, int cpuT)
    {
        arrivalTime = arrT;
        cpuTime = cpuT;
        pid = id;
        qLevel = 0;
        timeRemaining = cpuTime;
    }
}
