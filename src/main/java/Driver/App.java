package Driver;

import Util.FileProcessor;
import Util.Results;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

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
            case 2:
                String inputFileTwo = "src/main/menus.txt";
                String inputTwo;
                ArrayList<String> menus = new ArrayList<>();
                FileProcessor fpOne = new FileProcessor(inputFileTwo);
                while ((input = fpOne.readLine()) != null){
                    menus.add(input);
                }

                HashMap<String, Double> specialMenu = new HashMap<>();
                HashMap<String, Double> mainMenu = new HashMap<>();
                HashMap<String, Double> desserts = new HashMap<>();

                //read data from file and store it into resp. HashMap

                for (String menu: menus){
                    if(menu.contains("SpecialMenu")) {
                       String[] arr = menu.split(", ");
                       specialMenu.put(arr[1].toLowerCase(), Double.parseDouble(arr[2]));
                    }else if(menu.contains("MainMenu")) {
                        String[] arr = menu.split(", ");
                        mainMenu.put(arr[1].toLowerCase(), Double.parseDouble(arr[2]));
                    } else if(menu.contains("Desserts")) {
                        String[] arr = menu.split(", ");
                        desserts.put(arr[1].toLowerCase(), Double.parseDouble(arr[2]));
                    }
                }
                while (true){
                        logger.info("\n Do you wish to 1. create a new menu item 2. update a menu item 3. delete a menu item 4. Show all menu items 5. Exit");
                        int options = scn.nextInt();
                        if(options == 5 ){
                            Results res = new Results(inputFileTwo);
                            for (Map.Entry<String, Double> doubleEntry : specialMenu.entrySet()) {
                                    Map.Entry mapElement = doubleEntry;
                                    double mrp = (double) mapElement.getValue();
                                    String elem = "SpecialMenu ," + mapElement.getKey() + ", " + mrp;
                                    res.storeNewResult(elem);
                                }
                                for (Map.Entry<String, Double> stringDoubleEntry : mainMenu.entrySet()) {
                                    Map.Entry mapElement = stringDoubleEntry;
                                    double mrp = (double) mapElement.getValue();
                                    String elem = "MainMenu ," + mapElement.getKey() + ", " + mrp;
                                    res.storeNewResult(elem);
                                }
                                for (Map.Entry<String, Double> stringDoubleEntry : desserts.entrySet()) {
                                    Map.Entry mapElement = stringDoubleEntry;
                                    double mrp = (double) mapElement.getValue();
                                    String elem = "Desserts ," + mapElement.getKey() + ", " + mrp;
                                    res.storeNewResult(elem);
                                }
                            res.writeResults();
                            res.closeMyFile();
                            break;
                        }
                        switch (options){
                            case 1:
                                logger.info("______________________CREATE NEW MENU ITEM_______________________ \n\n");
                                logger.info("Please choose your menu type 1.Special Menu 2.Main Menu or 3.Desserts");
                                String opt = scn.next();
                                int choices = Integer.parseInt(opt);
                                logger.info("please enter the dish name");
                                scn.nextLine();
                                String dish = scn.nextLine();
                                logger.info("Enter the price of the dish");
                                Double cost = scn.nextDouble();
                                switch (choices) {
                                    case 1:
                                        specialMenu.put(dish.toLowerCase(), cost);
                                        break;
                                    case 2:
                                        mainMenu.put(dish.toLowerCase(), cost);
                                        break;
                                    case 3:
                                        desserts.put(dish.toLowerCase(), cost);
                                        break;
                                    default:
                                        logger.info("Please enter the correct choice");
                                        break;
                                }
                                break;
                            case 2:
                                logger.info("______________________UPDATE MENU ITEM_______________________ \n\n");
                                logger.info("Please choose your menu type 1.Special Menu 2.Main Menu or 3.Desserts");
                                String ch = scn.next();
                                int opts = Integer.parseInt(ch);
                                logger.info("Please enter the name of dish you want to update");
                                scn.nextLine();
                                String myDish = scn.nextLine();
                                logger.info("Please enter the new price");
                                double price = scn.nextDouble();
                                switch (opts){
                                    case 1:
                                        specialMenu.put(myDish.toLowerCase(), price);
                                        break;
                                    case 2:
                                        mainMenu.put(myDish.toLowerCase(), price);
                                        break;
                                    case 3:
                                        desserts.put(myDish.toLowerCase(), price);
                                        break;
                                    default:
                                        logger.info("Please enter the correct choice");
                                        break;
                                }
                                break;
                            case 3:
                                logger.info("______________________DELETE MENU ITEM_______________________ \n\n");
                                logger.info("Please choose your menu type 1.Special Menu 2.Main Menu or 3.Desserts");
                                String chs = scn.next();
                                int num = Integer.parseInt(chs);
                                logger.info("Please enter the name of dish you want to update");
                                scn.nextLine();
                                String delDish = scn.nextLine();
                                switch (num){
                                    case 1:
                                        specialMenu.remove(delDish.toLowerCase());
                                        break;
                                    case 2:
                                        mainMenu.remove(delDish.toLowerCase());
                                        break;
                                    case 3:
                                        desserts.remove(delDish.toLowerCase());
                                        break;
                                    default:
                                        logger.info("Please enter the correct choice");
                                        break;
                                }
                                break;
                            case 4:
                                logger.info("______________________DISPLAY ALL MENU ITEMS_______________________ \n\n");
                                logger.info("Please choose your menu type 1.Special Menu 2.Main Menu or 3.Desserts");
                                String optionss = scn.next();
                                int numbs = Integer.parseInt(optionss);
                                switch (numbs){
                                    case 1:
                                        for (Map.Entry<String, Double> doubleEntry : specialMenu.entrySet()) {
                                            Map.Entry mapElement = doubleEntry;
                                            double mrp = (double) mapElement.getValue();
                                            logger.info(mapElement.getKey() + " price: $" + mrp);
                                        }
                                        break;
                                    case 2:
                                        for (Map.Entry<String, Double> stringDoubleEntry : mainMenu.entrySet()) {
                                            Map.Entry mapElement = stringDoubleEntry;
                                            double mrp = (double) mapElement.getValue();
                                            logger.info(mapElement.getKey() + " price: $" + mrp);
                                        }
                                        break;
                                    case 3:
                                        for (Map.Entry<String, Double> stringDoubleEntry : desserts.entrySet()) {
                                            Map.Entry mapElement = stringDoubleEntry;
                                            double mrp = (double) mapElement.getValue();
                                            logger.info(mapElement.getKey() + " price: $" + mrp);
                                        }
                                        break;
                                    default:
                                        logger.info("Please enter the correct choice");
                                        break;
                                }
                                break;

                            default:
                                logger.info("Please enter the correct choice");
                                break;
                        }
                }
        }
    }
}
