package edu.aitu.oop3.db.ReportingComponent;

import edu.aitu.oop3.db.MemberManagementComponent.Exception.MemberNotFoundException;
import edu.aitu.oop3.db.CoreComponent.Entities.Loan;
import edu.aitu.oop3.db.CoreComponent.Entities.LoanReport;
import edu.aitu.oop3.db.CoreComponent.Entities.Member;
import edu.aitu.oop3.db.LoanManagementComponent.Repository.Interface.LoanRepository;
import edu.aitu.oop3.db.MemberManagementComponent.Repository.Interface.MemberRepository;
import edu.aitu.oop3.db.LoanManagementComponent.Policy.FinePolicy;
import java.time.LocalDate;
import java.util.List;

public class LoanReportForMemberService {

    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;

    public LoanReportForMemberService(LoanRepository loanRepository,
                                      MemberRepository memberRepository) {
        this.loanRepository = loanRepository;
        this.memberRepository = memberRepository;
    }

    public LoanReport generateReport(int memberId) {
        Member member = memberRepository.findById(memberId);
        if (member == null) {
            throw new MemberNotFoundException("Member not found");
        }

        List<Loan> loans = loanRepository.findByMember_id(memberId);
        int activeLoans = (int) loans.stream()
                .filter(l -> l.getReturnDate() == null)
                .count();
        int overdueLoans = (int) loans.stream()
                .filter(l -> l.getReturnDate() == null &&
                        l.getDueDate().isBefore(LocalDate.now()))
                .count();
        int closedLoans = (int) loans.stream()
                .filter(l -> l.getReturnDate() != null)
                .count();

        int totalFines = loans.stream()
                .filter(l -> l.getReturnDate() != null &&
                        l.getReturnDate().isAfter(l.getDueDate()))
                .mapToInt(l ->
                        FinePolicy.getInstance()
                                .calculateFine(l.getDueDate(), l.getReturnDate()))
                .sum();

        return new LoanReport.Builder()
                .memberId(member.getId())
                .memberName(member.getFirstName())
                .loans(loans)
                .activeLoans(activeLoans)
                .overdueLoans(overdueLoans)
                .closedLoans(closedLoans)
                .totalFines(totalFines)
                .build();
    }
}
