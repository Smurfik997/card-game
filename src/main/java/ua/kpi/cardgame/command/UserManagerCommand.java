package ua.kpi.cardgame.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kpi.cardgame.entities.User;
import ua.kpi.cardgame.services.ServiceFactory;
import ua.kpi.cardgame.services.UserService;

import java.util.List;

public class UserManagerCommand implements ICommand{
    private final static String USER_MANAGER_PAGE = "/usersManager.jsp";
    private final static String MAIN_PAGE = "redirect:/main.jsp";
    private final static String AUTH_PAGE = "redirect:/login";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String auth = Authorization.checkAdmin(req, resp);
        if (auth != null)
            return auth;

        UserService userService = ServiceFactory.getUserService();

        List<User> users = userService.getAll().stream().filter(u -> userService.isAdmin(u.getUserId())).toList();
        req.setAttribute("users", users);

        return USER_MANAGER_PAGE;
    }
}
