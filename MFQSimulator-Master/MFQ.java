import java.io.*;

/**
 * Multi Level Feedback Queue Class.
 * 
 * @author      Matthew Alisangco
 * @version     V1.0
 */
public class MFQ
{
    // instance variables - replace the example below with your own
    SuperOutput out;
    ObjectQueue qIn;
    ObjectQueue[] jobQueue;
    CPU cpu;

    //stats
    int totalJobs;
    int totalProcessTime;
    float averageResponseTime;
    float averageTurnaroundTime;
    float averageWaitingTime;
    float averageThroughput;
    int totalCpuIdleTime;

    /**
     * Constructor for objects of class MFQ
     */
    public MFQ(SuperOutput o)
    {
        out = o;
        qIn = new ObjectQueue();
        jobQueue = new ObjectQueue[4];
        for(int i = 0; i < 4; i++)
            jobQueue[i] = new ObjectQueue();
        cpu = new CPU();
    }

    /**This methods will read in the text file and split th variables and place them inside th qIn.  
     * 
     */
    public void getJobs() throws IOException
    {
        RandomAccessFile raf = new RandomAccessFile("mfq.txt", "rw");
        String jobInfo;
        String[] tokens;

        while((jobInfo = raf.readLine()) != null)
        {
            tokens = jobInfo.split("[ ]+");
            qIn.insert(new Job(
                    Integer.parseInt(tokens[0]), 
                    Integer.parseInt(tokens[1]), 
                    Integer.parseInt(tokens[2])));
        }
    }

    /**
     * outputHeader     Writes the column headers to our output file
     */
    public void outputHeader()
    {
        out.output("\f\n\t\t\t\tCPU\tTotal\tLowest\n");
        out.output("\t\tSystem\t\tTime\tTime In\tLevel\n");
        out.output("Event\t\tTime\tPID\tNeeded\tSystem\tQueue\n\n");
    }

    /**This method adds a job to our cpu
     * 
     * 
     * @param   queue  
     */
    private void addJobToCPU(int queue)
    {
        cpu.job = (Job) jobQueue[queue].remove();
        cpu.isBusy = true;
        cpu.quantaClock = 2 << queue;
        
        //stats
        if(cpu.job.qLevel == 0)
            averageResponseTime += cpu.sysTime - cpu.job.arrivalTime;
    }

    /**
     * findNonEmptyQueue    searches through queues 1-4 to find a non empty queue
     * 
     * @param   start       the queue to start searching from
     * @return              the first non empty queue or 0 if all queues are empty
     */
    private int findNonEmptyQueue(int startQueue)
    {
        int i = startQueue;
        for(int j = 0; j < 4; j++, i = (i + 1) % 4)
        {
            if(!jobQueue[i].isEmpty())
                return i;
        }
        return 0; //all queues are empty
    }

    /**This methods is our bulk of code and will be our processor essentially.  
     * 
     */
    public void runSimulation()
    {
        int i = 0;
        boolean newJobArrived = false;
        //stats
        totalJobs = 0;
        totalProcessTime = 0;
        averageResponseTime = 0;
        averageTurnaroundTime = 0;
        averageWaitingTime = 0;
        averageThroughput = 0;
        totalCpuIdleTime = 0;

        while(!qIn.isEmpty() || !jobQueue[0].isEmpty() || !jobQueue[1].isEmpty() || !jobQueue[2].isEmpty() || !jobQueue[3].isEmpty() || cpu.isBusy)
        {
            cpu.sysTime++;
            newJobArrived = false;

            //Here we are seeing if a new job is entering our system.  If this is true, then we insert into our first level que.  
            if(!qIn.isEmpty() && cpu.sysTime == ((Job) qIn.query()).arrivalTime)
            {
                jobQueue[0].insert((Job) qIn.query());
                out.output("Arrived\t\t%d\t%d\t%d\n", cpu.sysTime, ((Job) qIn.query()).pid, ((Job) qIn.query()).cpuTime);
                qIn.remove();
                newJobArrived = true;
                //xtra credit(stat)
                totalJobs++;
            }

            //here we check to see if the cpu is busy.  If no then we submit appropiate jobs, if yes then we dour decrements.  
            if(cpu.isBusy)
            {
                cpu.quantaClock--;
                cpu.job.timeRemaining--;
                //xtra credit(stat)
                totalProcessTime++;
                //Now if our cpu is busy, we will check to see if our job is complete.  
                if(cpu.job.timeRemaining == 0)
                {
                    //if job is is complete, so our busy flag to false to open our job.  
                    cpu.isBusy = false;
                    out.output("Departure\t%d\t%d\t\t%d\t%d\n", cpu.sysTime, cpu.job.pid,(cpu.sysTime - cpu.job.arrivalTime), (i < 3) ? (i + 2) : 4); 
                   
                    //xtra credit (stat)
                    averageWaitingTime += (cpu.sysTime - cpu.job.arrivalTime) - cpu.job.cpuTime;
                    averageTurnaroundTime += cpu.sysTime - cpu.job.arrivalTime;
                    //check if current queue is empty and if so find a non empty queue
                    i = findNonEmptyQueue(i);
                    if(!jobQueue[i].isEmpty())
                        addJobToCPU(i);
                }
                //if a new job has arrived OR the allocated time has run out, there is atleast one job in any queue then prempt and find a new one. 
                else if(newJobArrived == true || (cpu.quantaClock <= 0 && (!jobQueue[0].isEmpty() || !jobQueue[1].isEmpty() || !jobQueue[2].isEmpty() || !jobQueue[3].isEmpty())))
                {
                    jobQueue[(i < 3) ? i + 1 : 3].insert(cpu.job); //preemption
                    cpu.job.qLevel = (i < 3) ? i + 1 : 3;
                    //new job has arrived, job preempted, set queue level to 0
                    if(newJobArrived == true)
                        i = 0;
                    cpu.isBusy = false;
                    //check if current queue is empty and if so find a non empty queue
                    i = findNonEmptyQueue(i);
                    if(!jobQueue[i].isEmpty())
                        addJobToCPU(i);
                }
            }
            //cpu is not busy
            else if(!jobQueue[i].isEmpty()) //add job to cpu if there is any in our lowest level queue
                addJobToCPU(i);
            else //there are 0 processes in all queues
            {
                out.output("Waiting\t\t%d\n", cpu.sysTime);
                //stats
                totalCpuIdleTime++;
            }

            try {
                Thread.sleep(5);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        //xtra credit  (stat)
        averageResponseTime /= totalJobs;
        averageWaitingTime /= totalJobs;
        averageTurnaroundTime /= totalJobs;
        averageThroughput = (float)totalJobs / totalProcessTime;
    }

    /**
     * outStats     prints some stats from the last MFQ simulation
     */
    public void outStats()
    {
        out.output("%n%nTotal Number of Jobs:\t\t" + totalJobs+"%n");
        out.output("Total Process Time:\t\t"+totalProcessTime+"%n");
        out.output("Total CPU Idle Time:\t\t"+totalCpuIdleTime+"%n");
        out.output("Average Response Time:\t\t"+averageResponseTime+"%n");
        out.output("Average Turnaround Time:\t"+averageTurnaroundTime+"%n");
        out.output("Average Waiting Time:\t\t"+averageWaitingTime+"%n");
        out.output("Average Throughput:\t\t"+averageThroughput+"%n");
        
        out.close();
    }
}
