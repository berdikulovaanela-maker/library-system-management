package edu.aitu.oop3.db.repositories.interfaces;

import edu.aitu.oop3.db.entities.Loan;

import java.util.List;

public interface LoanRepository extends CrudRepository<Loan,Integer> {
        List<Loan> findByMember_id(int member_id);
        void updateLoanStatus(Loan loan);
}
