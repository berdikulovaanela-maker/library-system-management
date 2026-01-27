package edu.aitu.oop3.db.repositories;

import edu.aitu.oop3.db.entities.Member;
import edu.aitu.oop3.db.db.IDB;
import edu.aitu.oop3.db.repositories.interfaces.MemberRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberRepositoryImpl  implements MemberRepository {
    private final IDB db;

    public MemberRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public Member findById(Integer memberId) {
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
    }

    @Override
    public List<Member> findAll() {
        String sql = "SELECT * FROM members";
        List<Member> members = new ArrayList<>();
        try(Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Member member = new Member(rs.getInt("id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("email") );
                members.add(member);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Error finding all members",e);
        }
        return members;
    }

    @Override
    public void save(Member entity) {
        String sql = "INSERT INTO members (first_name, last_name, email) VALUES (?, ?, ?)";
        try(Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getEmail());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                entity.setId(rs.getInt(1));
            }
        }
        catch(SQLException e) {
            throw new RuntimeException("Error saving members",e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM members WHERE id = ?";
        try(Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.executeUpdate();
        }
        catch(SQLException e) {
            throw new RuntimeException("Error deleting members",e);
        }
    }
}

