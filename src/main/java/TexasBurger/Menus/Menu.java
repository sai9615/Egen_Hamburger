package TexasBurger.Menus;

import FileProcessors.Results;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static Driver.App.logger;

public class Menu implements MenuInterface {


    private HashMap<String, Double> menuMap;
    Scanner scn = new Scanner(System.in);
    private String menuType = "";

    public Menu(HashMap<String, Double> menuMap, String menuType) {
        this.menuMap = menuMap; this.menuType= menuType;
    }

    @Override
    public void createItem() {
        logger.info("______________________CREATE NEW "+menuType.toUpperCase()+" ITEM_______________________ \n\n");
        logger.info("please enter the dish name");
        String dish = scn.nextLine();
        logger.info("Enter the price of the dish");
        Double cost = scn.nextDouble();
        menuMap.put(dish.toLowerCase(), cost);
    }

    @Override
    public void updateItem() {
        logger.info("______________________UPDATE "+menuType.toUpperCase()+" MENU ITEM_______________________ \n\n");
        logger.info("Please enter the name of dish you want to update");
        scn.nextLine();
        String myDish = scn.nextLine();
        logger.info("Please enter the new price");
        double price = scn.nextDouble();
        if(!menuMap.containsKey(myDish)){
            logger.debug("Dish doesn't exist, please enter the correct dish name");
            updateItem();
        }
        menuMap.put(myDish.toLowerCase(), price);
    }

    @Override
    public void deleteItem() {
        logger.info("______________________DELETE "+menuType.toUpperCase()+" ITEM_______________________ \n\n");
        logger.info("Please enter the name of dish you want to delete");
        String delDish = scn.nextLine();
        if(!menuMap.containsKey(delDish)){
            logger.error("Dish doesn't exist, please enter the correct dish name");
            deleteItem();
        }
        menuMap.remove(delDish);
    }

    @Override
    public void readItem() {
        logger.info("______________________DISPLAY ALL "+menuType.toUpperCase()+" ITEMS_______________________ \n\n");
        for (Map.Entry<String, Double> stringDoubleEntry : menuMap.entrySet()) {
            double mrp = (double) ((Map.Entry) stringDoubleEntry).getValue();
            logger.info(((Map.Entry) stringDoubleEntry).getKey() + " price: $" + mrp);
        }
    }


    /**
     * @param res
     * @param currentMenu
     */
    @Override
    public void storeAllItems(Results res, HashMap<String, Double> currentMenu) {
        logger.info("______________________STORING ALL "+menuType+ " ITEMS_______________________ \n\n");
        for (Map.Entry<String, Double> stringDoubleEntry : currentMenu.entrySet()) {
            Map.Entry mapElement = stringDoubleEntry;
            double mrp = (double) mapElement.getValue();
            String elem = menuType+", " + mapElement.getKey() + ", " + mrp;
            res.storeNewResult(elem);
        }
    }
}
