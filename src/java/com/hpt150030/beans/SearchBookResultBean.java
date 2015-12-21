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
public class SearchBookResultBean {
    
    private String bookId;
    private String title;
    private String authors;
    private String branchId;
    private String noOfCopies;
    private String availability;

    @Override
    public String toString() {
        return "SearchBookResultBean{" + "bookId=" + bookId + ", title=" + title + ", authors=" + authors + ", branchId=" + branchId + ", noOfCopies=" + noOfCopies + ", availability=" + availability + '}';
    }

    public SearchBookResultBean(String bookId, String title, String authors, String branchId, String noOfCopies, String availability) {
        this.bookId = bookId;
        this.title = title;
        this.authors = authors;
        this.branchId = branchId;
        this.noOfCopies = noOfCopies;
        this.availability = availability;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(String noOfCopies) {
        this.noOfCopies = noOfCopies;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
