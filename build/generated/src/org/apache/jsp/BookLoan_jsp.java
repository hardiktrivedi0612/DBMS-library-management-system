package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.hpt150030.utilities.STATUS_TYPE;
import java.util.ArrayList;
import com.hpt150030.beans.LoanBookListBean;
import com.hpt150030.controllers.BookLoanController;
import com.hpt150030.utilities.Constants;

public final class BookLoan_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


            boolean hasStatus = false;
        

                        boolean hasData = false;
                    

                                        ArrayList<LoanBookListBean> data = new ArrayList<LoanBookListBean>();
                                    
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <title>Book Loan</title>\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css\">\r\n");
      out.write("        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>\r\n");
      out.write("        <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("\r\n");
      out.write("        ");
      out.write("\r\n");
      out.write("        ");

            if (request.getAttribute(Constants.HAS_STATUS) != null) {
                if ((Boolean) request.getAttribute(Constants.HAS_STATUS) == true) {
                    hasStatus = true;
                }
            } else {
                hasStatus = false;
            }
        
      out.write("\r\n");
      out.write("        <nav class=\"navbar navbar-inverse navbar-fixed-top\">\r\n");
      out.write("            <div class=\"container\">\r\n");
      out.write("                <div class=\"navbar-header\">\r\n");
      out.write("                    <a class=\"navbar-brand\" href=\"#\">Library Management System</a>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div id=\"navbar\" class=\"navbar-collapse collapse\">\r\n");
      out.write("                    <ul class=\"nav navbar-nav navbar-right\">\r\n");
      out.write("                        <li><a href=\"index.html\">Home</a></li>\r\n");
      out.write("                        <li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/SearchBookController\">Search</a></li>\r\n");
      out.write("                        <li class=\"active\"><a href=\"#\">Loan</a></li>\r\n");
      out.write("                        <li><a href=\"#\">Borrowers</a></li>\r\n");
      out.write("                    </ul>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </nav>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"container\">\r\n");
      out.write("            <h2>Dynamic Tabs</h2>\r\n");
      out.write("            <ul class=\"nav nav-tabs\">\r\n");
      out.write("                <li ");
 if ((request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE) == null) || request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE).equals(Constants.BOOK_LOAN_REQ_CHECKOUT_TYPE)) {
                        out.println("class=\"active\"");
                    }
      out.write("><a data-toggle=\"tab\" href=\"#checkOut\">Check out</a></li>\r\n");
      out.write("                <li ");
 if (request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE) != null && request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE).equals(Constants.BOOK_LOAN_REQ_CHECKIN_TYPE)) {
                        out.println("class=\"active\"");
                    }
      out.write("><a data-toggle=\"tab\" href=\"#checkIn\">Check in</a></li>\r\n");
      out.write("            </ul>\r\n");
      out.write("\r\n");
      out.write("            <br>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"tab-content\">\r\n");
      out.write("\r\n");
      out.write("                <div id=\"checkOut\" class=\"tab-pane fade in ");
 if ((request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE) == null && request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE) == null) || request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE).equals(Constants.BOOK_LOAN_REQ_CHECKOUT_TYPE)) {
                        out.println("active");
                    }
      out.write("\">\r\n");
      out.write("                    <div class=\"panel panel-default\">\r\n");
      out.write("                        <div class=\"panel-heading\">Check in book</div>\r\n");
      out.write("                        <div class=\"panel-body\">\r\n");
      out.write("                            <form class=\"form-horizontal\" role=\"form\" action=\"");
      out.print(request.getContextPath());
      out.write("/BookLoanController\" method=\"POST\">\r\n");
      out.write("                                <div class=\"form-group\">\r\n");
      out.write("                                    <label class=\"control-label col-sm-2\" for=\"");
      out.print(Constants.BOOK_LOAN_REQ_BOOK_ID);
      out.write("\">Book ID:</label>\r\n");
      out.write("                                    <div class=\"col-sm-10\">\r\n");
      out.write("                                        <input type=\"text\" class=\"form-control\" name=\"");
      out.print( Constants.BOOK_LOAN_REQ_BOOK_ID);
      out.write("\" \r\n");
      out.write("                                               placeholder=\"Enter Book id\" \r\n");
      out.write("                                               value=\"");
 if (request.getParameter(Constants.BOOK_LOAN_REQ_BOOK_ID) != null) {
                                                       out.println(request.getParameter(Constants.BOOK_LOAN_REQ_BOOK_ID));
                                                   }
      out.write("\">\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <div class=\"form-group\">\r\n");
      out.write("                                    <label class=\"control-label col-sm-2\" for=\"");
      out.print( Constants.BOOK_LOAN_REQ_BRANCH_ID);
      out.write("\">Branch ID:</label>\r\n");
      out.write("                                    <div class=\"col-sm-10\">\r\n");
      out.write("                                        <input type=\"text\" class=\"form-control\" name=\"");
      out.print( Constants.BOOK_LOAN_REQ_BRANCH_ID);
      out.write("\" \r\n");
      out.write("                                               placeholder=\"Enter branch id\" \r\n");
      out.write("                                               value=\"");
