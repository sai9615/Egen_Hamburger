package TexasBurger.Menus;

import FileProcessors.Results;

import java.util.HashMap;

public interface MenuInterface {
        public void createItem();
        public void updateItem();
        public void deleteItem();
        public void readItem();
        public void storeAllItems(Results res, HashMap<String, Double> currentMenu);

}
