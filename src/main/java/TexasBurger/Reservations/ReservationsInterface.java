package TexasBurger.Reservations;

import Util.Results;

public interface ReservationsInterface {
    public void createReservation();
    public void updateReservation();
    public void deleteReservation();
    public void readReservation();
    public void storeAllReservations(Results res);
}
