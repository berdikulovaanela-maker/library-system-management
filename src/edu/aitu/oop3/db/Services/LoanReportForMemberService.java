package edu.aitu.oop3.db.Services;

import edu.aitu.oop3.db.Exceptions.MemberNotFoundException;
import edu.aitu.oop3.db.entities.Loan;
import edu.aitu.oop3.db.entities.LoanReport;
import edu.aitu.oop3.db.entities.Member;
import edu.aitu.oop3.db.repositories.interfaces.LoanRepository;
import edu.aitu.oop3.db.repositories.interfaces.MemberRepository;

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
