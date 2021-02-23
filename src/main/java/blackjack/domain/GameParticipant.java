package blackjack.domain;

import java.util.List;

// TODO: rename class
public interface GameParticipant {

    List<Card> getCardsBeforeGameStart();

    String getName();

    List<Card> getCards();

    int getResult();
}
