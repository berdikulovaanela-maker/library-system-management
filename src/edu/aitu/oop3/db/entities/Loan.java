package edu.aitu.oop3.db.entities;

import java.time.LocalDate;

public class Loan {
    private int id;
    private int memberId;
    private int bookId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    public Loan(Builder builder) {
        this.id = builder.id;
        this.memberId = builder.memberId;
        this.bookId = builder.bookId;
        this.loanDate = builder.loanDate;
        this.dueDate = builder.dueDate;
        this.returnDate = builder.returnDate;
    }
    public static class Builder{
        private int id;
        private int memberId;
        private int bookId;
        private LocalDate loanDate;
        private LocalDate dueDate;
        private LocalDate returnDate;
        public Builder(){}
        public Builder memberId(int memberId){
            this.memberId = memberId;
            return this;
        }
        public Builder bookId(int bookId){
            this.bookId = bookId;
            return this;
        }
        public Builder loanDate(LocalDate loanDate){
            if(loanDate == null){
                throw new IllegalArgumentException("Loan Date cannot be null");
            }
            this.loanDate = loanDate;
            return this;
        }
        public Builder dueDate(LocalDate dueDate){
            if(dueDate == null || dueDate.isBefore(loanDate)){
                throw new IllegalArgumentException("Due Date cannot be null or before loan date");
            }
            this.dueDate = dueDate;
            return this;
        }
        public Builder returnDate(LocalDate returnDate){
            this.returnDate = returnDate;
            return this;
        }
        public Builder id(int id){
            this.id = id;
            return this;
        }
        public Loan build(){
            return new Loan(this);
        }
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getMemberId() {
        return memberId;
    }
    public int getBookId() {
        return bookId;
    }
    public LocalDate getLoanDate() {
        return loanDate;
    }
    public LocalDate getDueDate() {return dueDate;}
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    @Override
    public String toString() {
        return "id:" + id + "memberId:" + memberId + "bookId:" + bookId + "loanDate:" + loanDate + "dueDate:" + dueDate + "returnDate:" + returnDate;
    }
}
