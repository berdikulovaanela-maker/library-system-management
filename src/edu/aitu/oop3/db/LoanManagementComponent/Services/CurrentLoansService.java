package edu.aitu.oop3.db.LoanManagementComponent.Services;

import edu.aitu.oop3.db.LoanManagementComponent.Repository.Interface.LoanRepository;
import edu.aitu.oop3.db.CoreComponent.Entities.Loan;

import java.util.List;

public class CurrentLoansService {
    private final LoanRepository loanRepository;
    public CurrentLoansService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }
    public List<Loan> execute(int memberId) {
        return loanRepository.findAll().stream().filter(loan -> loan.getMemberId() == memberId).filter(loan -> loan.getReturnDate() == null).toList();
    }
}

