package com.libraryproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "loans")
public class Loans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLoan")
    private int idLoan;

    @Column(name="idUser")
    private int idUser;

    @Column(name="idBook")
    private int idBook;

    @Column(name="loanDate", nullable = false)
    private Date loanDate;

    @Column(name="estimatedReturnDate")
    private Date estimatedReturnDate;

    @Column(name="realReturnDate")
    private Date realReturnDate;

    @Enumerated(EnumType.STRING)
    @Column (name = "loanStatus", length = 12, nullable =  false)
    private EnumType loanStatus;

    public Loans(){
    }

    public Loans(int idLoan, int idUser, int idBook, Date loanDate, Date estimatedReturnDate,
    Date realReturnDate, EnumType loanStatus){
    this.idLoan=idLoan;
        this.idUser=idUser;
        this.idBook=idBook;
        this.loanDate=loanDate;
        this.estimatedReturnDate=estimatedReturnDate;
        this.realReturnDate=realReturnDate;
        this.loanStatus=loanStatus;
    }

    public int getIdLoan(){
        return idLoan;
    }

    public void setIdLoan(int idLoan){
        this.idLoan=idLoan;
    }

    public int getIdUser(){
        return idUser;
    }

    public void setIdUser(int idUser){
        this.idUser=idUser;
    }

    public int getIdBook(){
        return idBook;
    }

    public void setidBook(int idBook){
        this.idBook=idBook;
    }

    public Date getLoanDate(){
        return loanDate;
    }

    public void setLoanDate(Date loanDate){
        this.loanDate=loanDate;
    }

    public Date getEstimatedReturnDate(){
        return estimatedReturnDate;
    }

    public void setEstimatedReturnDate(Date estimatedReturnDate){
        this.estimatedReturnDate=estimatedReturnDate;
    }

    public Date getRealReturnDate(){
        return estimatedReturnDate;
    }

    public void setRealReturnDate(Date realReturnDate){
        this.realReturnDate=realReturnDate;
    }

    public EnumType getLoanStatus(){
        return loanStatus;
    }

    public void setLoanStatus(EnumType loanStatus){
        this.loanStatus=loanStatus;
    }

    @Override
    public String toString() {
        return "IdLoan [idLoan=" + idLoan + ", idUser=" + idUser + ", idBook=" + idBook + ", loanDate=" + loanDate
                + ", estimatedReturnDate=" + estimatedReturnDate + ", realReturnDate=" + realReturnDate + ", loanStatus=" + loanStatus
                + "]";
    }

    public enum loanStatus {
        active,
        returned,
        delayed,
        not_returned
    }

}
