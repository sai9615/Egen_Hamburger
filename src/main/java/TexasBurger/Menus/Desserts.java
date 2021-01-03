package TexasBurger.Menus;

import Util.Results;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static Driver.App.logger;

public class Desserts implements MenuInterface{
    HashMap<String, Double> desserts;
    Scanner scn = new Scanner(System.in);

    public Desserts(HashMap<String, Double> desserts) {
        this.desserts = desserts;
    }

    @Override
    public void createItem() {
        logger.info("______________________CREATE NEW DESSERT ITEM_______________________ \n\n");
        logger.info("please enter the dish name");
        scn.nextLine();
        String dish = scn.nextLine();
        logger.info("Enter the price of the dish");
        Double cost = scn.nextDouble();
        desserts.put(dish.toLowerCase(), cost);
    }

    @Override
    public void updateItem() {
        logger.info("______________________UPDATE DESSERT ITEM_______________________ \n\n");
        logger.info("Please enter the name of dish you want to update");
        String myDish = scn.nextLine();
        logger.info("Please enter the new price");
        double price = scn.nextDouble();
        desserts.put(myDish.toLowerCase(), price);
    }

    @Override
    public void deleteItem() {
        logger.info("______________________DELETE DESSERT ITEM_______________________ \n\n");
        logger.info("Please enter the name of dish you want to delete");
        String delDish = scn.nextLine();
        desserts.remove(delDish);
    }

    @Override
    public void readItem() {
        logger.info("______________________READ ALL DESSERT ITEMS_______________________ \n\n");
        for (Map.Entry<String, Double> stringDoubleEntry : desserts.entrySet()) {
            double mrp = (double) ((Map.Entry) stringDoubleEntry).getValue();
            logger.info(((Map.Entry) stringDoubleEntry).getKey() + " price: $" + mrp);
        }
    }

    /**
     * @param res
     */
    @Override
    public void storeAllItems(Results res) {
        logger.info("______________________STORING ALL DESSERT ITEMS_______________________ \n\n");
        for (Map.Entry<String, Double> stringDoubleEntry : desserts.entrySet()) {
            Map.Entry mapElement = stringDoubleEntry;
            double mrp = (double) mapElement.getValue();
            String elem = "Desserts, " + mapElement.getKey() + ", " + mrp;
            res.storeNewResult(elem);
        }
    }
}
