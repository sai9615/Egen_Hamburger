package Driver;

import TexasBurger.Locations.Location;
import TexasBurger.Menus.Menu;
import TexasBurger.Reservations.Reservations;
import FileProcessors.FileProcessor;
import FileProcessors.Results;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * @author saimilind
 */
public class App {
    public static final Logger logger = LogManager.getLogger(App.class.getName());

    /**
     * @param args
     */
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n------------------ADMIN PANEL------------------\n");
            System.out.println("1.Location 2.Menu 3.Party Reservations 4.Exit \n");
            int choice =0;
            Scanner scn = new Scanner(System.in);
            try {
                choice = scn.nextInt();
                if(choice >=5 || choice <=0){
                    throw new Exception("Option doesn't exist");
                }
            } catch (Exception e) {
                logger.error(e);
                logger.info("Exiting due to exception, " + e + " Please choose correct option again");
                main(null);
                break;
            }
            switch (choice) {
                case 1:
                    String inputFile = "src/main/locations.txt";
                    String input;
                    ArrayList<String> locations = new ArrayList<>();
                    FileProcessor fp = new FileProcessor(inputFile);
                    while ((input = fp.readLine()) != null) {
                        locations.add(input);
                    }
                    Location location = new Location(locations);
                    while (true) {
                        int option = 0;
                        Results res = new Results(inputFile);
                        try {
                            logger.info("Do you wish to 1. create a new location 2. update a location 3. delete a location 4. Show all locations 5. Exit");
                            option = scn.nextInt();
                            if (option >= 6) {
                                throw new Exception("Option doesn't exist");
                            }
                        } catch (Exception e) {
                            logger.error(e);
                            logger.info("Exiting due to exception, " + e + " Please choose correct option again");
                            location.storeLocations(res);
                            break;
                        }
                        switch (option) {
                            case 1:
                                location.createLocation();
                                break;
                            case 2:
                                location.updateLocation();
                                break;

                            case 3:
                                location.deleteLocation();
                                break;

                            case 4:
                                location.readLocation();
                                break;
                            case 5:
                                location.storeLocations(res);
                                break;
                            default:
                                logger.info("Enter the correct choice");
                                break;
                        }
                        if (option == 5) {
                            break;
                        }
                    }
                    break;
                case 2:
                    String inputFileTwo = "src/main/menus.txt";
                    String inputTwo;
                    ArrayList<String> menus = new ArrayList<>();
                    FileProcessor fpOne = new FileProcessor(inputFileTwo);
                    while ((inputTwo = fpOne.readLine()) != null) {
                        menus.add(inputTwo);
                    }

                    HashMap<String, Double> specialMenu = new HashMap<>();
                    HashMap<String, Double> mainMenu = new HashMap<>();
                    HashMap<String, Double> dessert = new HashMap<>();

                    //read data from file and store it into resp. HashMap

                    for (String menu : menus) {
                        if (menu.contains("Special Menu")) {
                            String[] arr = menu.split(", ");
                            specialMenu.put(arr[1].toLowerCase(), Double.parseDouble(arr[2]));
                        } else if (menu.contains("Main Menu")) {
                            String[] arr = menu.split(", ");
                            mainMenu.put(arr[1].toLowerCase(), Double.parseDouble(arr[2]));
                        } else if (menu.contains("Desserts")) {
                            String[] arr = menu.split(", ");
                            dessert.put(arr[1].toLowerCase(), Double.parseDouble(arr[2]));
                        }
                    }

                    Menu specialMenus = new Menu(specialMenu, "Special Menu");
                    Menu mainMenus = new Menu(mainMenu, "Main Menu");
                    Menu desserts = new Menu(dessert, "Desserts");

                    HashMap<Menu, HashMap<String, Double>> menuHashMap = new HashMap<>();
                    menuHashMap.put(specialMenus, specialMenu);
                    menuHashMap.put(mainMenus, mainMenu);
                    menuHashMap.put(desserts, dessert);

                    while (true) {
                        int options = 0;
                        Results res = new Results(inputFileTwo);
                        App obj = new App();
                        try {
                            logger.info("\n Do you wish to 1. create a new menu item 2. update a menu item 3. delete a menu item 4. Show all menu items 5. Store all item and Exit");
                            options = scn.nextInt();
                            if (options >= 6) {
                                throw new Exception("Option doesn't exist");
                            }
                        } catch (Exception e) {
                            logger.error(e);
                            logger.info("Exiting due to exception, " + e + " Please choose correct option again");
                            obj.storeMenuResult(menuHashMap, res);
                            break;
                        }
                        switch (options) {
                            case 1:
                                logger.info("______________________CREATE NEW MENU ITEM_______________________ \n\n");
                                logger.info("Please choose your menu type 1.Special Menu 2.Main Menu or 3.Desserts");
                                String opt = scn.next();
                                int choices = Integer.parseInt(opt);
                                switch (choices) {
                                    case 1:
                                        specialMenus.createItem();
                                        break;
                                    case 2:
                                        mainMenus.createItem();
                                        break;
                                    case 3:
                                        desserts.createItem();
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
                                switch (opts) {
                                    case 1:
                                        specialMenus.updateItem();
                                        break;
                                    case 2:
                                        mainMenus.updateItem();
                                        break;
                                    case 3:
                                        desserts.updateItem();
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
                                switch (num) {
                                    case 1:
                                        specialMenus.deleteItem();
                                        break;
                                    case 2:
                                        mainMenus.deleteItem();
                                        break;
                                    case 3:
                                        desserts.deleteItem();
                                        break;
                                    default:
                                        logger.info("Please enter the correct choice");
                                        break;
                                }
                                break;
                            case 4:
                                logger.info("______________________DISPLAY MENU ITEMS_______________________ \n\n");
                                logger.info("Please choose your menu type 1.Special Menu 2.Main Menu or 3.Desserts");
                                String optionss = scn.next();
                                int numbs = Integer.parseInt(optionss);
                                switch (numbs) {
                                    case 1:
                                        specialMenus.readItem();
                                        break;
                                    case 2:
                                        mainMenus.readItem();
                                        break;
                                    case 3:
                                        desserts.readItem();
                                        break;
                                    default:
                                        logger.info("Please enter the correct choice");
                                        break;
                                }
                                break;
                            case 5:
                                logger.info("______________________STORE MENU ITEMS_______________________ \n\n");
                                obj.storeMenuResult(menuHashMap, res);
                                break;
                            default:
                                logger.info("Please enter the correct choice");
                                break;
                        }
                        if (options == 5) {
                            break;
                        }
                    }
                    break;
                case 3:
                    String inputFileThree = "src/main/reservations.txt";
                    String inputThree;
                    ArrayList<String> reservation = new ArrayList<>();
                    FileProcessor fpTwo = new FileProcessor(inputFileThree);
                    while ((inputThree = fpTwo.readLine()) != null) {
                        reservation.add(inputThree);
                    }
                    HashMap<String, Date> reserves = new HashMap<>();
                    Set<Date> dates = new HashSet<>();
                    for (String str : reservation) {
                        String[] arr = str.split(", ");
                        int[] dateArray = Arrays.stream(arr[1].split("/")).mapToInt(Integer::parseInt).toArray();
                        Date date = new Date(dateArray[2] - 1900, dateArray[1], dateArray[0]);
                        reserves.put(arr[0].toLowerCase(), date);
                        dates.add(date);
                    }
                    Reservations reservations = new Reservations(reserves, dates);
                    while (true) {
                        int options = 0;
                        Results res = new Results(inputFileThree);
                        try {
                            logger.info("\n Do you wish to 1. create a new reservation 2. update a reservation 3. delete a reservation 4. Show all reservation 5. Save all reservations and Exit");
                            options = scn.nextInt();
                            if (options >= 6) {
                                throw new Exception("Option doesn't exist");
                            }
                        } catch (Exception e) {
                            logger.error(e);
                            logger.info("Exiting due to exception, " + e + " Please choose correct option again");
                            reservations.storeAllReservations(res);
                            break;
                        }
                        switch (options) {
                            case 1:
                                reservations.createReservation();
                                break;
                            case 2:
                                reservations.updateReservation();
                                break;
                            case 3:
                                reservations.deleteReservation();
                                break;
                            case 4:
                                reservations.readReservation();
                                break;
                            case 5:
                                reservations.storeAllReservations(res);
                                res.writeResults();
                                res.closeMyFile();
                                break;
                        }
                        if (options == 5) {
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("\n------------------CLOSING THE ADMIN PANEL------------------\n");
                    break;
                default:
                    System.out.println("Enter correct choice");
                    break;
            }
            if (choice == 4) {
                System.exit(0);
            }
        }
    }

    /**
     * Method to store all menu items of all the available menu types.
     *
     * @param menuHashMap
     * @param res
     */
    public void storeMenuResult(HashMap<Menu, HashMap<String, Double>> menuHashMap, Results res) {
        for (Map.Entry<Menu, HashMap<String, Double>> entry : menuHashMap.entrySet()) {
            Menu menuType = entry.getKey();
            HashMap<String, Double> menuItems = entry.getValue();
            menuType.storeAllItems(res, menuItems);
        }
        res.writeResults();
        res.closeMyFile();
    }
}
