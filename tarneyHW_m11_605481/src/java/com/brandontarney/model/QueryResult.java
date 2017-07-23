/**
 * QueryResult Class (JavaBean)
 *
 * @author Brandon Tarney
 * @since 7/22/2017
 */
package com.brandontarney.model;

import java.util.ArrayList;

public class QueryResult {
    
    private ArrayList<Reservation> reservations;
    
    //TODO:
    //  1. Store DB query results here
    
    public QueryResult() {
        reservations = new ArrayList<Reservation>();
    }
    
    public ArrayList<Reservation> getReservations() {
        return this.reservations;
    }
    
    public void setReservations(ArrayList<Reservation> newReservations) {
        this.reservations = newReservations;
    }
    
    
}
