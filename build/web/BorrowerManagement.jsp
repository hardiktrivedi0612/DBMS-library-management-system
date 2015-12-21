<%-- 
    Document   : BorrowerManagement
    Created on : Oct 13, 2015, 7:53:21 PM
    Author     : Hardik
--%>

<%@page import="com.hpt150030.utilities.STATUS_TYPE"%>
<%@page import="com.hpt150030.utilities.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Borrower Management</title>
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
                        <li><a href="BookLoanController">Loan</a></li>
                        <li><a href="FineTrackingController">Fines</a></li>
                        <li class="active"><a href="#">Borrowers</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <br>
        <br>
        <br>

        <div class="panel panel-default">
            <div class="panel-heading">Add new borrower</div>
            <div class="panel-body">
                <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/NewBorrowerController" method="POST">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="<%=Constants.NEW_BORROWER_REQ_FNAME%>">First Name:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="<%= Constants.NEW_BORROWER_REQ_FNAME%>" placeholder="Enter first name" 
                                   value="<% if (request.getParameter(Constants.NEW_BORROWER_REQ_FNAME) != null) {
                                           out.println(request.getParameter(Constants.NEW_BORROWER_REQ_FNAME));
                                       }%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="<%=Constants.NEW_BORROWER_REQ_LNAME%>">Last Name:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="<%= Constants.NEW_BORROWER_REQ_LNAME%>" placeholder="Enter last name"
                                   value="<% if (request.getParameter(Constants.NEW_BORROWER_REQ_LNAME) != null) {
                                           out.println(request.getParameter(Constants.NEW_BORROWER_REQ_LNAME));
                                       }%>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="<%=Constants.NEW_BORROWER_REQ_EMAIL%>">Email:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="<%= Constants.NEW_BORROWER_REQ_EMAIL%>" placeholder="Enter email"
                                   value="<% if (request.getParameter(Constants.NEW_BORROWER_REQ_EMAIL) != null) {
                                           out.println(request.getParameter(Constants.NEW_BORROWER_REQ_EMAIL));
                                       }%>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="<%=Constants.NEW_BORROWER_REQ_ADDRESS%>">Address:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="<%= Constants.NEW_BORROWER_REQ_ADDRESS%>" placeholder="Enter address"
                                   value="<% if (request.getParameter(Constants.NEW_BORROWER_REQ_ADDRESS) != null) {
                                           out.println(request.getParameter(Constants.NEW_BORROWER_REQ_ADDRESS));
                                       }%>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="<%=Constants.NEW_BORROWER_REQ_CITY%>">City:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="<%= Constants.NEW_BORROWER_REQ_CITY%>" placeholder="Enter city"
                                   value="<% if (request.getParameter(Constants.NEW_BORROWER_REQ_CITY) != null) {
                                           out.println(request.getParameter(Constants.NEW_BORROWER_REQ_CITY));
                                       }%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="<%=Constants.NEW_BORROWER_REQ_STATE%>">State:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="<%= Constants.NEW_BORROWER_REQ_STATE%>" placeholder="Enter state"
                                   value="<% if (request.getParameter(Constants.NEW_BORROWER_REQ_STATE) != null) {
                                           out.println(request.getParameter(Constants.NEW_BORROWER_REQ_STATE));
                                       }%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="<%=Constants.NEW_BORROWER_REQ_PHONE%>">Phone no:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="<%= Constants.NEW_BORROWER_REQ_PHONE%>" placeholder="Enter phone number"
                                   value="<% if (request.getParameter(Constants.NEW_BORROWER_REQ_PHONE) != null) {
                                           out.println(request.getParameter(Constants.NEW_BORROWER_REQ_PHONE));
                                       }%>">
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </div>
                </form>

                <% if (hasStatus) {%>
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
    </body>
</html>
