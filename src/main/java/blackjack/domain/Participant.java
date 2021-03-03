package blackjack.domain;

import java.util.List;

public interface Participant {

    List<Card> getCardsBeforeGameStart();

    String getName();

    List<Card> getCards();

    int getResult();
}
