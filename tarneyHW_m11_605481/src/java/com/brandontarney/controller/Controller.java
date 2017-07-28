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
    
    private final static String LOG_PATH = "/Users/Tarney/tmp/tarneyHW_m11_605481.log";

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

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        ServletContext servletContext = getServletContext();

        Reservations reservations = (Reservations) session.getAttribute("reservations");
        if (reservations == null) {
            try {
                MyLogger.setup(LOG_PATH);
                reservations = new Reservations();
                session.setAttribute("reservations", reservations);
                RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/query.jsp");
                dispatcher.forward(request, response);

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Problems with creating the log files");
            }
        }

        LOGGER.info("processRequest start");

        BufferedWriter bw = null;
        bw = new BufferedWriter(new FileWriter("/Users/Tarney/tmp/test_log"));

        try {
            DatabaseManager dbMgr = new DatabaseManager("web6.jhuep.com", "class", 3306);

            reservations = dbMgr.getReservations();
            session.setAttribute("reservations", reservations);
            bw.write("size of reservations: " + reservations.getReservations().size());
            bw.close();
            System.out.println("Size of reservations: " + reservations.getReservations().size());
        } catch (Exception e) {
            bw.write("exception = " + e.getMessage());
            bw.close();
            System.err.println("" + e.getMessage());
        }

        //TODO:
        //  1. Decipher Date from request parameters
        //  2. Tell model to update itself (from database) 
        //  3. Get model results of DB Query (to determine if valid or not)
        //  4. Update Model with result of query
        //  5. Serve up a JSP (which itself will render the HTML table based on the model 
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/result.jsp");
        dispatcher.forward(request, response);

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
