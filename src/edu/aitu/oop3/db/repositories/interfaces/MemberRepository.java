package edu.aitu.oop3.db.repositories.interfaces;

import edu.aitu.oop3.db.entities.Member;

import java.util.List;

public interface MemberRepository {
    Member findMemberById(int memberId);
    Member findMemberByEmail(String email);
    Member findMemberByFirstname(String firstname);
    Member findMemberByLastname(String lastname);
    List<Member> findAllMembers();
}
