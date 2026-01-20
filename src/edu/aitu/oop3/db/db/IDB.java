package edu.aitu.oop3.db.db;

import java.sql.Connection;
import java.sql.SQLException;
public interface IDB {
    Connection getConnection() throws SQLException;
}
