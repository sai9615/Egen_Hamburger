package TexasBurger.Menus;

import Util.Results;

public interface MenuInterface {
    public void createItem();
    public void updateItem();
    public void deleteItem();
    public void readItem();
    public void storeAllItems(Results res);
}
