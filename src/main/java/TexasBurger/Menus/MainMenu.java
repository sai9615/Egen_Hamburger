package TexasBurger.Menus;

import Util.Results;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static Driver.App.logger;

public class MainMenu implements MenuInterface {

    HashMap<String, Double> mainMenu;
    Scanner scn = new Scanner(System.in);

    public MainMenu(HashMap<String, Double> mainMenu) {
        this.mainMenu = mainMenu;
    }

    @Override
    public void createItem() {
        logger.info("______________________CREATE NEW MAIN MENU ITEM_______________________ \n\n");
        logger.info("please enter the dish name");
        scn.nextLine();
        String dish = scn.nextLine();
        logger.info("Enter the price of the dish");
        Double cost = scn.nextDouble();
        mainMenu.put(dish.toLowerCase(), cost);
    }

    @Override
    public void updateItem() {
        logger.info("______________________UPDATE MAIN MENU ITEM_______________________ \n\n");
        logger.info("Please enter the name of dish you want to update");
        String myDish = scn.nextLine();
        logger.info("Please enter the new price");
        double price = scn.nextDouble();
        mainMenu.put(myDish.toLowerCase(), price);
    }

    @Override
    public void deleteItem() {
        logger.info("______________________DELETE MAIN MENU ITEM_______________________ \n\n");
        logger.info("Please enter the name of dish you want to delete");
        String delDish = scn.nextLine();
        mainMenu.remove(delDish);
    }

    @Override
    public void readItem() {
        logger.info("______________________READ ALL MAIN MENU ITEMS_______________________ \n\n");
        for (Map.Entry<String, Double> stringDoubleEntry : mainMenu.entrySet()) {
            double mrp = (double) ((Map.Entry) stringDoubleEntry).getValue();
            logger.info(((Map.Entry) stringDoubleEntry).getKey() + " price: $" + mrp);
        }
    }

    @Override
    public void storeAllItems(Results res) {
        logger.info("______________________STORING ALL MAIN MENU ITEMS_______________________ \n\n");
        for (Map.Entry<String, Double> stringDoubleEntry : mainMenu.entrySet()) {
            Map.Entry mapElement = stringDoubleEntry;
            double mrp = (double) mapElement.getValue();
            String elem = "MainMenu ," + mapElement.getKey() + ", " + mrp;
            res.storeNewResult(elem);
        }
    }
}
