package ua.kpi.cardgame.dao.impl;

import ua.kpi.cardgame.dao.interfaces.IRoleDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PSQLRoleDAO implements IRoleDAO
{
    private final PSQLController controller;

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_ROLE_ID = "role_id";

    private static final long adminRoleID = 1;
    private static final long userRoleID = 2;


    public PSQLRoleDAO () {
        controller = PSQLController.getInstance();
    }


    @Override
    public String getUserRole(int userId) throws SQLException {
        PreparedStatement ps = controller.getPreparedStatement("SELECT role_id FROM cardGame.user_role " + " WHERE user_id = ?");
        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();

        int roleId = 0;
        String roleName = null;

        if (rs.next()) {
            roleId = rs.getInt(COLUMN_ROLE_ID);
        }

        rs.close();
        ps.close();

        if (roleId != 0){
            ps = controller.getPreparedStatement("SELECT name FROM cardGame.roles " + " WHERE role_id = ?");
            ps.setInt(1, roleId);

            rs = ps.executeQuery();

            if (rs.next()) {
                roleName = rs.getString("name");
            }
        }

        return roleName;
    }

    @Override
    public void promoteUser(int id) throws SQLException {
        PreparedStatement ps = controller.getPreparedStatement("DELETE FROM cardGame.user_role " + " WHERE user_id = ?");
        ps.setInt(1, id);

        ps.executeUpdate();
        ps.close();

        ps = controller.getPreparedStatement("INSERT INTO cardgame.user_role(user_id, role_id) VALUES (? , ?)");

        ps.setInt(1, id);
        ps.setInt(2, 1);

        ps.executeUpdate();
        ps.close();
    }
}
