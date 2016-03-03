import java.io.*;
import java.util.Scanner;
import java.io.File;
import java.math.*;
import java.io.PrintWriter;
/**
 * Driver to simulate Payroll Software. 
 * 
 * @author Matthew Alisangco
 * @version V1.0x
 */
public class Driver
{
    public static void main (String[] args)throws IOException
    {
       
        PrintWriter pw = new PrintWriter(new FileWriter("csis.txt"));
        Payroll payroll = new Payroll(pw);        
        //creats payroll class
        payroll.getEmployees();
        payroll.employeeTable();
        payroll.numEmployees();
        payroll.women();
        payroll.Look4Vets(); //salary>35k && workyears>5
        payroll.giveraise();
        payroll.SortLastN();
        payroll.hireEmployees();
        payroll.fireEmployees();
      
        pw.close();
        
    }
}

