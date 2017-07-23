/**
 * Reservation Class (Model)
 *
 * @author Brandon Tarney
 * @since 7/22/2017
 */
package com.brandontarney.model;

import java.util.Date;

public class Reservation {

    public Date startDate;
    public Date endDate;
    public String location;
    public String guideFirstName;
    public String guideLastName;
    public String customerFirstName;
    public String customerLastName;

    public Reservation() {}
    
    public Reservation(Date startDate, Date endDate,
            String location, String guideFirstName,
            String guideLastName, String customerFirstName,
            String customerLastName) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.guideFirstName = guideFirstName;
        this.guideLastName = guideLastName;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
    }

}
