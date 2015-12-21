/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hpt150030.controllers;

import com.hpt150030.beans.SearchBookResultBean;
import com.hpt150030.utilities.Constants;
import com.hpt150030.utilities.DatabaseConnection;
import com.hpt150030.utilities.STATUS_TYPE;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
public class SearchBookController extends HttpServlet {

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

        if ((request.getParameter(Constants.BOOK_SEARCH_CONTROLLER_REQ_BOOK_ID) == null && request.getParameter(Constants.BOOK_SEARCH_CONTROLLER_REQ_TITLE) == null && request.getParameter(Constants.BOOK_SEARCH_CONTROLLER_REQ_AUTHORS) == null)) {
            //First time call
            RequestDispatcher rd = request.getRequestDispatcher("SearchBook.jsp");
            request.setAttribute(Constants.HAS_STATUS, false);
            rd.forward(request, response);
        } else if (request.getParameter(Constants.BOOK_SEARCH_CONTROLLER_REQ_BOOK_ID).equals("") && request.getParameter(Constants.BOOK_SEARCH_CONTROLLER_REQ_TITLE).equals("") && request.getParameter(Constants.BOOK_SEARCH_CONTROLLER_REQ_AUTHORS).equals("")) {
            RequestDispatcher rd = request.getRequestDispatcher("SearchBook.jsp");
            request.setAttribute(Constants.HAS_STATUS, true);
            request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.WARNING);
            request.setAttribute(Constants.STATUS_HEADER, "Empty search");
            request.setAttribute(Constants.STATUS_BODY, "Please enter into the boxes to search for books.");
            rd.forward(request, response);
        } else {
            DatabaseConnection dbConnection = new DatabaseConnection();
            try {
                String bookId = request.getParameter(Constants.BOOK_SEARCH_CONTROLLER_REQ_BOOK_ID);
                String title = request.getParameter(Constants.BOOK_SEARCH_CONTROLLER_REQ_TITLE);
                String authors = request.getParameter(Constants.BOOK_SEARCH_CONTROLLER_REQ_AUTHORS);

                StringBuilder sqlString = new StringBuilder();
                sqlString.append("SELECT b.book_id,b.title,ba.author_name,lb.branch_id,bc.no_of_copies,ifnull((bc.no_of_copies - t.borrowed),bc.no_of_copies) as available "
                        + "from ((((book as b join book_authors as ba on b.book_id = ba.book_id) "
                        + "join book_copies as bc on b.book_id = bc.book_id) "
                        + "join library_branch as lb on lb.branch_id = bc.branch_id) "
                        + "left join (select bl.book_id as book_id, bl.branch_id as branch_id, count(*) as borrowed from book_loans as bl where bl.date_in is null group by bl.book_id,bl.branch_id ) as T on T.book_id = b.book_id and T.branch_id = bc.branch_id) "
                        + "where");
                if (!bookId.equals("")) {
                    sqlString.append(" b.book_id = ?");
                }
                if (!title.equals("")) {
                    if((!bookId.equals("")))
                    {
                        sqlString.append(" and");
                    }
                    sqlString.append(" b.title like ?");
                }
                if (!authors.equals("")) {
                    if (!bookId.equals("") || (!title.equals(""))) {
                        sqlString.append(" and");
                    }
                    String[] authorNames = authors.split(",");
                    sqlString.append(" ba.author_name like ?");
                    for (int i = 0; i < authorNames.length - 1; i++) {
                        sqlString.append(" or ba.author_name like ?");
                    }
                }
                sqlString.append(";");
                
                dbConnection.openConnection();
                dbConnection.preparedStatement = dbConnection.connect.prepareStatement(sqlString.toString());
                int count = 1;
                if (!bookId.equals("")) {
                    dbConnection.preparedStatement.setString(count++, bookId);
                }
                if (!title.equals("")) {
                    dbConnection.preparedStatement.setString(count++, "%"+title+"%");
                }
                if (!authors.equals("")) {
                    String[] authorNames = authors.split(",");
                    dbConnection.preparedStatement.setString(count++, "%"+authorNames[0].trim()+"%");
                    for (int i = 0; i < authorNames.length - 1; i++) {
                        dbConnection.preparedStatement.setString(count++, "%"+authorNames[1 + i].trim()+"%");
                    }
                }
                
                System.out.println(dbConnection.preparedStatement.toString());
                
                dbConnection.resultSet = dbConnection.preparedStatement.executeQuery();
                ArrayList<SearchBookResultBean> list = new ArrayList<>();
                while (dbConnection.resultSet.next()) {
                    list.add(new SearchBookResultBean(dbConnection.resultSet.getString("book_id"), dbConnection.resultSet.getString("title"), dbConnection.resultSet.getString("author_name"), dbConnection.resultSet.getString("branch_id"), dbConnection.resultSet.getString("no_of_copies"), dbConnection.resultSet.getString("available")));
                }
                if(list.size()>2000)
                    list.subList(2000, list.size()).clear();
                
                dbConnection.closeConnection();
                
                RequestDispatcher rd = request.getRequestDispatcher("SearchBook.jsp");
                request.setAttribute(Constants.HAS_STATUS, true);
                request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.SUCCESS);
                request.setAttribute(Constants.STATUS_HEADER, "Searching success");
                if(list.size()<2000)
                {
                    request.setAttribute(Constants.STATUS_BODY, "Search success.");
                } else {
                    request.setAttribute(Constants.STATUS_BODY, "Search success. But search results limited to 5000 rows. Please change search constraints for better results.");
                }
                
                request.setAttribute(Constants.BOOK_SEARCH_JSP_REQ, list);
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                RequestDispatcher rd = request.getRequestDispatcher("SearchBook.jsp");
                request.setAttribute(Constants.HAS_STATUS, true);
                request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.ERROR);
                request.setAttribute(Constants.STATUS_HEADER, "SQL Exception");
                request.setAttribute(Constants.STATUS_BODY, "SQL Exception caught. \n " + e.toString());
                rd.forward(request, response);
                dbConnection.closeConnection();
            }
            finally{
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
