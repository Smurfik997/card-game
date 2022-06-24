package ua.kpi.cardgame.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kpi.cardgame.services.CardService;
import ua.kpi.cardgame.services.ServiceFactory;

public class ChangeCardCommand implements ICommand{
    private final static String CARD_MANAGER_PAGE = "redirect:/cardsManager";
    private final static String CHANGE_CARD_PAGE = "/changeCard.jsp";
    private final static String MAIN_PAGE = "redirect:/main";
    private final static String AUTH_PAGE = "redirect:/login";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String auth = Authorization.checkAdmin(req, resp);
        if (auth != null)
            return auth;

        CardService cardService = ServiceFactory.getCardService();
        int cardId = Integer.parseInt(req.getParameter("cardId"));
        String cardText = req.getParameter("cardText");
        System.out.println("Changing card by id "+cardId+" to "+cardText);

        cardService.updateCardResource(cardId, cardText);
        return CARD_MANAGER_PAGE;
    }
}
