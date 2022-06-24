package ua.kpi.cardgame.services;

import ua.kpi.cardgame.dao.DAOFactory;
import ua.kpi.cardgame.dao.interfaces.IRoleDAO;
import ua.kpi.cardgame.dao.interfaces.IUserDAO;
import ua.kpi.cardgame.dao.interfaces.IUserOnlineDAO;
import ua.kpi.cardgame.entities.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class UserService {
    public User register(String login, String password) {
        IUserDAO userDAO = DAOFactory.getUserDAO();
        IUserOnlineDAO userOnlineDAO = DAOFactory.getUserOnlineDAO();
        User user = null;

        try {
            if (userDAO.getUserByLogin(login) == null) {
                user = userDAO.createUser(login, password);
                userOnlineDAO.setUserOnline(user.getUserId());
                userDAO.commitTransaction();
            } else {
                return null;
            }
        } catch (SQLException e) {
            userDAO.rollbackTransaction();
            e.printStackTrace();
        }

        return user;
    }

    public User login(String login, String password) {
        IUserDAO userDAO = DAOFactory.getUserDAO();
        IUserOnlineDAO userOnlineDAO = DAOFactory.getUserOnlineDAO();
        User user = null;

        try {
            user = userDAO.getUserByLogin(login);
            if (user != null && !password.equals(user.getPassword())) {
                user = null;
            } else if (user != null) {
                userOnlineDAO.setUserOnline(user.getUserId());
                userOnlineDAO.commitTransaction();
            }
        } catch (SQLException e) {
            userOnlineDAO.rollbackTransaction();
            e.printStackTrace();
        }

        return user;
    }

    public void logout(User user) {
        IUserOnlineDAO userOnlineDAO = DAOFactory.getUserOnlineDAO();

        try {
            userOnlineDAO.setUserOffline(user.getUserId());
            userOnlineDAO.commitTransaction();
        } catch (SQLException e) {
            userOnlineDAO.rollbackTransaction();
            e.printStackTrace();
        }
    }

    public void setUserOnline(User user) {
        IUserOnlineDAO userOnlineDAO = DAOFactory.getUserOnlineDAO();

        try {
            userOnlineDAO.setUserOnline(user.getUserId());
            userOnlineDAO.commitTransaction();
        } catch (SQLException e) {
            // pass
        }
    }

    public List<User> getAllOnline() {
        IUserDAO userDAO = DAOFactory.getUserDAO();
        IUserOnlineDAO userOnlineDAO = DAOFactory.getUserOnlineDAO();
        List<User> users = new ArrayList<>();

        try {
            users = userOnlineDAO.getAllOnlineUsers().stream().
                    filter(userOnline -> new Date().getTime() - userOnline.getTimestamp().getTime() < 30000).
                    map(userOnline -> {
                        try {
                            return userDAO.getUserById(userOnline.getUserId());
                        } catch (SQLException e) {
                            return null;
                        }
                    }).
                    filter(Objects::nonNull).
                    sorted((User u1, User u2) -> u2.getRate() - u1.getRate()).toList();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public List<User> getAll() {
        IUserDAO userDAO = DAOFactory.getUserDAO();
        List<User> users = new ArrayList<>();

        try {
            users = userDAO.getAllUsers().stream().
                    filter(Objects::nonNull).
                    sorted((User u1, User u2) -> u2.getRate() - u1.getRate()).toList();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public String getUserRole(int userId) {
        IRoleDAO roleDAO = DAOFactory.getRoleDAO();
        String role = null;

        try {
            role = roleDAO.getUserRole(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }

    public boolean isAdmin(int userId) {
        String role = getUserRole(userId);

        if (role != null)
            return !role.equals("admin");
        return true;
    }

    public User searchByUsername(String username) {
        IUserDAO userDAO = DAOFactory.getUserDAO();
        User user = null;

        try {
            user = userDAO.getUserByLogin(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public void promoteUser(int id) {
        IRoleDAO roleDAO = DAOFactory.getRoleDAO();

        try {
            roleDAO.promoteUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void banUser(int id) {
        IUserDAO userDAO = DAOFactory.getUserDAO();

        try {
            userDAO.banUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void unbanUser(int id) {
        IUserDAO userDAO = DAOFactory.getUserDAO();

        try {
            userDAO.unbanUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
