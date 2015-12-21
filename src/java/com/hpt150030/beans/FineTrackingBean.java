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
public class FineTrackingBean {

    private String fname;
    private String lname;
    private String cardNo;
    private String fineAmount;
    private boolean isPaid;
    
    public FineTrackingBean(String fname, String lname, String cardNo, String fineAmount, boolean isPaid) {
        this.fname = fname;
        this.lname = lname;
        this.cardNo = cardNo;
        this.fineAmount = fineAmount;
        this.isPaid = isPaid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(String fineAmount) {
        this.fineAmount = fineAmount;
    }

    public boolean isIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }
}
