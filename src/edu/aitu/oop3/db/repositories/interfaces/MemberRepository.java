package edu.aitu.oop3.db.repositories.interfaces;

import edu.aitu.oop3.db.entities.Member;

import java.util.List;

public interface MemberRepository {
    Member findMemberById(int memberId);
}
