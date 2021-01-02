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
                boolean exit = true;
                while (exit){
                    logger.info("Do you wish to 1. create a new location 2. update a location 3. delete a location 4. Show all locations 5. Exit");
                    int option = scn.nextInt();
                    Results res = new Results(inputFile);
                    if(option == 5){
                        for (String location: locations){
                            res.storeNewResult(location);
                        }
                        res.writeResults();
                        res.closeMyFile();
                        break;
                    }
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
                                locations.add(newLocation);
                                logger.info("New location has been created \n");
                                break;
                        case 2:
                            logger.info("______________________UPDATE LOCATION_______________________ \n\n");
                            logger.info("Enter the contact number of the location you want to update");
                            String phoneNumber = scn.next();
                            for(int i=0; i<locations.size(); i++) {
                                String loc = locations.get(i);
                                String[] arrLoc = loc.split(", ");
                                List<String> list = new ArrayList<>(Arrays.asList(arrLoc));
                                if (list.contains(phoneNumber)) {
                                    logger.info("enter the updated phone number");
                                    String newNumber = scn.next();
                                    list.remove(phoneNumber);
                                    list.add(newNumber);
                                    String updatedAddress = "";
                                    for (String sample : list) {
                                        updatedAddress += sample + ", ";
                                    }
                                    logger.info("updated location" +updatedAddress);
                                    locations.set(i, updatedAddress);
                                    break;
                                    }
                                }
                                break;

                        case 3:
                            logger.info("______________________DELETE LOCATION_______________________ \n\n");
                            logger.info("Enter the contact number of the location you want to delete");
                            String phone_Number = scn.next();
                            for(int i=0; i<locations.size(); i++){
                                String loc = locations.get(i);
                                String[] arrLoc = loc.split(", ");
                                List<String> list = new ArrayList<>(Arrays.asList(arrLoc));
                                if (list.contains(phone_Number)) {
                                    logger.info("Deleted location");
                                    locations.remove(i);
                                }
                            }
                            break;

                        case 4:
                            logger.info("______________________DISPLAYING LOCATION_______________________ \n\n");
                            for (String loc: locations){
                                System.out.println(loc +"\n");
                            }
                            break;

                        default:
                            logger.info("Enter the correct choice");
                            break;
                    }
                }
        }
    }
}
