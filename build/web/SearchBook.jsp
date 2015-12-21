<%-- 
    Document   : SearchBook
    Created on : Oct 13, 2015, 1:03:16 AM
    Author     : Hardik
--%>

<%@page import="com.hpt150030.utilities.STATUS_TYPE"%>
<%@page import="java.util.List"%>
<%@page import="com.hpt150030.beans.SearchBookResultBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hpt150030.utilities.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Book</title>
    </head>
    <body>
        <!DOCTYPE html>
    <html>
        <head>
            <title>Book Search and Availability</title>
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
                if (request.getAttribute(Constants.BOOK_SEARCH_JSP_REQ) != null) {
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
                            <li class="active"><a href="#">Search</a></li>
                            <li><a href="BookLoanController">Loan</a></li>
                            <li><a href="FineTrackingController">Fines</a></li>
                            <li><a href="NewBorrowerController">Borrowers</a></li>
                        </ul>
                    </div>
                </div>
            </nav>


            <br><br><br>



            <div class="panel panel-default">
                <div class="panel-heading">Search</div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/SearchBookController" method="POST">
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="<%=Constants.BOOK_SEARCH_CONTROLLER_REQ_BOOK_ID%>">Book ID:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="<%= Constants.BOOK_SEARCH_CONTROLLER_REQ_BOOK_ID%>" placeholder="Enter Book ID">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="<%= Constants.BOOK_SEARCH_CONTROLLER_REQ_TITLE%>">Title:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="<%= Constants.BOOK_SEARCH_CONTROLLER_REQ_TITLE%>" placeholder="Enter title">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2" for="<%= Constants.BOOK_SEARCH_CONTROLLER_REQ_AUTHORS%>">Author(s):</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="<%= Constants.BOOK_SEARCH_CONTROLLER_REQ_AUTHORS%>" placeholder="Enter comma separated author names">
                            </div>
                        </div>
                        <input type ="hidden" name ="<%=Constants.BOOK_LOAN_REQ_TYPE%>" value="<%=Constants.BOOK_LOAN_REQ_CHECKOUT_TYPE%>">
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
                                <th>Book ID</th>
                                <th>Title</th>
                                <th>Author(s)</th>
                                <th>Branch ID</th>
                                <th>No of copies</th>
                                <th>Availability</th>
                                <th>Action</th>
                            </tr>
                            <%!
                                ArrayList<SearchBookResultBean> data = new ArrayList<SearchBookResultBean>();
                            %>
                            <%
                                data = (ArrayList<SearchBookResultBean>) request.getAttribute(Constants.BOOK_SEARCH_JSP_REQ);
                                for (SearchBookResultBean bean : data) {
                            %>
                            <tr>
                                <td><%= bean.getBookId()%>
                                </td>
                                <td><%= bean.getTitle()%>
                                </td>
                                <td><%= bean.getAuthors()%>
                                </td>
                                <td><%= bean.getBranchId()%>
                                </td>
                                <td><%= bean.getNoOfCopies()%>
                                </td>
                                <td><%= bean.getAvailability()%>
                                </td>
                                <td>
                                    <%
                                        if (Integer.parseInt(bean.getAvailability()) > 0) {
                                    %>
                                    <form method='POST' action='<%= request.getContextPath()%>/BookLoanController'>
                                        <input type='submit' class='btn btn-sm btn-primary' value='Check Out'/>
                                        <input type='hidden' name='<%= Constants.BOOK_LOAN_REQ_BOOK_ID%>' value='<%=bean.getBookId()%>'/> 
                                        <input type='hidden' name='<%= Constants.BOOK_LOAN_REQ_BRANCH_ID%>' value='<%= bean.getBranchId()%>'/>
                                    </form>

                                    <%
                                    } else {
                                    %>
                                    <%= "Loaning not available"%>
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
</body>
</html>