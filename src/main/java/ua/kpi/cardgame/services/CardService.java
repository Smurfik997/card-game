package ua.kpi.cardgame.services;

import ua.kpi.cardgame.dao.DAOFactory;
import ua.kpi.cardgame.dao.interfaces.ICardDAO;
import ua.kpi.cardgame.dao.interfaces.IUserDAO;
import ua.kpi.cardgame.dao.interfaces.IUserOnlineDAO;
import ua.kpi.cardgame.entities.Card;
import ua.kpi.cardgame.entities.CardType;
import ua.kpi.cardgame.entities.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardService {

    public List<Card> getAll() {
        ICardDAO cardDAO = DAOFactory.getCardDAO();
        List<Card> cards = new ArrayList<>();

        try {
            cards = cardDAO.getAllCards().stream().
                    filter(Objects::nonNull).filter(card  -> card.getType() == CardType.TEXT).toList();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cards;
    }

    public Card getById(int id) {
        ICardDAO cardDAO = DAOFactory.getCardDAO();
        Card card = null;

        try {
            card = cardDAO.getCardById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return card;
    }

    public void updateCardResource(int id, String resource) {
        ICardDAO cardDAO = DAOFactory.getCardDAO();

        try {
            Card card = cardDAO.getCardById(id);
            cardDAO.updateCardResource(card, resource);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
