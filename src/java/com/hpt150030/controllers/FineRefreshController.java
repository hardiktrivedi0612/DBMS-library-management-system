/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hpt150030.controllers;

import com.hpt150030.utilities.Constants;
import com.hpt150030.utilities.DatabaseConnection;
import com.hpt150030.utilities.STATUS_TYPE;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
@WebServlet(name = "FineRefreshController", urlPatterns = {"/FineRefreshController"})
public class FineRefreshController extends HttpServlet {

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
        DatabaseConnection dbConnection = new DatabaseConnection();
        try {
            response.setContentType("text/html;charset=UTF-8");

            dbConnection.openConnection();

            StringBuilder sqlString = new StringBuilder();
            sqlString.append("insert into fines (loan_id, fine_amt) "
                    + "select T.loan_id,T.fine*0.25 from (select loan_id, if(date_in IS NULL,datediff(curdate(),due_date),IF(date_in>due_date,datediff(date_in,due_date),0)) as fine from book_loans having fine>0) as T "
                    + "where T.loan_id NOT IN (select f.loan_id from fines as f);");
            dbConnection.preparedStatement = dbConnection.connect.prepareStatement(sqlString.toString());
            dbConnection.preparedStatement.executeUpdate();

            sqlString = new StringBuilder();
            sqlString.append("update fines join (select loan_id, if(date_in IS NULL,datediff(curdate(),due_date),IF(date_in>due_date,datediff(date_in,due_date),0)) as fine from book_loans having fine>0) as T on fines.loan_id = T.loan_id set fines.fine_amt = T.fine*0.25 where fines.paid = 0; ");
            dbConnection.preparedStatement = dbConnection.connect.prepareStatement(sqlString.toString());
            dbConnection.preparedStatement.executeUpdate();

            dbConnection.closeConnection();

            RequestDispatcher rd = request.getRequestDispatcher("FineTracking.jsp");
            request.setAttribute(Constants.HAS_STATUS, true);
            request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.SUCCESS);
            request.setAttribute(Constants.STATUS_HEADER, "Refresh ");
            request.setAttribute(Constants.STATUS_BODY, "Refresh Successful");
            rd.forward(request, response);

        } catch (SQLException ex) {
            dbConnection.closeConnection();
            RequestDispatcher rd = request.getRequestDispatcher("FineTracking.jsp");
            request.setAttribute(Constants.HAS_STATUS, true);
            request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.ERROR);
            request.setAttribute(Constants.STATUS_HEADER, "Exception caught");
            request.setAttribute(Constants.STATUS_BODY, "MySql exception caught. Please try again. Exception is " + ex.toString());
            rd.forward(request, response);

        } catch (ClassNotFoundException ex) {
            dbConnection.closeConnection();
            RequestDispatcher rd = request.getRequestDispatcher("FineTracking.jsp");
            request.setAttribute(Constants.HAS_STATUS, true);
            request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.ERROR);
            request.setAttribute(Constants.STATUS_HEADER, "Exception caught");
            request.setAttribute(Constants.STATUS_BODY, "ClassNotFound exception caught. Please try again. Exception is " + ex.toString());
            rd.forward(request, response);
        } finally {
            dbConnection.closeConnection();
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
