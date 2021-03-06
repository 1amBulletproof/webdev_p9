/**
 * Reservation Class (Model)
 *
 * @author Brandon Tarney
 * @since 7/22/2017
 */
package com.brandontarney.model;

import java.sql.Date;

public class Reservation {
    private Date startDate;
    private int duration;
    private String location;
    private String guideFirstName;
    private String guideLastName;
    private String customerFirstName;
    private String customerLastName;
    
    public Reservation() {
        this.startDate = new Date(1);
        this.duration = -1;
        this.location = "unset";
        this.guideFirstName = "unset";
        this.guideLastName = "unset";
        this.customerFirstName = "unset";
        this.customerLastName = "unset";
    }

    
    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setDuration(int newDuration) {
        this.duration = newDuration;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the guideFirstName
     */
    public String getGuideFirstName() {
        return guideFirstName;
    }

    /**
     * @param guideFirstName the guideFirstName to set
     */
    public void setGuideFirstName(String guideFirstName) {
        this.guideFirstName = guideFirstName;
    }

    /**
     * @return the guideLastName
     */
    public String getGuideLastName() {
        return guideLastName;
    }

    /**
     * @param guideLastName the guideLastName to set
     */
    public void setGuideLastName(String guideLastName) {
        this.guideLastName = guideLastName;
    }

    /**
     * @return the customerFirstName
     */
    public String getCustomerFirstName() {
        return customerFirstName;
    }

    /**
     * @param customerFirstName the customerFirstName to set
     */
    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    /**
     * @return the customerLastName
     */
    public String getCustomerLastName() {
        return customerLastName;
    }

    /**
     * @param customerLastName the customerLastName to set
     */
    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }


    
    /*public Reservation(Date startDate, Date endDate,
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
*/

}
