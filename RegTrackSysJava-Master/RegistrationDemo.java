 
/** RegistrationDemo Class, runs all methods.
 * @author Matthew Alisangco
 * @since 12/11/2013
 * @version v1.1
 */

import java.util.Arrays;

public class RegistrationDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RegistrationMethods RegistrationMethods = new RegistrationMethods();
		CarOwner[] ltState = new CarOwner[RegistrationMethods.ARRAY_SIZE];
		for(int x = 0; x < ltState.length ; x++){
			 ltState[x] = new CarOwner();  //default constructor
			
		}
		RegistrationMethods.setFileNames(); //input output set
		RegistrationMethods.processText2Array(ltState);
		RegistrationMethods.printArrayToFile(ltState, "initial set of CarOwners - unsorted");
	
		
		CarOwner[] ltStatecopy =  Arrays.copyOf(ltState, ltState.length);
		Arrays.sort(ltStatecopy);
		RegistrationMethods.printArrayToFile(ltStatecopy, "initial set of CarOwners - sorted");
		//new array of owners whos registration that is >1yr
		CarOwner[] dinqOwners = RegistrationMethods.flagDinqOwners(ltStatecopy);
		RegistrationMethods.printArrayToFile(dinqOwners, "Owners with Expired registration");
		RegistrationMethods.flagAlmostDueOwners(ltStatecopy); //flag dinqowners
		CarOwner[] almostDue = RegistrationMethods.flagAlmostDueOwners(ltStatecopy);
		RegistrationMethods.printArrayToFile(almostDue, "Owners with registration expiring in three months or less"); 
		//use for loop to create carowner
		// [] = carOwnerConstructor;
		//process csv file to array
		//

	}

}
