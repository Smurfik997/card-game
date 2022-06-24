package ua.kpi.cardgame.servlet;

import jakarta.servlet.http.HttpServletRequest;
import ua.kpi.cardgame.command.*;

import java.util.HashMap;

public class ControllerHelper {
    private static ControllerHelper instance;
    private HashMap<String, ICommand> commands = new HashMap<>();

    private ControllerHelper() {
        commands.put("/register", new RegisterCommand());
        commands.put("/login", new LoginCommand());
        commands.put("/logout", new LogoutCommand());
        commands.put("/main", new MainPageCommand());
        commands.put("/lobby", new LobbyPageCommand());

        commands.put("/api/cancelSearch", new SearchGameCommand());
        commands.put("/api/getSessionInfo", new GameSessionInfoCommand());
        commands.put("/api/makeChoice", new MakeChoiceCommand());
        commands.put("/api/searchGame", new SearchGameCommand());

        //Liza
        commands.put("/rating", new RatingPageCommand());
        commands.put("/admin", new AdminMainCommand());
        commands.put("/usersManager", new UserManagerCommand());
        commands.put("/searchUser", new SearchUserCommand());
        commands.put("/cardsManager", new CardManagerCommand());

        commands.put("/api/searchUser", new SearchUserCommand());
        commands.put("/api/promote", new PromoteCommand());
        commands.put("/api/ban", new BanCommand());
        commands.put("/api/unban", new UnbanCommand());
        commands.put("/changeCard", new ChangeCardPageCommand());
        commands.put("/api/changeCard", new ChangeCardCommand());
    }

    public static ControllerHelper getInstance() {
        if (instance == null) {
            instance = new ControllerHelper();
        }

        return instance;
    }

    public ICommand getCommand(HttpServletRequest req) {
        if (req.getParameter("command") == null) {
            System.out.println("Current command: "+req.getRequestURI());
            return commands.get(req.getRequestURI());
        } else {
            return commands.get(req.getParameter("command"));
        }
    }
}
