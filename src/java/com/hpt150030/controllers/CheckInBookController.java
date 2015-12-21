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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
public class CheckInBookController extends HttpServlet {

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

        if (request.getParameter(Constants.CHECK_IN_REQ_DATE) == null) {
            RequestDispatcher rd = request.getRequestDispatcher("CheckInBook.jsp");
            request.setAttribute(Constants.HAS_STATUS, true);
            request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.WARNING);
            request.setAttribute(Constants.STATUS_HEADER, "Enter date");
            request.setAttribute(Constants.STATUS_BODY, "Please enter the check-in date in the format yyyy-MM-dd to complete the check in");
            rd.forward(request, response);
        } else {
            //insert the check in date into the book_loans table
            DatabaseConnection dbConnection = new DatabaseConnection();
            try {
                String sqlString = new String();
                String date = request.getParameter(Constants.CHECK_IN_REQ_DATE);
                String bookId = request.getParameter(Constants.CHECK_IN_REQ_BOOK_ID);
                String branchId = request.getParameter(Constants.CHECK_IN_REQ_BRANCH_ID);
                String cardNo = request.getParameter(Constants.CHECK_IN_REQ_CARD_NO);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.parse(date);

                dbConnection.openConnection();

                sqlString = "update book_loans set date_in = ? where book_id = ? and card_no = ? and branch_id = ?";
                dbConnection.preparedStatement = dbConnection.connect.prepareStatement(sqlString);
                dbConnection.preparedStatement.setString(1, date);
                dbConnection.preparedStatement.setString(2, bookId);
                dbConnection.preparedStatement.setString(3, cardNo);
                dbConnection.preparedStatement.setString(4, branchId);
                dbConnection.preparedStatement.executeUpdate();

                dbConnection.closeConnection();

                RequestDispatcher rd = request.getRequestDispatcher("BookLoan.jsp");
                request.setAttribute(Constants.BOOK_LOAN_REQ_TYPE, Constants.BOOK_LOAN_REQ_CHECKIN_TYPE);
                request.setAttribute(Constants.HAS_STATUS, true);
                request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.SUCCESS);
                request.setAttribute(Constants.STATUS_HEADER, "Success");
                request.setAttribute(Constants.STATUS_BODY, "Book successfully checked in");
                rd.forward(request, response);

            } catch (ParseException e) {
                RequestDispatcher rd = request.getRequestDispatcher("CheckInBook.jsp");
                request.setAttribute(Constants.HAS_STATUS, true);
                request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.ERROR);
                request.setAttribute(Constants.STATUS_HEADER, "Date incorrect");
                request.setAttribute(Constants.STATUS_BODY, "Please enter the date in correct yyyy-MM-dd format");
                rd.forward(request, response);
            } catch (SQLException e) {
                RequestDispatcher rd = request.getRequestDispatcher("CheckInBook.jsp");
                request.setAttribute(Constants.HAS_STATUS, true);
                request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.ERROR);
                request.setAttribute(Constants.STATUS_HEADER, "SQL Exception caught");
                request.setAttribute(Constants.STATUS_BODY, "Sql exception caught. Try again. Exception is: " + e.toString());
                rd.forward(request, response);
            } catch (ClassNotFoundException e) {
                RequestDispatcher rd = request.getRequestDispatcher("CheckInBook.jsp");
                request.setAttribute(Constants.HAS_STATUS, true);
                request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.ERROR);
                request.setAttribute(Constants.STATUS_HEADER, "Class not found Exception caught");
                request.setAttribute(Constants.STATUS_BODY, "Class not found exception caught. Try again. Exception is: " + e.toString());
                rd.forward(request, response);
            } finally {
                dbConnection.closeConnection();
            }

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
