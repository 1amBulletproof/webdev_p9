/**
 * MockDatabaseManager Class
 * - "Pretend" to be the DatabaseManager
 * - Accepts QueryResult javabean as input (to store the result)
 * - populate QueryResult with "pretend" DB data  
 * 
 * @author Brandon Tarney
 * @since 7/22/2017
 */

package com.brandontarney.model;

public class MockDatabaseManager extends DatabaseManager {

    public void getReservations(QueryResult queryResult) {

        String mockQueryResult = "NEVER HAPPENED";
        this.populateQueryResult(mockQueryResult, queryResult);
    }

}
