/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hpt150030.beans;

/**
 *
 * @author Hardik
 */
public class LoanBookListBean {

    public LoanBookListBean(String bookId, String cardNo, String borrowerFName, String borrowerLName, String branchId, String dueDate) {
        this.bookId = bookId;
        this.cardNo = cardNo;
        this.borrowerFName = borrowerFName;
        this.borrowerLName = borrowerLName;
        this.branchId = branchId;
        this.dueDate = dueDate;
    }

    private String bookId;
    private String cardNo;
    private String borrowerFName;
    private String borrowerLName;
    private String branchId;
    private String dueDate;

    

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBorrowerFName() {
        return borrowerFName;
    }

    public void setBorrowerFName(String borrowerFName) {
        this.borrowerFName = borrowerFName;
    }

    public String getBorrowerLName() {
        return borrowerLName;
    }

    public void setBorrowerLName(String borrowerLName) {
        this.borrowerLName = borrowerLName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
