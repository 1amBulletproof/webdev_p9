/**
 * Reservations Class (Model)
 *
 * @author Brandon Tarney
 * @since 7/22/2017
 */
package com.brandontarney.model;

import java.sql.Date;
import java.util.ArrayList;

public class Reservations {

    /**
     * @return the comparisonDate
     */
    public String getComparisonDate() {
        return comparisonDate;
    }

    /**
     * @param comparisonDate the comparisonDate to set
     */
    public void setComparisonDate(String comparisonDate) {
        this.comparisonDate = comparisonDate;
    }

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
    private String comparisonDate;

    public Reservations() {
        this.comparisonDate = "N/A";
    }
;

}
