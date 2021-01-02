package TexasBurger.Locations;

import Util.Results;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static Driver.App.logger;

public class Location implements LocationInterface {

    private ArrayList<String> locations;
    Scanner scn = new Scanner(System.in);

    public Location(ArrayList<String> locations) {
        this.locations = locations;
    }

    @Override
    public void createLocation() {
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
    }

    @Override
    public void updateLocation() {
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
    }

    @Override
    public void readLocation() {
        logger.info("______________________DISPLAYING LOCATION_______________________ \n\n");
        for (String loc: locations){
            System.out.println(loc +"\n");
        }
    }

    @Override
    public void deleteLocation() {
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
    }

    @Override
    public void storeLocations(Results res){
        logger.info("______________________STORE ALL LOCATIONS_______________________ \n\n");
        for (String loc: locations){
            res.storeNewResult(loc);
        }
        res.writeResults();
        res.closeMyFile();
    }
}
