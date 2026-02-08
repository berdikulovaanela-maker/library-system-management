package edu.aitu.oop3.db.LoanManagementComponent.Repository.Interface;

import edu.aitu.oop3.db.CoreComponent.Entities.Loan;
import edu.aitu.oop3.db.CoreComponent.Repository.Interface.CrudRepository;

import java.util.List;

public interface LoanRepository extends CrudRepository<Loan,Integer> {
        List<Loan> findByMember_id(int member_id);
        void updateLoanStatus(Loan loan);
}
