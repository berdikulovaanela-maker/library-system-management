package edu.aitu.oop3.db.Services;

import edu.aitu.oop3.db.entities.Loan;
import edu.aitu.oop3.db.repositories.interfaces.LoanRepository;
import java.util.List;

public class CurrentLoansService {
    private final LoanRepository loanRepository;
    public CurrentLoansService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }
    public List<Loan> execute(int memberId) {
        return loanRepository.findActiveLoansByMemberId(memberId);
    }
}

