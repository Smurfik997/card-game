package ua.kpi.cardgame.command;

import org.json.simple.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kpi.cardgame.entities.Card;
import ua.kpi.cardgame.entities.User;
import ua.kpi.cardgame.services.CardService;
import ua.kpi.cardgame.services.ServiceFactory;
import ua.kpi.cardgame.services.UserService;

import java.util.List;

public class CardManagerCommand implements ICommand {
    private final static String CARD_MANAGER_PAGE = "/cardsManager.jsp";
    private final static String MAIN_PAGE = "redirect:/main.jsp";
    private final static String AUTH_PAGE = "redirect:/login";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String auth = Authorization.checkAdmin(req, resp);
        if (auth != null)
            return auth;

        CardService cardService = ServiceFactory.getCardService();

        List<Card> cards = cardService.getAll();
        System.out.println("CARDS AMOUNT: "+cards.size());

        req.setAttribute("cards", cards);

        return CARD_MANAGER_PAGE;
    }
}
