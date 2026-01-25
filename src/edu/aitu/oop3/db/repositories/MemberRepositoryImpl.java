package edu.aitu.oop3.db.repositories;

import edu.aitu.oop3.db.entities.Member;
import edu.aitu.oop3.db.db.IDB;
import edu.aitu.oop3.db.repositories.interfaces.MemberRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRepositoryImpl  implements MemberRepository {
    private final IDB db;
    public MemberRepositoryImpl(IDB db) {
        this.db = db;
    }
    @Override
    public Member findMemberById(int memberId) {
        String sql = "SELECT * FROM members WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Member(
                            rs.getInt("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding by id",e);
        }

        return null;
    }}
