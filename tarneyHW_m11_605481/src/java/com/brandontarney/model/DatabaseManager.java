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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Class definition
 */
public class DatabaseManager {

    private final static Logger LOGGER
            = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private String host;
    private String dbName;
    private int port;
    private String dbURL;
    private Connection connection;

    /**
     * DatabaseManager Constructor - initiatlize connection
     *
     * @param host
     * @param dbName
     * @param port
     * @throws SQLException
     */
    public DatabaseManager(String host, String dbName, int port) throws SQLException {
        LOGGER.info("DB handler constructed");
        this.host = host;
        this.dbName = dbName;
        this.port = port;

        this.connectToDatabase(this.host, this.dbName, this.port);
    }

    /**
     * getReservations - get all reservations tracked in the table
     *
     * @return Reservations (all of them)
     */
    public Reservations getReservations() {
        LOGGER.info("getting ALL reservations from the DB");
        Reservations reservations = new Reservations();
        String queryStr = "select * from reservation "
                + "inner join locations on "
                + "reservation.location = locations.idlocations "
                + "inner join guides on "
                + "reservation.guide = guides.idguides";
        try {
            ResultSet result = this.queryDB(queryStr);
            reservations = parseResultSet(result);
        } catch (SQLException exception) {
            LOGGER.severe("exception = " + exception.getMessage());
        }
        return reservations;
    }

    /**
     * getReservationsAfterDate - get all reservations who's date is after input
     * date
     *
     * @param date input date of comparison
     * @return Reservations (all of them)
     */
    public Reservations getReservationsAfterDate(Date date) {
        LOGGER.info("getting reservations after " + date.toString() + " from the DB");
        Reservations reservations = new Reservations();
        String queryStr = "SELECT * from reservation inner join locations on reservation.location = locations.idlocations inner join guides on reservation.guide = guides.idguides where reservation.StartDay > ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(queryStr);
            statement.setDate(1, date);
            ResultSet result = statement.executeQuery();
            reservations = parseResultSet(result);
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
        return reservations;
    }

    private Reservations parseResultSet(ResultSet result) {
        ArrayList<Reservation> allReservations = new ArrayList<>();
        try {
            int idx = 1;
            while (result.next()) {
                Reservation tmpReservation = this.populateReservation(result);
                allReservations.add(tmpReservation);
                idx++;
            }
        } catch (SQLException exception) {
            LOGGER.severe("exception = " + exception.getMessage());
        }
        Reservations reservations = new Reservations();
        reservations.setReservations(allReservations);

        return reservations;
    }

    private Reservation populateReservation(ResultSet dbQueryResult) {
        Reservation newReservation = new Reservation();
        try {
            //LOGGER.info("Row " + idx + " FIRST_NAME = " + result.getString("First"));
            newReservation.setStartDate(dbQueryResult.getDate("StartDay"));
            newReservation.setDuration(dbQueryResult.getInt("NumberOfDays"));
            newReservation.setLocation(dbQueryResult.getString(9));
            newReservation.setCustomerFirstName(dbQueryResult.getString(2));
            newReservation.setCustomerLastName(dbQueryResult.getString(3));
            newReservation.setGuideFirstName(dbQueryResult.getString(11));
            newReservation.setGuideLastName(dbQueryResult.getString(12));
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
        return newReservation;
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
        LOGGER.info("Connected to DB");

    }

    public static void main(String[] args) {
        try {
            DatabaseManager dbMgr = new DatabaseManager("web6.jhuep.com", "class", 3306);
            Reservations reservations1 = dbMgr.getReservations();
            System.out.println("Rows returned (all reservations): " + reservations1.getReservations().size());
            Reservations reservations2 = dbMgr.getReservationsAfterDate(Date.valueOf("1988-11-05"));
            System.out.println("Rows returned (reservations after my birthday): " + reservations2.getReservations().size());
            Reservations reservations3 = dbMgr.getReservationsAfterDate(Date.valueOf("2016-01-01"));
            System.out.println("Rows returned (reservations after 2016): " + reservations3.getReservations().size());
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
