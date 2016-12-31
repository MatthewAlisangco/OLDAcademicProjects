/**Super output to write our .txt file 
 * 
 */
import java.io.*;
public class SuperOutput {
    PrintWriter pw;
    /**
     * Constructor for SuperOutput objects
     * @param   fileName  the name of the file to print to
     */
    public SuperOutput(String fileName) {
        try {
            pw = new PrintWriter(new FileWriter(fileName));
        }
        catch (IOException e) {
            System.out.printf("File %s could not be opened for output.\n", fileName);
            pw = null;
        }
    }

    /**This method will forward our string and var args to print statements.  
     * 
     * @param string the string to print
     */
    public void output(String string, Object... args) {
        if (pw == null)
            return;
        System.out.printf(string, args);
        pw.printf(string, args);
    }

    /**This method will forward a string and our var args to a file   
     * 
     * @param string the string to print
     */
    public void outputFile(String string, Object... args) {
        if (pw == null)
            return;
        pw.printf(string, args);
    }

    /**
     * Closes the SuperOutput object
     */
    public void close () {
        if (pw != null) {
            pw.close();
        }
    }
}