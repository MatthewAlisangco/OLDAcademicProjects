import java.io.*;
import java.util.Scanner;
import java.io.File;
import java.math.*;
import java.io.PrintWriter;
import java.text.*;
/**
 * Employee Data is handled via payroll class,  
 * 
 * @author Matthew Alisangco
 * @version V1.2x rv
 */
public class Payroll
{
    ObjectList employeeList; 
    private PrintWriter pw;
    DecimalFormat dF = new DecimalFormat("##.00");
    
    /**import a printwriter class.  
     * 
     */
    public Payroll(PrintWriter inpw){
        pw=inpw;
        employeeList = new ObjectList();
    }
    
    /**Default Constructor
     * 
     */
    public Payroll()
    {
        employeeList = new ObjectList();
    }
    
    /**Method reads in our first set of raw data, boxes it into stringarray then converted to listobjec
     *  
     * 
     */
    public void getEmployees () 
    {
        //instance variables 
        String [] indata;
        String line;
        String firstName;
        String lastName;
        String gender;
        int  tenure;
        String rate;
        double salary;
        

        Scanner fileScan = null;
        //a try and catch block to see if .txt file exist.  
        try
        {
            fileScan = new Scanner(new File("payfile.txt"));
        }
        catch(FileNotFoundException e)
        {
            //always use this catch to account if file not found and use messages for users.
            System.out.println("This file does not exist:     Shutting Down");
            System.exit(0);
        }

        while(fileScan.hasNext())
        {
            line = fileScan.nextLine();
            indata = line.split("[ ]+");

            firstName = indata [0];
            lastName = indata [1];
            gender = indata [2];
            tenure = Integer.parseInt(indata[3]);
            rate =  indata[4];
            salary =Double.parseDouble((indata[5]));

            employeeList.addLast((Object)new Employee(firstName,lastName,gender,tenure,rate,salary));

        }

    }
    
    /**traverse the List and output each node into a table format
     */
    public void employeeTable()
    {
        Employee node;    
        ObjectListNode p = employeeList.getFirstNode();
        //print header
        System.out.println("Name                 Gender          Tenure            Rate             Salary");
        pw.println("Name                 Gender          Tenure            Rate             Salary");
        //print each employee
        while (p != null) {
            node=(Employee)p.getInfo();
            System.out.printf("%s %s\t\t%s\t\t%d\t\t%s\t\t%.2f\n",node.firstName,node.lastName,node.gender,node.tenure,node.rate,node.salary);
            pw.printf(node.firstName+"  "+node.lastName+"\t\t"+node.gender+"\t\t"+node.tenure+"\t\t"+node.rate+"\t\t"+(dF.format(node.salary))+"%n");
            p = p.getNext();
        }
        System.out.println();//for a space in output
        pw.println();
    }

    /**traverse list and returns number of employees(using count method in object list)
     */
    public void numEmployees(){
        System.out.println("Number of Employees in Company: "+ employeeList.size()+ "\n");
        pw.println("Number of Employees in Company: "+ employeeList.size());
        pw.println();
    }

    /**output first names of women on the payroll. 
     */
    public void women(){
        Employee employee;    
        ObjectListNode p = employeeList.getFirstNode();
        String gender;

        System.out.println("Women on payroll:");
        pw.println("Women on payroll:");
        while (p != null) {
            employee=(Employee)p.getInfo();
            gender=employee.gender;
            
            if(gender.equals("F")){
                System.out.println(employee.firstName);
                pw.println(employee.firstName);
            }
            p = p.getNext();
        }
        System.out.println();
        pw.println();
    }

    /**output weekly  employee whos salary > 35,000 && work years > 5.
     * 
     */
    public void Look4Vets(){
        final int WEEKS_IN_YEAR=52;
        Employee employee;    
        ObjectListNode p = employeeList.getFirstNode();
        double yearlySal; //how much money that employee makes a year

        while (p != null) {
            employee=(Employee)p.getInfo(); 
            if((employee.rate).equals("W")){
                yearlySal=(employee.salary)*WEEKS_IN_YEAR;
                if(yearlySal>35000 && employee.tenure >= 5 )
                {
                    System.out.println("Weekly employees who make more than $35,000 and with the company for 5 years:");
                    System.out.println(employee.firstName+" "+employee.lastName);
                    pw.println("Weekly employees who make more than $35,000 and with the company for 5 years:");
                    pw.println(employee.firstName+" "+employee.lastName);
                }
            }
            p = p.getNext();
        }
        System.out.println(); 
        pw.println();
    }

