<%-- 
    Document   : FineTracking
    Created on : Oct 24, 2015, 8:19:58 PM
    Author     : Hardik
--%>

<%@page import="com.hpt150030.beans.FineTrackingBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hpt150030.utilities.STATUS_TYPE"%>
<%@page import="com.hpt150030.utilities.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fine Tracking</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%!
            boolean hasData = false;
            boolean hasStatus = false;
        %>

        <%
            if (request.getAttribute(Constants.FINE_JSP_REQ) != null) {
                hasData = true;

            } else {
                hasData = false;
            }
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
                        <li class="active"><a href="#">Fines</a></li>
                        <li><a href="NewBorrowerController">Borrowers</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <br><br><br>

        <div class="panel panel-default">
            <div class="panel-heading">Refresh</div>
            <div class="panel-body">
                <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/FineRefreshController" method="POST">
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-primary">Press here to refresh the data</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <br>

        <div class="panel panel-default">
            <div class="panel-heading">Search</div>
            <div class="panel-body">
                <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/FineTrackingController" method="POST">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="<%=Constants.FINE_REQ_BORROWER_NAME%>">Borrower Name:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="<%= Constants.FINE_REQ_BORROWER_NAME%>" placeholder="Enter part or full first name / last name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="<%= Constants.FINE_REQ_CARD_NO%>">Card number:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="<%= Constants.FINE_REQ_CARD_NO%>" placeholder="Enter card no">
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
                            <th>Fine</th>
                            <th>Paid / Unpaid</th>
                            <th>Action</th>
                        </tr>
                        <%!
                            ArrayList<FineTrackingBean> data = new ArrayList<FineTrackingBean>();
                        %>
                        <%
                            data = (ArrayList<FineTrackingBean>) request.getAttribute(Constants.FINE_JSP_REQ);
                            for (FineTrackingBean bean : data) {
                        %>
                        <tr>
                            <td><%= bean.getFname()%>
                            </td>
                            <td><%= bean.getLname()%>
                            </td>
                            <td><%= bean.getCardNo()%>
                            </td>
                            <td><%= bean.getFineAmount()%>
                            </td>
                            <td><% if (bean.isIsPaid()) {%>
                                <span class="label label-success">Paid</span> 
                                <% } else { %> 
                                <span class="label label-danger">Unpaid</span> 
                                <% }%>
                            </td>
                            <td>
                                <%
                                    if (!bean.isIsPaid()) {
                                %>
                                <form method='POST' action='<%= request.getContextPath()%>/FinePaymentController'>
                                    <input type='submit' class='btn btn-sm btn-primary' value='Pay Now'/>
                                    <input type='hidden' name='<%= Constants.FINE_PAYMENT_REQ_CARD_NO%>' value='<%=bean.getCardNo()%>'/> 
                                    <input type='hidden' name='<%= Constants.FINE_PAYMENT_REQ_AMT%>' value='<%= bean.getFineAmount()%>'/>
                                </form>
                                <%}%>
                            </td>
                        </tr>
                        <%}%>
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
    </body>
</html>
