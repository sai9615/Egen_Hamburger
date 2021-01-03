package TexasBurger.Reservations;

import Util.Results;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static Driver.App.logger;

public class Reservations implements ReservationsInterface{
    HashMap<String, Date> reservations = new HashMap<>();
    Set<Date> dates = new HashSet<>();
    Scanner scn = new Scanner(System.in);

    public Reservations(HashMap<String, Date> reservations, Set<Date> dates) {
        this.reservations = reservations;
        this.dates = dates;
    }

    @Override
    public void createReservation() {
        logger.info("\n______________________CREATE NEW RESERVATION_______________________ \n\n");
        logger.info("Enter the name of the person");
        String name = scn.nextLine();
        logger.info("Enter the date for reservation in dd/mm/yyyy format");
        String dt = scn.nextLine();
        int[] dateArray = Arrays.stream(dt.split("/")).mapToInt(Integer::parseInt).toArray();
        Date date = new Date(dateArray[2]-1900, dateArray[1],dateArray[0]);
        Date currentDate = new Date();
        if(dates.contains(date)){
            logger.debug("Reservation exist, please specify another date");
            createReservation();
        }else if(date.before(currentDate)){
            logger.debug("Can't reserve for today or a previous date, please specify another date \n");
            createReservation();
        }
        reservations.put(name.toLowerCase(), date);
        dates.add(date);
        logger.info("Added new reservation");
    }

    @Override
    public void updateReservation() {
        logger.info("______________________UPDATE RESERVATION_______________________ \n\n");
        logger.info("Enter the name of the person");
        String name = scn.nextLine();
        logger.info("Enter the new date for reservation in dd/mm/yyyy format");
        String dt = scn.nextLine();
        int[] dateArray = Arrays.stream(dt.split("/")).mapToInt(Integer::parseInt).toArray();
        Date date = new Date(dateArray[2]-1900, dateArray[1],dateArray[0]);
        Date currentDate = new Date();
        if(dates.contains(date)){
            logger.debug("Reservation exist, please specify another date");
            updateReservation();
        }
        else if(date.before(currentDate)){
            logger.debug("Can't reserve for today or a previous date, please specify another date \n");
            createReservation();
        }
        reservations.put(name.toLowerCase(), date);
        dates.add(date);
        logger.info("Updated reservation for "+name);
    }

    @Override
    public void deleteReservation() {
        logger.info("_____________________DELETE RESERVATION_______________________ \n\n");
        logger.info("Enter the name of the person");
        String name = scn.nextLine();
        logger.info("Enter the date for reservation in dd/mm/yyyy format");
        String dt = scn.nextLine();
        int[] dateArray = Arrays.stream(dt.split("/")).mapToInt(Integer::parseInt).toArray();
        Date date = new Date(dateArray[2]-1900, dateArray[1],dateArray[0]);
        if(!dates.contains(date)){
            logger.debug("Reservation doesn't exist, please specify another date");
            deleteReservation();
        }
        reservations.remove(name.toLowerCase());
        dates.remove(date);
        logger.info("Deleted reservation for "+name);
    }

    @Override
    public void readReservation() {
        logger.info("______________________READ ALL SPECIAL MENU ITEMS_______________________ \n\n");
        String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        for (Map.Entry<String, Date> doubleEntry : reservations.entrySet()) {
            Date date = (Date) ((Map.Entry) doubleEntry).getValue();
            String day = df.format(date);
            logger.info(((Map.Entry) doubleEntry).getKey() + " Date: " + day);
        }
    }

    /**
     * @param res
     */
    @Override
    public void storeAllReservations(Results res) {
        String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        logger.info("______________________STORING ALL RESERVATIONS_______________________ \n\n");
        for (Map.Entry<String, Date> doubleEntry : reservations.entrySet()) {
            Map.Entry mapElement = doubleEntry;
            Date date = (Date) mapElement.getValue();
            String day = df.format(date);
            String elem =  mapElement.getKey() + ", " + day;
            res.storeNewResult(elem);
        }
    }
}
