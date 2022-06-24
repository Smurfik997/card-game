package ua.kpi.cardgame.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kpi.cardgame.services.ServiceFactory;
import ua.kpi.cardgame.services.UserService;

public class UnbanCommand implements ICommand{
    private final static String USER_MANAGER_PAGE = "redirect:/usersManager.jsp";
    private final static String MAIN_PAGE = "redirect:/main.jsp";
    private final static String AUTH_PAGE = "redirect:/login";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String auth = Authorization.checkAdmin(req, resp);
        if (auth != null)
            return auth;

        int id = Integer.parseInt(req.getParameter("id"));
        UserService userService = ServiceFactory.getUserService();
        userService.unbanUser(id);

        return new UserManagerCommand().execute(req, resp);
    }
}

