<%-- 
    Document   : BookLoan
    Created on : Oct 13, 2015, 12:06:04 PM
    Author     : Hardik
--%>

<%@page import="com.hpt150030.utilities.STATUS_TYPE"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hpt150030.beans.LoanBookListBean"%>
<%@page import="com.hpt150030.controllers.BookLoanController"%>
<%@page import="com.hpt150030.utilities.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Loan</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </head>
    <body>

        <%!
            boolean hasStatus = false;
        %>
        <%
            if (request.getAttribute(Constants.HAS_STATUS) != null) {
                if ((Boolean) request.getAttribute(Constants.HAS_STATUS) == true) {
                    hasStatus = true;
                }
            } else {
                hasStatus = false;
            }
        %>
        
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.html">Library Management System</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="index.html">Home</a></li>
                        <li><a href="SearchBookController">Search</a></li>
                        <li class="active"><a href="#">Loan</a></li>
                        <li><a href="FineTrackingController">Fines</a></li>
                        <li><a href="NewBorrowerController">Borrowers</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        
        <br><br><br><br>

        <div class="container">
            
            <ul class="nav nav-tabs">
                <li <% if ((request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE) == null) || request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE).equals(Constants.BOOK_LOAN_REQ_CHECKOUT_TYPE)) {
                        out.println("class=\"active\"");
                    }%>><a data-toggle="tab" href="#checkOut">Check out</a></li>
                <li <% if (request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE) != null && request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE).equals(Constants.BOOK_LOAN_REQ_CHECKIN_TYPE)) {
                        out.println("class=\"active\"");
                    }%>><a data-toggle="tab" href="#checkIn">Check in</a></li>
            </ul>

            <br>

            <div class="tab-content">

                <div id="checkOut" class="tab-pane fade in <% if ((request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE) == null && request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE) == null) || request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE).equals(Constants.BOOK_LOAN_REQ_CHECKOUT_TYPE)) {
                        out.println("active");
                    }%>">
                    <div class="panel panel-default">
                        <div class="panel-heading">Check out book</div>
                        <div class="panel-body">
                            <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/BookLoanController" method="POST">
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="<%=Constants.BOOK_LOAN_REQ_BOOK_ID%>">Book ID:</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="<%= Constants.BOOK_LOAN_REQ_BOOK_ID%>" 
                                               placeholder="Enter Book id" 
                                               value="<% if (request.getParameter(Constants.BOOK_LOAN_REQ_BOOK_ID) != null) {
                                                       out.println(request.getParameter(Constants.BOOK_LOAN_REQ_BOOK_ID));
                                                   }%>">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="<%= Constants.BOOK_LOAN_REQ_BRANCH_ID%>">Branch ID:</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="<%= Constants.BOOK_LOAN_REQ_BRANCH_ID%>" 
                                               placeholder="Enter branch id" 
                                               value="<%if (request.getParameter(Constants.BOOK_LOAN_REQ_BRANCH_ID) != null) {
                                                       out.println(request.getParameter(Constants.BOOK_LOAN_REQ_BRANCH_ID));
                                                   }%>">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="<%= Constants.BOOK_LOAN_REQ_CARD_NO%>">Card Number:</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="<%= Constants.BOOK_LOAN_REQ_CARD_NO%>" 
                                               placeholder="Enter borrower card number" 
                                               value="<%if (request.getParameter(Constants.BOOK_LOAN_REQ_CARD_NO) != null) {
                                                       out.println(request.getParameter(Constants.BOOK_LOAN_REQ_CARD_NO));
                                                   }%>">
                                    </div>
                                </div>
                                <input type ="hidden" name ="<%=Constants.BOOK_LOAN_REQ_TYPE%>" value="<%=Constants.BOOK_LOAN_REQ_CHECKOUT_TYPE%>">
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="submit" class="btn btn-primary">Submit</button>
                                    </div>
                                </div>
                            </form>
                            <% if (hasStatus && request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE).equals(Constants.BOOK_LOAN_REQ_CHECKOUT_TYPE)) {%>
                            <h5>
                                <% if (request.getAttribute(Constants.STATUS_TYPE) == STATUS_TYPE.ERROR) {
                                %>
                                <div class="alert alert-danger" role="alert">
                                    <strong>
                                        <%= request.getAttribute(Constants.STATUS_BODY)%>
                                    </strong>
                                </div>
                                <% } else if (request.getAttribute(Constants.STATUS_TYPE) == STATUS_TYPE.WARNING) {
                                %>
                                <div class="alert alert-info" role="alert">
                                    <strong>
                                        <%= request.getAttribute(Constants.STATUS_BODY)%>
                                    </strong>
                                </div>
                                <% } else if (request.getAttribute(Constants.STATUS_TYPE) == STATUS_TYPE.SUCCESS) {
                                %>
                                <div class="alert alert-success" role="alert">
                                    <strong>
                                        <%= request.getAttribute(Constants.STATUS_BODY)%>
                                    </strong>
                                </div>
                                <% }%>
                            </h5>
                            <%}%>
                        </div>
                    </div>
                </div>

                <div id="checkIn" class="tab-pane fade in <% if (request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE) != null && request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE).equals(Constants.BOOK_LOAN_REQ_CHECKIN_TYPE)) {
                        out.println("active");
                    }%>">
                    <div class="panel panel-default">
                        <div class="panel-heading">Check in book</div>
                        <div class="panel-body">
                            <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/BookLoanController" method="POST">
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="<%=Constants.BOOK_LOAN_REQ_BOOK_ID%>">Book ID:</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="<%= Constants.BOOK_LOAN_REQ_BOOK_ID%>" 
                                               placeholder="Enter Book id" 
                                               value="<% if (request.getParameter(Constants.BOOK_LOAN_REQ_BOOK_ID) != null) {
                                                       out.println(request.getParameter(Constants.BOOK_LOAN_REQ_BOOK_ID));
                                                   }%>">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="<%= Constants.BOOK_LOAN_REQ_CARD_NO%>">Card Number:</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="<%= Constants.BOOK_LOAN_REQ_CARD_NO%>" 
                                               placeholder="Enter borrower card number" 
                                               value="<%if (request.getParameter(Constants.BOOK_LOAN_REQ_CARD_NO) != null) {
                                                       out.println(request.getParameter(Constants.BOOK_LOAN_REQ_CARD_NO));
                                                   }%>">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="<%= Constants.BOOK_LOAN_REQ_BORROWER_NAME%>">Borrower Name:</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="<%= Constants.BOOK_LOAN_REQ_BORROWER_NAME%>" 
                                               placeholder="Enter the borrower first name or last name" 
                                               value="<%if (request.getParameter(Constants.BOOK_LOAN_REQ_BORROWER_NAME) != null) {
                                                       out.println(request.getParameter(Constants.BOOK_LOAN_REQ_BORROWER_NAME));
                                                   }%>">
                                    </div>
                                </div>
                                <input type ="hidden" name ="<%=Constants.BOOK_LOAN_REQ_TYPE%>" value="<%=Constants.BOOK_LOAN_REQ_CHECKIN_TYPE%>">
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="submit" class="btn btn-primary">Submit</button>
                                    </div>
                                </div>
                            </form>



                            <% if (hasStatus && request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE).equals(Constants.BOOK_LOAN_REQ_CHECKIN_TYPE)) {%>
                            <h5>
                                <% if (request.getAttribute(Constants.STATUS_TYPE) == STATUS_TYPE.ERROR) {
                                %>
                                <div class="alert alert-danger" role="alert">
                                    <strong>
                                        <%= request.getAttribute(Constants.STATUS_BODY)%>
                                    </strong>
                                </div>
                                <% } else if (request.getAttribute(Constants.STATUS_TYPE) == STATUS_TYPE.WARNING) {
                                %>
                                <div class="alert alert-info" role="alert">
                                    <strong>
                                        <%= request.getAttribute(Constants.STATUS_BODY)%>
                                    </strong>
                                </div>
                                <% } else if (request.getAttribute(Constants.STATUS_TYPE) == STATUS_TYPE.SUCCESS) {
                                %>
                                <div class="alert alert-success" role="alert">
                                    <strong>
                                        <%= request.getAttribute(Constants.STATUS_BODY)%>
                                    </strong>
                                </div>
                                <% }%>
                            </h5>
                            <%}%>
                        </div>
                    </div>
                    

                    <%!
                        boolean hasData = false;
                    %>

                    <%
                        if (request.getAttribute(Constants.BOOK_LOAN_JSP_REQ) != null) {
                            hasData = true;
                        } else {
                            hasData = false;
                        }
                    %>
                    <div class="panel panel-default">
                        <div class="panel-heading">Search Results</div>
                        <div class="panel-body">
                            <%
                                if (hasData) {
                            %>
                            <div class="table-responsive">
                                <table class="table table-bordered">
                                    <tr>
                                        <th>First Name</th>
                                        <th>Last Name</th>
                                        <th>Card No</th>
                                        <th>Book ID</th>
                                        <th>Branch ID</th>
                                        <th>Due Date</th>
                                        <th>Action</th>
                                    </tr>
                                    <%!
                                        ArrayList<LoanBookListBean> data = new ArrayList<LoanBookListBean>();
                                    %>
                                    <%
                                        data = (ArrayList<LoanBookListBean>) request.getAttribute(Constants.BOOK_LOAN_JSP_REQ);
                                        for (LoanBookListBean bean : data) {
                                            out.println("<tr>");
                                    %>
                                    <%= "<td>" + bean.getBorrowerFName()
                                            + "</td>"%>
                                    <%= "<td>" + bean.getBorrowerLName()
                                            + "</td>"%>
                                    <%= "<td>" + bean.getCardNo()
                                            + "</td>"%>
                                    <%= "<td>" + bean.getBookId()
                                            + "</td>"%>
                                    <%= "<td>" + bean.getBranchId()
                                            + "</td>"%>
                                    <%= "<td>" + bean.getDueDate()
                                            + "</td>"%>
                                    <td>
                                        <%="<form method=\"POST\" action=\"CheckInBookController\">"
                                                + "<input type='submit' class='btn btn-sm btn-primary' value='Check In'>"
                                                + "<input type='hidden' name='" + Constants.CHECK_IN_REQ_BOOK_ID + "' value='" + bean.getBookId() + "'> "
                                                + "<input type='hidden' name='" + Constants.CHECK_IN_REQ_BRANCH_ID + "' value='" + bean.getBranchId() + "'> "
                                                + "<input type='hidden' name='" + Constants.CHECK_IN_REQ_CARD_NO + "' value='" + bean.getCardNo() + "'> "
                                                + "</form>"%>
                                    </td>

                                    <%
                                            out.println("</tr>");
                                        }
                                    %>
                                    <% if (data.size() == 0) { %> 
                                    <h4><span class="label label-warning">No results found</span> </h4>       
                                    <%}%>
                                </table>
                            </div>
                            <%                    } else {
                            %>
                            <h4><span class="label label-warning">No results found</span> </h4>       
                            <% }%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>