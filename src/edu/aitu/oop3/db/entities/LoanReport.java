package edu.aitu.oop3.db.entities;
import java.util.List;
public class LoanReport {
    private int member_id;
    private String member_name;
    private List<Loan> loans;
    private int activeLoans;
    private int overdueLoans;
    private int closedLoans;
    private int totalFine;
    private LoanReport(Builder builder){}
    public static class Builder{
        private int memberId;
        private String member_Firstname;
        private List<Loan> loans;
        private int activeLoans;
        private int overdueLoans;
        private int closedLoans;
        private int totalFine;
        public Builder memberId(int memberId){
            this.memberId = memberId;
            return this;
        }
        public Builder memberName(String member_Firstname){
            this.member_Firstname = member_Firstname;
            return this;

        }
        public Builder loans(List<Loan> loans){
            this.loans = loans;
            return this;
        }
        public Builder activeLoans(int activeLoans){
            this.activeLoans = activeLoans;
            return this;
        }
        public Builder overdueLoans(int overdueLoans){
            this.overdueLoans = overdueLoans;
            return this;
        }
        public Builder closedLoans(int closedLoans){
            this.closedLoans = closedLoans;
            return this;
        }
        public Builder totalFines(int totalFine){
            this.totalFine = totalFine;
            return this;
        }
        public LoanReport build(){
            return new LoanReport(this);
        }
    }
    public int getMember_id() {
        return member_id;
    }
    public String getMember_name() {
        return member_name;
    }
    public List<Loan> getLoans() {
        return loans;
    }
    public int getActiveLoans() {
        return activeLoans;
    }
    public int getOverdueLoans() {
        return overdueLoans;
    }
    public int getClosedLoans() {
        return closedLoans;
    }
    public int getTotalFine() {
        return totalFine;
    }

    @Override
    public String toString() {
        return "[member ID:" + member_id +"member name:" + member_name + "all loans:" + loans + "active loans:" + activeLoans + "overdue loans:" + overdueLoans + "closedloans:" + closedLoans + "totalfine:" + totalFine + "]" ;
    }
}
