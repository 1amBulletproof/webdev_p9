/**
 * DatabaseManager Class (Model)
 *  - Query Database
 *  - Accepts QueryResult JavaBean as input (to store the result)
 *
 * @author Brandon Tarney
 * @since 7/22/2017
 */
package com.brandontarney.model;

// Derby Example from class
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

//Class example
public class DatabaseManager {

    private final static Logger LOGGER
            = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private String host;
    private String dbName;
    private int port;
    private String dbURL;
    private Connection connection;

    public DatabaseManager(String host, String dbName, int port) throws SQLException {
        this.host = host;
        this.dbName = dbName;
        this.port = port;

        this.connectToDatabase(this.host, this.dbName, this.port);
    }

    public Reservations getReservations() {
        LOGGER.info("getReservations() begin");
        ArrayList<Reservation> allReservations = new ArrayList<>();
        try {
            ResultSet result = this.queryDB("select * from reservation");
            int idx = 1;
            while (result.next()) {
                System.out.println("Row " + idx + " FIRST_NAME = " + result.getString("First"));
                Reservation tmpReservation = new Reservation();
                tmpReservation.setCustomerFirstName(result.getString("First"));
                allReservations.add(tmpReservation);
                idx++;
            }

        } catch (SQLException exception) {
            LOGGER.severe("exception = " + exception.getMessage());
        }

        Reservations reservations = new Reservations();
        reservations.setReservations(allReservations);

        return reservations;
        //this.populateQueryResult(strQueryResult, queryResult);
    }

    public void getReservationsAfterDate(Date date) {
        /*TODO: USE A PREPARED STATEMENT
        PreparedStatement statement = this.connection.prepareStatement("SELECT * from People WHERE date < ?");
        statement.setDate(1, date);
        statement.executeUpdate
        
                //String getReservationQuery = "SELECT * FROM table WHERE StartDay >= DATE";
        //String strQueryResult = this.queryDB(getReservationQuery);
        
        select reservation.First, reservation.Last, locations.location from reservation, guides, locations where reservation.guide = guides.idguides and reservation.location = locations.idlocations;
         */
    }

    protected void populateQueryResult(String strQueryResult, QueryResult queryResult) {
        //Populate JavaBean QueryResult with the result 
    }

    private ResultSet queryDB(String sqlQueryString) throws SQLException {
        Statement statement = this.connection.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        return statement.executeQuery(sqlQueryString);
    }

    private void connectToDatabase(String host, String dbName, int port) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            LOGGER.severe("Error loading driver " + cnfe.getMessage());
        }
        this.dbURL = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

        String username = "johncolter";
        String password = "LetMeIn";
        this.connection = DriverManager.getConnection(dbURL, username, password);

        /*
                    try {
            this.connection = DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException e) {
            System.out.println("SQL exception: " + e.getMessage());
        }
         */
    }

    public static void main(String[] args) {
        try {
            DatabaseManager dbMgr = new DatabaseManager("web6.jhuep.com", "class", 3306);

            Reservations reservations = dbMgr.getReservations();
        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
    }

}

/*Class example
public class DatabaseManager {

    private String host;
    private String dbName;
    private int port;
    private String dbURL;
    private Connection connection;

    public DatabaseManager(String host, String dbName, int port) {
        //ensure that the JAR file that contains your JDBC driver is part of the CLASSPATH for your project
        // ADD the derby client and the mysql client connectors to your projects
        this.host = host;
        this.dbName = dbName;
        this.port = port;

        this.connectToDatabase(this.host, this.dbName, this.port);
    }

    public void getReservations() {
        //String getReservationQuery = "SELECT * FROM table WHERE StartDay >= DATE";
        //String strQueryResult = this.queryDB(getReservationQuery);
        

        try {
            ResultSet result = this.queryDB("select * from People");
            int idx = 1;
            while (result.next()) {
                System.out.println("Row " + idx + " FIRST_NAME = " + result.getNString("FIRST_NAME"));
                idx++;
            }
        } catch (SQLException exception) {
            System.out.println("exception = " + exception.getMessage());
        }

        //this.populateQueryResult(strQueryResult, queryResult);
    }

    protected void populateQueryResult(String strQueryResult, QueryResult queryResult) {
        //Populate JavaBean QueryResult with the result 
    }

    private ResultSet queryDB(String sqlQueryString) throws SQLException {
        Statement statement = this.connection.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return statement.executeQuery(sqlQueryString);
    }

    private void connectToDatabase(String host, String dbName, int port) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error loading driver " + cnfe.getMessage());
        }

        this.dbURL = "jdbc:derby://" + host + ":" + port + "/" + dbName;

        String username = "foo";
        String password = "foo"; // or was it foo foo 

        try {
            this.connection = DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException e) {
            System.out.println("SQL exception: " + e.getMessage());
        }

    }

    public static void main(String[] args) {
        DatabaseManager dbMgr = new DatabaseManager("localhost", "MyFirstDatabase", 1527);
        dbMgr.connectToDatabase("localhost", "MyFirstDatabase", 1527);
        dbMgr.getReservations();
    }

}
 */
