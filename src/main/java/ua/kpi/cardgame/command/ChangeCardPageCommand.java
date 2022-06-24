package ua.kpi.cardgame.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kpi.cardgame.entities.Card;
import ua.kpi.cardgame.services.CardService;
import ua.kpi.cardgame.services.ServiceFactory;

import java.util.List;

public class ChangeCardPageCommand implements ICommand{
    private final static String CARD_MANAGER_PAGE = "/cardsManager.jsp";
    private final static String CHANGE_CARD_PAGE = "/changeCard.jsp";
    private final static String MAIN_PAGE = "redirect:/main.jsp";
    private final static String AUTH_PAGE = "redirect:/login";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String auth = Authorization.checkAdmin(req, resp);
        if (auth != null)
            return auth;

        CardService cardService = ServiceFactory.getCardService();
        int cardId = Integer.parseInt(req.getParameter("id"));

        req.setAttribute("cardId", cardId);

        req.setAttribute("card", cardService.getById(cardId));

        return CHANGE_CARD_PAGE;
    }
}
