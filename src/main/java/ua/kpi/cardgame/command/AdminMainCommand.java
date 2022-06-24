package ua.kpi.cardgame.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kpi.cardgame.entities.User;
import ua.kpi.cardgame.services.ServiceFactory;
import ua.kpi.cardgame.services.UserService;

public class AdminMainCommand implements ICommand {
    private final static String AdminPage = "/adminMain.jsp";
    private final static String MAIN_PAGE = "redirect:/main.jsp";
    private final static String AUTH_PAGE = "redirect:/login";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getSession().getAttribute("user") == null) {
            return AUTH_PAGE;
        }

        UserService userService = ServiceFactory.getUserService();
        User currentUser = (User) req.getSession().getAttribute("user");

        if (userService.isAdmin(currentUser.getUserId()))
            return MAIN_PAGE;

        return AdminPage;
    }
}
