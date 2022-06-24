package ua.kpi.cardgame.dao.interfaces;

import java.sql.SQLException;

public interface IRoleDAO {
    String getUserRole(int userId) throws SQLException;


    void promoteUser(int id) throws SQLException;
}
