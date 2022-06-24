package ua.kpi.cardgame.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kpi.cardgame.entities.User;
import ua.kpi.cardgame.services.ServiceFactory;
import ua.kpi.cardgame.services.UserService;

import java.util.List;


public class SearchUserCommand implements ICommand{
    private final static String USER_MANAGER_PAGE = "/usersManager.jsp";
    private final static String REDIRECT_USER_MANAGER_PAGE = "redirect:/usersManager";
    private final static String MAIN_PAGE = "redirect:/main";
    private final static String AUTH_PAGE = "redirect:/login";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String auth = Authorization.checkAdmin(req, resp);
        if (auth != null)
            return auth;

        String username = req.getParameter("username");

        UserService userService = ServiceFactory.getUserService();
        User user = userService.searchByUsername(username);
        System.out.println("FOUND USER:" + user);


        if (user == null) {
            req.setAttribute("message", "No user found by this username :(");
            return new UserManagerCommand().execute(req, resp);
        }

        System.out.println("FOUND USER:" + user);
        String role = userService.getUserRole(user.getUserId());

        req.setAttribute("foundUser", user);
        req.setAttribute("role", role);

        return new UserManagerCommand().execute(req, resp);
    }
}
