/**
 * Reservations Class (Model)
 *
 * @author Brandon Tarney
 * @since 7/22/2017
 */
package com.brandontarney.model;

import java.util.ArrayList;


public class Reservations {

    /**
     * @return the reservations
     */
    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    /**
     * @param reservations the reservations to set
     */
    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }
    
    private ArrayList<Reservation> reservations;
    
    public Reservations(){};
    
}
