/**
 * Controller Class (Servlet)
 *
 * @author Brandon Tarney
 * @since 7/22/2017
 */
package com.brandontarney.controller;

import com.brandontarney.log.MyLogger;
import com.brandontarney.model.DatabaseManager;
import com.brandontarney.model.Reservations;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    private final static Logger LOGGER
            = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private final static String LOCAL_LOG_PATH = "/Users/Tarney/logs/tarneyHW_m11_605481.log";
    private final static String SERVER_LOG_PATH = "/home/btarney1/logs/tarneyHW_m11_605481.log";

    /**
     * Constructor - Initialize logger for whole application
     */
    public Controller() {
        try {
            MyLogger.setup(LOCAL_LOG_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems with creating the log files");
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LOGGER.info("Handling a Request");

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        ServletContext servletContext = getServletContext();

        Reservations reservations = (Reservations) session.getAttribute("reservations");
        if (reservations == null) {
            reservations = new Reservations();
            session.setAttribute("reservations", reservations);
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/query.jsp");
            dispatcher.forward(request, response);
        } else {
            try {
                String date = request.getParameter("datepicker");

                Date beforeDate = DateHelper.createSqlDate(date);
                LOGGER.info("Searching for all reservations after: " + beforeDate.toString());

                DatabaseManager dbMgr = new DatabaseManager("web6.jhuep.com", "class", 3306);

                reservations = dbMgr.getReservationsAfterDate(beforeDate);
                reservations.setComparisonDate(date);
                session.setAttribute("reservations", reservations);

                LOGGER.info("Found " + reservations.getReservations().size() + " reservations");
            } catch (Exception e) {

                LOGGER.severe("HOUSTON, WE HAVE A PROBLEM: " + e.getMessage());
            }

            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/result.jsp");
            dispatcher.forward(request, response);
        }

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
