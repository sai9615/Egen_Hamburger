package TexasBurger.Menus;

import Util.Results;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static Driver.App.logger;


public class SpecialMenu implements MenuInterface {

    HashMap<String, Double> specialMenu;
    Scanner scn = new Scanner(System.in);

    public SpecialMenu(HashMap<String, Double> specialMenu) {
        this.specialMenu = specialMenu;
    }

    @Override
    public void createItem() {
        logger.info("______________________CREATE NEW SPECIAL MENU ITEM_______________________ \n\n");
        logger.info("please enter the dish name");
        String dish = scn.nextLine();
        logger.info("Enter the price of the dish");
        Double cost = scn.nextDouble();
        specialMenu.put(dish.toLowerCase(), cost);
    }

    @Override
    public void updateItem() {
        logger.info("______________________UPDATE SPECIAL MENU ITEM_______________________ \n\n");
        logger.info("Please enter the name of dish you want to update");
        String myDish = scn.nextLine();
        logger.info("Please enter the new price");
        double price = scn.nextDouble();
        specialMenu.put(myDish.toLowerCase(), price);
    }

    @Override
    public void deleteItem() {
        logger.info("______________________DELETE SPECIAL MENU ITEM_______________________ \n\n");
        logger.info("Please enter the name of dish you want to delete");
        String delDish = scn.nextLine();
        specialMenu.remove(delDish);
    }

    @Override
    public void readItem() {
        logger.info("______________________READ ALL SPECIAL MENU ITEMS_______________________ \n\n");
        for (Map.Entry<String, Double> doubleEntry : specialMenu.entrySet()) {
            double mrp = (double) ((Map.Entry) doubleEntry).getValue();
            logger.info(((Map.Entry) doubleEntry).getKey() + " price: $" + mrp);
        }
    }

    @Override
    public void storeAllItems(Results res) {
        logger.info("______________________STORE ALL SPECIAL MENU ITEM_______________________ \n\n");
        for (Map.Entry<String, Double> doubleEntry : specialMenu.entrySet()) {
            Map.Entry mapElement = doubleEntry;
            double mrp = (double) mapElement.getValue();
            String elem = "SpecialMenu ," + mapElement.getKey() + ", " + mrp;
            res.storeNewResult(elem);
        }
    }
}
