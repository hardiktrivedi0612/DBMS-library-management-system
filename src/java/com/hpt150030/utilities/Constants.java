/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hpt150030.utilities;

/**
 *
 * @author Hardik
 */
public class Constants {
    public static final String BOOK_SEARCH_CONTROLLER_REQ_BOOK_ID = "bookId";
    public static final String BOOK_SEARCH_CONTROLLER_REQ_TITLE = "title";
    public static final String BOOK_SEARCH_CONTROLLER_REQ_AUTHORS = "authors";
    public static final String BOOK_SEARCH_RESP_BOOK_ID = "bookId";
    public static final String BOOK_SEARCH_RESP_BRANCH_ID = "branchId";
    
    public static final String BOOK_SEARCH_JSP_REQ = "booksList";
    
    public static final String BOOK_LOAN_REQ_BOOK_ID = "bookId";
    public static final String BOOK_LOAN_REQ_BRANCH_ID = "branchId";
    public static final String BOOK_LOAN_REQ_BORROWER_ID = "borrowerId";
    public static final String BOOK_LOAN_REQ_CARD_NO = "cardNo";
    public static final String BOOK_LOAN_REQ_BORROWER_NAME = "borrowerName";
    public static final String BOOK_LOAN_REQ_CHECKIN_TYPE = "checkin";
    public static final String BOOK_LOAN_REQ_CHECKOUT_TYPE = "checkout";
    public static final String BOOK_LOAN_REQ_TYPE = "type";
    
    public static final String CHECK_IN_DATE = "date";
    public static final String CHECK_IN_REQ_DATE = "date";
    public static final String CHECK_IN_REQ_BOOK_ID = "bookId";
    public static final String CHECK_IN_REQ_CARD_NO = "cardNo";
    public static final String CHECK_IN_REQ_BRANCH_ID = "branchId";
    
    public static final String NEW_BORROWER_REQ_FNAME = "fName";
    public static final String NEW_BORROWER_REQ_LNAME = "lName";
    public static final String NEW_BORROWER_REQ_EMAIL = "email";
    public static final String NEW_BORROWER_REQ_ADDRESS = "address";
    public static final String NEW_BORROWER_REQ_CITY = "city";
    public static final String NEW_BORROWER_REQ_STATE = "state";
    public static final String NEW_BORROWER_REQ_PHONE = "phone";
    
    public static final String BOOK_LOAN_JSP_REQ = "loanList";
    
    public static final String HAS_STATUS = "hasStatus";
    
    public static final String STATUS_HEADER = "statusHeader";
    public static final String STATUS_BODY = "statusBody";
    public static final String STATUS_TYPE = "statusType";
    public static final String STATUS_TYPE_ERROR = "error";
    public static final String STATUS_TYPE_WARNING = "warning";
    public static final String STATUS_TYPE_SUCCESS = "success";
    
    
    public static final String FINE_REQ_BORROWER_NAME = "borrowerName";
    public static final String FINE_REQ_CARD_NO = "cardNo";
    public static final String FINE_REQ_BOOK_ID = "bookId";
    
    public static final String FINE_JSP_REQ = "fineList";
    
    public static final String FINE_PAYMENT_REQ_CARD_NO = "cardNo";
    public static final String FINE_PAYMENT_REQ_AMT = "amount";
}
