/**
 * DateParser Class (Controller)
 *  - Create Various date formats from strings
 *
 * @author Brandon Tarney
 * @since 7/22/2017
 */
package com.brandontarney.controller;

import java.sql.Date;

public class DateHelper {

    public static Date createSqlDate(String oldFormatDate) {
        String year, month, day, newFormatDate;
        String dash = "-";
        String[] allDates = oldFormatDate.split("/");
        year = allDates[2];
        month = allDates[0];
        day = allDates[1];
        newFormatDate = year + dash + month + dash + day;
        Date returnDate = Date.valueOf(newFormatDate);
        return returnDate;
    }

}
