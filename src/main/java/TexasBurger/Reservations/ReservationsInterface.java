package TexasBurger.Reservations;

import FileProcessors.Results;

public interface ReservationsInterface {
    public void createReservation();
    public void updateReservation();
    public void deleteReservation();
    public void readReservation();
    public void storeAllReservations(Results res);
}
