/**
 * DatabaseManager Class (Model)
 *  - Query Database
 *  - Accepts QueryResult JavaBean as input (to store the result)
 *
 * @author Brandon Tarney
 * @since 7/22/2017
 */
package com.brandontarney.model;


public class DatabaseManager {


    public DatabaseManager() {
        //TODO: initialize DB connection object?
    }

    public void getReservations(QueryResult queryResult) {
        String getReservationQuery = "SELECT * FROM table WHERE StartDay >= DATE";
        String strQueryResult = this.queryDB(getReservationQuery);
        
        this.populateQueryResult(strQueryResult, queryResult);
    }
    
    protected void populateQueryResult(String strQueryResult, QueryResult queryResult) {
    //Populate JavaBean QueryResult with the result 
    }

    private String queryDB(String sqlQueryString) {
        //Query DB
        return "";
    }

}
