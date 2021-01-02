package Driver;

import Util.FileProcessor;
import Util.Results;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author saimilind
 *
 */
public class App
{
    public static final Logger logger = LogManager.getLogger(App.class.getName());
    public static void main( String[] args ) {
        System.out.println("1.CRUD Location 2.Display Menu 3.About Party Reservations 4.Contact us");
        Scanner scn = new Scanner(System.in);
        int choice = scn.nextInt();
        switch (choice) {
            case 1:
                String inputFile = "src/main/locations.txt";
                String input;
                ArrayList<String> locations = new ArrayList<>();
                FileProcessor fp = new FileProcessor(inputFile);
                while ((input = fp.readLine()) != null){
                    locations.add(input);
                }
                logger.info("Displaying all the current locations");
                logger.debug(locations);
                boolean exit = true;
                while (exit){
                    logger.info("Do you wish to 1. create a new location 2. update a location 3. delete a location");
                    int option = scn.nextInt();
                    Results res = new Results(inputFile);
                    switch (option){
                        case 1:
                                logger.info("______________________CREATE NEW LOCATION_______________________ \n\n");
                                logger.info("Please enter the new location's longitude");
                                double longitude = scn.nextDouble();
                                logger.info("Please enter the new location's latitude");
                                double latitude = scn.nextDouble();
                                scn.nextLine();
                                logger.info("Please Enter the new address \n");
                                String address = scn.nextLine();
                                logger.info("Enter the new location phone number");
                                String number = scn.next();
                                String newLocation = longitude + ", " + latitude + ", " + address + ", " + number;
                                res.storeNewResult(newLocation);
                                res.closeMyFile();
                                logger.info("New location has been created \n");
                                logger.info("Do you wish to exit yes or no?");
                                String txt = scn.next();
                                txt.toLowerCase();
                                if (txt.equals("yes")) {
                                    exit = false;
                                }
                                break;
                        case 2:
                            logger.info("______________________UPDATE LOCATION_______________________ \n\n");
                            logger.info("Enter the contact number of the location you want to update");
                            String phoneNumber = scn.next();
                            for(int i=0; i<locations.size(); i++){
                                String loc = locations.get(i);
                                String[] arrLoc = loc.split(",");
                                List<String> list = Arrays.asList(arrLoc);
                                if(list.contains(phoneNumber)){
                                    logger.info("enter the updated phone number");
                                    String newNumber = scn.next();
                                    list.remove(list.size() - 1);
                                    list.add(newNumber);
                                    String updatedAddress = "";
                                    for(String sample : list){
                                        updatedAddress += sample + ", ";
                                    }
                                    locations.add(i, updatedAddress);
                                }

                            }
                    }
                }
        }
    }
}
