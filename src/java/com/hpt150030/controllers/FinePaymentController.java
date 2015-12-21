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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
public class FinePaymentController extends HttpServlet {

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
        DatabaseConnection dbConnection = new DatabaseConnection();
        try {
            if (request.getParameter(Constants.FINE_PAYMENT_REQ_CARD_NO) == null || request.getParameter(Constants.FINE_PAYMENT_REQ_AMT) == null || request.getParameter(Constants.FINE_PAYMENT_REQ_CARD_NO).equals("") || request.getParameter(Constants.FINE_PAYMENT_REQ_AMT).equals("")) {
                throw new SQLException();
            }
            String cardNo = request.getParameter(Constants.FINE_PAYMENT_REQ_CARD_NO);
            String amount = request.getParameter(Constants.FINE_PAYMENT_REQ_AMT);

            dbConnection.openConnection();
            String sqlString = "update fines join book_loans on fines.loan_id = book_loans.loan_id set fines.paid = 1 where fines.paid = 0 and book_loans.card_no = ?;";
            dbConnection.preparedStatement = dbConnection.connect.prepareStatement(sqlString);
            dbConnection.preparedStatement.setString(1, cardNo);
            dbConnection.preparedStatement.executeUpdate();

            RequestDispatcher rd = request.getRequestDispatcher("FineTracking.jsp");
            request.setAttribute(Constants.HAS_STATUS, true);
            request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.SUCCESS);
            request.setAttribute(Constants.STATUS_HEADER, "Payment");
            request.setAttribute(Constants.STATUS_BODY, "Payment of " + amount + " was successfully recorded.");
            rd.forward(request, response);

        } catch (SQLException e) {
            dbConnection.closeConnection();
            RequestDispatcher rd = request.getRequestDispatcher("FineTracking.jsp");
            request.setAttribute(Constants.HAS_STATUS, true);
            request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.ERROR);
            request.setAttribute(Constants.STATUS_HEADER, "Exception caught");
            request.setAttribute(Constants.STATUS_BODY, "MySql exception caught. Please try again. Exception is " + e.toString());
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
