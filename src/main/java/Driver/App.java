package Driver;

import TexasBurger.Locations.Location;
import TexasBurger.Menus.Desserts;
import TexasBurger.Menus.MainMenu;
import TexasBurger.Menus.SpecialMenu;
import Util.FileProcessor;
import Util.Results;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * @author saimilind
 */
public class App {
    public static final Logger logger = LogManager.getLogger(App.class.getName());

    public static void main(String[] args) {
        System.out.println("1.CRUD Location 2.Display Menu 3.About Party Reservations 4.Contact us");
        Scanner scn = new Scanner(System.in);
        int choice = scn.nextInt();
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
                    logger.info("Do you wish to 1. create a new location 2. update a location 3. delete a location 4. Show all locations 5. Exit");
                    int option = scn.nextInt();
                    Results res = new Results(inputFile);
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
                while ((input = fpOne.readLine()) != null) {
                    menus.add(input);
                }

                HashMap<String, Double> specialMenu = new HashMap<>();
                HashMap<String, Double> mainMenu = new HashMap<>();
                HashMap<String, Double> dessert = new HashMap<>();

                //read data from file and store it into resp. HashMap

                for (String menu : menus) {
                    if (menu.contains("SpecialMenu")) {
                        String[] arr = menu.split(", ");
                        specialMenu.put(arr[1].toLowerCase(), Double.parseDouble(arr[2]));
                    } else if (menu.contains("MainMenu")) {
                        String[] arr = menu.split(", ");
                        mainMenu.put(arr[1].toLowerCase(), Double.parseDouble(arr[2]));
                    } else if (menu.contains("Desserts")) {
                        String[] arr = menu.split(", ");
                        dessert.put(arr[1].toLowerCase(), Double.parseDouble(arr[2]));
                    }
                }

                SpecialMenu specialMenus = new SpecialMenu(specialMenu);
                MainMenu mainMenus = new MainMenu(mainMenu);
                Desserts desserts = new Desserts(dessert);

                while (true) {
                    logger.info("\n Do you wish to 1. create a new menu item 2. update a menu item 3. delete a menu item 4. Show all menu items 5. Store all item and Exit");
                    int options = scn.nextInt();
                    Results res = new Results(inputFileTwo);
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
                            logger.info("______________________DISPLAY ALL MENU ITEMS_______________________ \n\n");
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
                            specialMenus.storeAllItems(res);
                            mainMenus.storeAllItems(res);
                            desserts.storeAllItems(res);
                            res.writeResults();
                            res.closeMyFile();
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
        }
    }
}