if (request.getParameter(Constants.BOOK_LOAN_REQ_BRANCH_ID) != null) {
                                                       out.println(request.getParameter(Constants.BOOK_LOAN_REQ_BRANCH_ID));
                                                   }
      out.write("\">\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("\r\n");
      out.write("                                <div class=\"form-group\">\r\n");
      out.write("                                    <label class=\"control-label col-sm-2\" for=\"");
      out.print( Constants.BOOK_LOAN_REQ_CARD_NO);
      out.write("\">Card No:</label>\r\n");
      out.write("                                    <div class=\"col-sm-10\">\r\n");
      out.write("                                        <input type=\"text\" class=\"form-control\" name=\"");
      out.print( Constants.BOOK_LOAN_REQ_CARD_NO);
      out.write("\" \r\n");
      out.write("                                               placeholder=\"Enter borrower card number\" \r\n");
      out.write("                                               value=\"");
if (request.getParameter(Constants.BOOK_LOAN_REQ_CARD_NO) != null) {
                                                       out.println(request.getParameter(Constants.BOOK_LOAN_REQ_CARD_NO));
                                                   }
      out.write("\">\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <input type =\"hidden\" name =\"");
      out.print(Constants.BOOK_LOAN_REQ_TYPE);
      out.write("\" value=\"");
      out.print(Constants.BOOK_LOAN_REQ_CHECKOUT_TYPE);
      out.write("\">\r\n");
      out.write("                                <div class=\"form-group\">\r\n");
      out.write("                                    <div class=\"col-sm-offset-2 col-sm-10\">\r\n");
      out.write("                                        <button type=\"submit\" class=\"btn btn-primary\">Submit</button>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </form>\r\n");
      out.write("                            ");
 if (hasStatus && request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE).equals(Constants.BOOK_LOAN_REQ_CHECKOUT_TYPE)) {
      out.write("\r\n");
      out.write("                            <div class=\"panel panel-default\">\r\n");
      out.write("                                <div class=\"panel-heading\">\r\n");
      out.write("                                    ");
      out.print( request.getAttribute(Constants.STATUS_HEADER));
      out.write("\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <div class=\"panel-body\">\r\n");
      out.write("                                    <h4>\r\n");
      out.write("                                        ");
 if (request.getAttribute(Constants.STATUS_TYPE) == STATUS_TYPE.ERROR) {
                                        
      out.write("\r\n");
      out.write("                                        <span class=\"label label-danger\">");
      out.print( request.getAttribute(Constants.STATUS_BODY));
      out.write("\r\n");
      out.write("                                        </span>\r\n");
      out.write("                                        ");
 } else if (request.getAttribute(Constants.STATUS_TYPE) == STATUS_TYPE.WARNING) {
                                        
      out.write("\r\n");
      out.write("                                        <span class=\"label label-info\">");
      out.print( request.getAttribute(Constants.STATUS_BODY));
      out.write("\r\n");
      out.write("                                        </span>\r\n");
      out.write("                                        ");
 } else if (request.getAttribute(Constants.STATUS_TYPE) == STATUS_TYPE.SUCCESS) {
                                        
      out.write("\r\n");
      out.write("                                        <span class=\"label label-success\">");
      out.print( request.getAttribute(Constants.STATUS_BODY));
      out.write("\r\n");
      out.write("                                        </span>\r\n");
      out.write("                                        ");
 }
      out.write("\r\n");
      out.write("                                    </h4>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            ");
}
      out.write("\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("                <div id=\"checkIn\" class=\"tab-pane fade in ");
 if (request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE) != null && request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE).equals(Constants.BOOK_LOAN_REQ_CHECKIN_TYPE)) {
                        out.println("active");
                    }
      out.write("\">\r\n");
      out.write("                    <div class=\"panel panel-default\">\r\n");
      out.write("                        <div class=\"panel-heading\">Check in book</div>\r\n");
      out.write("                        <div class=\"panel-body\">\r\n");
      out.write("                            <form class=\"form-horizontal\" role=\"form\" action=\"");
      out.print(request.getContextPath());
      out.write("/BookLoanController\" method=\"POST\">\r\n");
      out.write("                                <div class=\"form-group\">\r\n");
      out.write("                                    <label class=\"control-label col-sm-2\" for=\"");
      out.print(Constants.BOOK_LOAN_REQ_BOOK_ID);
      out.write("\">Book ID:</label>\r\n");
      out.write("                                    <div class=\"col-sm-10\">\r\n");
      out.write("                                        <input type=\"text\" class=\"form-control\" name=\"");
      out.print( Constants.BOOK_LOAN_REQ_BOOK_ID);
      out.write("\" \r\n");
      out.write("                                               placeholder=\"Enter Book id\" \r\n");
      out.write("                                               value=\"");
 if (request.getParameter(Constants.BOOK_LOAN_REQ_BOOK_ID) != null) {
                                                       out.println(request.getParameter(Constants.BOOK_LOAN_REQ_BOOK_ID));
                                                   }
      out.write("\">\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <div class=\"form-group\">\r\n");
      out.write("                                    <label class=\"control-label col-sm-2\" for=\"");
      out.print( Constants.BOOK_LOAN_REQ_CARD_NO);
      out.write("\">Card Number:</label>\r\n");
      out.write("                                    <div class=\"col-sm-10\">\r\n");
      out.write("                                        <input type=\"text\" class=\"form-control\" name=\"");
      out.print( Constants.BOOK_LOAN_REQ_CARD_NO);
      out.write("\" \r\n");
      out.write("                                               placeholder=\"Enter card id\" \r\n");
      out.write("                                               value=\"");
