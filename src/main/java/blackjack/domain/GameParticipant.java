package blackjack.domain;

import java.util.List;

// TODO: rename class
public interface GameParticipant {
    void hit(Card card);

    List<Card> getCardsBeforeGameStart();

    String getName();

    List<Card> getCards();

    int getResult();
}