    /**give raise to employees
     */
    public void giveraise(){
        Employee employee;    
        ObjectListNode p = employeeList.getFirstNode();
        String rate;
        double oldSalary;
        //headings
        System.out.println("Employees who recieved a raise:");
        System.out.println("Name\t\t\tNew Salary");
        pw.println("Employees who recieved a raise:");
        pw.println("Name\t\t\tNew Salary");

        while (p != null) {
            employee=(Employee)p.getInfo();
            rate=employee.rate;
            oldSalary=employee.salary;//salary at the start
            if(rate.equals("H")){
                if(oldSalary< 10.00){
                    employee.salary+= .75;
                }
            }
            else {//if rate is weekly
                if(oldSalary<350.00){
                    employee.salary+= 50.00;

                }
            }
            if(oldSalary<employee.salary)//if recieved a raise
            {
                System.out.printf("%s %s\t\t%.2f\n",employee.firstName,employee.lastName,employee.salary);
                pw.printf(employee.firstName+"\t"+employee.lastName+"    \t"+(dF.format(employee.salary))+"%n");
            }
            p = p.getNext();
        }
        System.out.println();
        pw.println();
        //add space in output
    }

    /** sorts list by Lastname
     * 
     */
    public void SortLastN(){
        ObjectList newList= new ObjectList();//the ordered list
        Object object;
        ObjectListNode p= new ObjectListNode();
        Employee employee;
        while (employeeList.getFirstNode()!= null) 
        {
            object=employeeList.removeFirst();
            newList.insert(object);
        }
        employeeList=newList;
        p=employeeList.getFirstNode();
        System.out.println("Employees in alphabetical order by last name and their salaries:");
        pw.println("Employees in alphabetical order by last name and their salaries:");
        //print the employees
        while(p!= null)
        {
            employee=(Employee)p.getInfo();
            System.out.printf("%s %s\t%.2f\n",employee.firstName,employee.lastName,employee.salary);
            pw.printf(employee.firstName+"\t"+employee.lastName+"     \t"+(dF.format(employee.salary))+"%n");
            p = p.getNext();          
        }
        System.out.println();      
        pw.println();
    }

    /**reads hirefile and inserts them into the linked list
     */
    public void hireEmployees() {
        Scanner fileScan = null;
        try
        {
            fileScan = new Scanner(new File("hirefile.txt"));
        }
        catch(FileNotFoundException e)
        {
            //always use this catch to account if file not found and use messages for users.
            System.out.println("This file does not exist:     Shutting Down");
            pw.println("This File does not exist:   Shutting Down");
            System.exit(0);
        }
        ObjectListNode p;
        Employee employee;
        String [] employeeInfo;
        String line;
        String firstName;
        String lastName;
        String gender;
        int tenure; 
        String rate;
        double salary;
        while(fileScan.hasNext()){
            line=fileScan.nextLine();
            employeeInfo= line.split("[ ]+");
            firstName= employeeInfo[0];
            lastName= employeeInfo[1];
            gender= employeeInfo[2];
            tenure= Integer.parseInt(employeeInfo[3]);
            rate= employeeInfo[4];
            salary=Double.parseDouble(employeeInfo[5]);
            employeeList.insert((Object)new Employee(firstName,lastName,gender,tenure,rate,salary));
        }

        p= employeeList.getFirstNode();
        System.out.println("List afer certain employees were hired:");
        pw.println("List afer certain employees were hired:");
        while(p!= null){
            employee=(Employee) p.getInfo();
            System.out.println(employee.firstName+" "+employee.lastName);
            pw.println(employee.firstName+" "+employee.lastName);
            p = p.getNext();
        }
        System.out.println();
        pw.println();
    }

    /**reads firefile and fires employees by removing from linked list
     */
    public void fireEmployees() throws IOException{
        Scanner toFire= new Scanner(new File("firefile.txt"));//read new employees
        String [] employeeInfo;
        ObjectListNode p;
        Employee employee;
        String line;
        String firstName;
        String lastName;
        String gender;
        int tenure; 
        String rate;
        double salary;
        System.out.println("List after certain employees were fired:");
        pw.println("List after certain employees were fired:");
        while(toFire.hasNext()){
            line=toFire.nextLine();
            employeeInfo= line.split("[ ]+");
            firstName= employeeInfo[0];
            lastName= employeeInfo[1];
            gender= employeeInfo[2];
            employeeList.remove((Object)new Employee(firstName,lastName,gender));
        }

        p=employeeList.getFirstNode();
        while(p!=null){
            employee=(Employee)p.getInfo();
            System.out.println(employee.firstName+" "+employee.lastName);
            pw.println(employee.firstName+" "+employee.lastName);
            p = p.getNext();
        }
        System.out.println();
        pw.println();

    }
}