if (request.getParameter(Constants.BOOK_LOAN_REQ_CARD_NO) != null) {
                                                       out.println(request.getParameter(Constants.BOOK_LOAN_REQ_CARD_NO));
                                                   }
      out.write("\">\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("\r\n");
      out.write("                                <div class=\"form-group\">\r\n");
      out.write("                                    <label class=\"control-label col-sm-2\" for=\"");
      out.print( Constants.BOOK_LOAN_REQ_BORROWER_NAME);
      out.write("\">Borrower Name:</label>\r\n");
      out.write("                                    <div class=\"col-sm-10\">\r\n");
      out.write("                                        <input type=\"text\" class=\"form-control\" name=\"");
      out.print( Constants.BOOK_LOAN_REQ_BORROWER_NAME);
      out.write("\" \r\n");
      out.write("                                               placeholder=\"Enter the borrower first name or last name\" \r\n");
      out.write("                                               value=\"");
if (request.getParameter(Constants.BOOK_LOAN_REQ_BORROWER_NAME) != null) {
                                                       out.println(request.getParameter(Constants.BOOK_LOAN_REQ_BORROWER_NAME));
                                                   }
      out.write("\">\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <input type =\"hidden\" name =\"");
      out.print(Constants.BOOK_LOAN_REQ_TYPE);
      out.write("\" value=\"");
      out.print(Constants.BOOK_LOAN_REQ_CHECKIN_TYPE);
      out.write("\">\r\n");
      out.write("                                <div class=\"form-group\">\r\n");
      out.write("                                    <div class=\"col-sm-offset-2 col-sm-10\">\r\n");
      out.write("                                        <button type=\"submit\" class=\"btn btn-primary\">Submit</button>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </form>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <br><br>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                    ");
 if (hasStatus && request.getAttribute(Constants.BOOK_LOAN_REQ_TYPE).equals(Constants.BOOK_LOAN_REQ_CHECKIN_TYPE)) {
      out.write("\r\n");
      out.write("                    <div class=\"panel panel-default\">\r\n");
      out.write("                        <div class=\"panel-heading\">\r\n");
      out.write("                            ");
      out.print( request.getAttribute(Constants.STATUS_HEADER));
      out.write("\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"panel-body\">\r\n");
      out.write("                            <h4>\r\n");
      out.write("                                ");
 if (request.getAttribute(Constants.STATUS_TYPE) == STATUS_TYPE.ERROR) {
                                
      out.write("\r\n");
      out.write("                                <span class=\"label label-danger\">");
      out.print( request.getAttribute(Constants.STATUS_BODY));
      out.write("\r\n");
      out.write("                                </span>\r\n");
      out.write("                                ");
 } else if (request.getAttribute(Constants.STATUS_TYPE) == STATUS_TYPE.WARNING) {
                                
      out.write("\r\n");
      out.write("                                <span class=\"label label-info\">");
      out.print( request.getAttribute(Constants.STATUS_BODY));
      out.write("\r\n");
      out.write("                                </span>\r\n");
      out.write("                                ");
 } else if (request.getAttribute(Constants.STATUS_TYPE) == STATUS_TYPE.SUCCESS) {
                                
      out.write("\r\n");
      out.write("                                <span class=\"label label-success\">");
      out.print( request.getAttribute(Constants.STATUS_BODY));
      out.write("\r\n");
      out.write("                                </span>\r\n");
      out.write("                                ");
 }
      out.write("\r\n");
      out.write("                            </h4>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    ");
}
      out.write("\r\n");
      out.write("\r\n");
      out.write("                    ");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                    ");

                        if (request.getAttribute(Constants.BOOK_LOAN_JSP_REQ) != null) {
                            hasData = true;
                        } else {
                            hasData = false;
                        }
                    
      out.write("\r\n");
      out.write("                    <div class=\"panel panel-default\">\r\n");
      out.write("                        <div class=\"panel-heading\">Search Results</div>\r\n");
      out.write("                        <div class=\"panel-body\">\r\n");
      out.write("                            ");

                                if (hasData) {
                            
      out.write("\r\n");
      out.write("                            <div class=\"table-responsive\">\r\n");
      out.write("                                <table class=\"table table-stripped\">\r\n");
      out.write("                                    <tr>\r\n");
      out.write("                                        <th>First Name</th>\r\n");
      out.write("                                        <th>Last Name</th>\r\n");
      out.write("                                        <th>Card No</th>\r\n");
      out.write("                                        <th>Book ID</th>\r\n");
      out.write("                                        <th>Branch ID</th>\r\n");
      out.write("                                        <th>Due Date</th>\r\n");
      out.write("                                        <th>Action</th>\r\n");
      out.write("                                    </tr>\r\n");
      out.write("                                    ");
      out.write("\r\n");
      out.write("                                    ");

                                        data = (ArrayList<LoanBookListBean>) request.getAttribute(Constants.BOOK_LOAN_JSP_REQ);
                                        for (LoanBookListBean bean : data) {
                                            out.println("<tr>");
                                    
      out.write("\r\n");
      out.write("                                    ");
      out.print( "<td>" + bean.getBorrowerFName()
                                            + "</td>");
      out.write("\r\n");
      out.write("                                    ");
      out.print( "<td>" + bean.getBorrowerLName()
                                            + "</td>");
      out.write("\r\n");
      out.write("                                    ");
      out.print( "<td>" + bean.getCardNo()
                                            + "</td>");
      out.write("\r\n");
      out.write("                                    ");
      out.print( "<td>" + bean.getBookId()
                                            + "</td>");
      out.write("\r\n");
      out.write("                                    ");
      out.print( "<td>" + bean.getBranchId()
                                            + "</td>");
      out.write("\r\n");
      out.write("                                    ");
      out.print( "<td>" + bean.getDueDate()
                                            + "</td>");
      out.write("\r\n");
      out.write("                                    <td>\r\n");
      out.write("                                        ");
      out.print("<form method=\"POST\" action=\"CheckInBookController\">"
                                                + "<input type='submit' class='btn btn-sm btn-primary' value='Loan'>"
                                                + "<input type='hidden' name='" + Constants.CHECK_IN_REQ_BOOK_ID + "' value='" + bean.getBookId() + "'> "
                                                + "<input type='hidden' name='" + Constants.CHECK_IN_REQ_BRANCH_ID + "' value='" + bean.getBranchId() + "'> "
                                                + "<input type='hidden' name='" + Constants.CHECK_IN_REQ_CARD_NO + "' value='" + bean.getCardNo() + "'> "
                                                + "</form>");
      out.write("\r\n");
      out.write("                                    </td>\r\n");
      out.write("\r\n");
      out.write("                                    ");

                                            out.println("</tr>");
                                        }
                                    
      out.write("\r\n");
      out.write("                                    ");
 if (data.size() == 0) { 
      out.write(" \r\n");
      out.write("                                    <h4><span class=\"label label-warning\">No results found</span> </h4>       \r\n");
      out.write("                                    ");
}
      out.write("\r\n");
      out.write("                                </table>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            ");
                    } else {
                            
      out.write("\r\n");
      out.write("                            <h4><span class=\"label label-warning\">No results found</span> </h4>       \r\n");
      out.write("                            ");
 }
      out.write("\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
