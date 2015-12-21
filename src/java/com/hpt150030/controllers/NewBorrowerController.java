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
public class NewBorrowerController extends HttpServlet {

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

        if (request.getParameter(Constants.NEW_BORROWER_REQ_FNAME) == null && request.getParameter(Constants.NEW_BORROWER_REQ_LNAME) == null && request.getParameter(Constants.NEW_BORROWER_REQ_EMAIL) == null && request.getParameter(Constants.NEW_BORROWER_REQ_ADDRESS) == null && request.getParameter(Constants.NEW_BORROWER_REQ_CITY) == null && request.getParameter(Constants.NEW_BORROWER_REQ_STATE) == null && request.getParameter(Constants.NEW_BORROWER_REQ_PHONE) == null) {
            //first time
            RequestDispatcher rd = request.getRequestDispatcher("BorrowerManagement.jsp");
            request.setAttribute(Constants.HAS_STATUS, false);
            rd.forward(request, response);
        } else if (request.getParameter(Constants.NEW_BORROWER_REQ_FNAME).equalsIgnoreCase("") && request.getParameter(Constants.NEW_BORROWER_REQ_LNAME).equalsIgnoreCase("") && request.getParameter(Constants.NEW_BORROWER_REQ_EMAIL).equalsIgnoreCase("") && request.getParameter(Constants.NEW_BORROWER_REQ_ADDRESS).equalsIgnoreCase("") && request.getParameter(Constants.NEW_BORROWER_REQ_CITY).equalsIgnoreCase("") && request.getParameter(Constants.NEW_BORROWER_REQ_STATE).equalsIgnoreCase("") && request.getParameter(Constants.NEW_BORROWER_REQ_PHONE).equalsIgnoreCase("")) {
            //nothing entered
            RequestDispatcher rd = request.getRequestDispatcher("BorrowerManagement.jsp");
            request.setAttribute(Constants.HAS_STATUS, true);
            request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.WARNING);
            request.setAttribute(Constants.STATUS_HEADER, "Empty search");
            request.setAttribute(Constants.STATUS_BODY, "Please enter something to search");
            rd.forward(request, response);
        } else {
            if (request.getParameter(Constants.NEW_BORROWER_REQ_FNAME).equalsIgnoreCase("") || request.getParameter(Constants.NEW_BORROWER_REQ_LNAME).equalsIgnoreCase("") || request.getParameter(Constants.NEW_BORROWER_REQ_EMAIL).equalsIgnoreCase("") || request.getParameter(Constants.NEW_BORROWER_REQ_ADDRESS).equalsIgnoreCase("") || request.getParameter(Constants.NEW_BORROWER_REQ_CITY).equalsIgnoreCase("") || request.getParameter(Constants.NEW_BORROWER_REQ_STATE).equalsIgnoreCase("") || request.getParameter(Constants.NEW_BORROWER_REQ_PHONE).equalsIgnoreCase("")) {
                RequestDispatcher rd = request.getRequestDispatcher("BorrowerManagement.jsp");
                request.setAttribute(Constants.HAS_STATUS, true);
                request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.ERROR);
                request.setAttribute(Constants.STATUS_HEADER, "Error occured");
                request.setAttribute(Constants.STATUS_BODY, "One of the fields have been left blank. Please fill it and try again.");
                rd.forward(request, response);
                return;
            }

            String fname = request.getParameter(Constants.NEW_BORROWER_REQ_FNAME);
            String lname = request.getParameter(Constants.NEW_BORROWER_REQ_LNAME);
            String email = request.getParameter(Constants.NEW_BORROWER_REQ_EMAIL);
            String address = request.getParameter(Constants.NEW_BORROWER_REQ_ADDRESS);
            String city = request.getParameter(Constants.NEW_BORROWER_REQ_CITY);
            String state = request.getParameter(Constants.NEW_BORROWER_REQ_STATE);
            String phone = request.getParameter(Constants.NEW_BORROWER_REQ_PHONE);

            StringBuffer sqlString = new StringBuffer();
            sqlString.append("select COUNT(*) from borrower where fname =  ? and  lname =  ? and  address =  ?;");

            DatabaseConnection dbConnection = new DatabaseConnection();
            try {
                dbConnection.openConnection();
                dbConnection.preparedStatement = dbConnection.connect.prepareStatement(sqlString.toString());
                dbConnection.preparedStatement.setString(1, fname);
                dbConnection.preparedStatement.setString(2, lname);
                dbConnection.preparedStatement.setString(3, address);
                dbConnection.resultSet = dbConnection.preparedStatement.executeQuery();
                dbConnection.resultSet.next();
                if (dbConnection.resultSet.getInt(1) > 0) {
                    //borrower record already exists
                    RequestDispatcher rd = request.getRequestDispatcher("BorrowerManagement.jsp");
                    request.setAttribute(Constants.HAS_STATUS, true);
                    request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.ERROR);
                    request.setAttribute(Constants.STATUS_HEADER, "Error");
                    request.setAttribute(Constants.STATUS_BODY, "Borrower already exists. Please check the details again.");
                    rd.forward(request, response);
                    return;
                }

                sqlString = new StringBuffer();
                sqlString.append("insert into borrower (fname,lname,email,address,city,state,phone) values (?,?,?,?,?,?,?);");
                dbConnection.preparedStatement = dbConnection.connect.prepareStatement(sqlString.toString());
                dbConnection.preparedStatement.setString(1, fname);
                dbConnection.preparedStatement.setString(2, lname);
                dbConnection.preparedStatement.setString(3, email);
                dbConnection.preparedStatement.setString(4, address);
                dbConnection.preparedStatement.setString(5, city);
                dbConnection.preparedStatement.setString(6, state);
                dbConnection.preparedStatement.setString(7, phone);
                dbConnection.preparedStatement.executeUpdate();

                
                sqlString = new StringBuffer();
                sqlString.append("select card_no from borrower where fname = ? and lname = ? and address = ?");
                dbConnection.preparedStatement = dbConnection.connect.prepareStatement(sqlString.toString());
                dbConnection.preparedStatement.setString(1, fname);
                dbConnection.preparedStatement.setString(2, lname);
                dbConnection.preparedStatement.setString(3, address);
                dbConnection.resultSet = dbConnection.preparedStatement.executeQuery();
                dbConnection.resultSet.next();
                int cardNo = dbConnection.resultSet.getInt(1);
                
                dbConnection.closeConnection();
                
                RequestDispatcher rd = request.getRequestDispatcher("BorrowerManagement.jsp");
                request.setAttribute(Constants.HAS_STATUS, true);
                request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.SUCCESS);
                request.setAttribute(Constants.STATUS_HEADER, "Success");
                request.setAttribute(Constants.STATUS_BODY, "New borrower has been successfully added to the database. The card number assigned is: "+ cardNo);
                rd.forward(request, response);

            } catch (SQLException e) {
                dbConnection.closeConnection();
                RequestDispatcher rd = request.getRequestDispatcher("BorrowerManagement.jsp");
                request.setAttribute(Constants.HAS_STATUS, true);
                request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.ERROR);
                request.setAttribute(Constants.STATUS_HEADER, "SQL Exception caught");
                request.setAttribute(Constants.STATUS_BODY, "Sql exception caught. Try again. Exception is: " + e.toString());
                rd.forward(request, response);
            } catch (Exception e) {
                dbConnection.closeConnection();
                RequestDispatcher rd = request.getRequestDispatcher("BorrowerManagement.jsp");
                request.setAttribute(Constants.HAS_STATUS, true);
                request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.ERROR);
                request.setAttribute(Constants.STATUS_HEADER, "Exception caught");
                request.setAttribute(Constants.STATUS_BODY, "Exception caught. PLease try again. Exception is " + e.toString());
                rd.forward(request, response);
            } finally {
                dbConnection.closeConnection();
            }
        }
    }// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

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
