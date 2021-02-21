package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO: GameParticipant 인터페이스 필요성에 대해 재검토
public class Dealer implements GameParticipant {
    private static final String NAME = "딜러";
    private static final int CRITERIA = 17;

    private final Deck deck;
    private final List<Card> cards = new ArrayList<>();

    public Dealer(final Deck deck) {
        this.deck = deck;
    }

    /**
     * 플레이어와 자신에게 deal(카드를 분배)한다.
     *
     * @param players 1명 이상의 플레이어
     */
    public void dealsCardsInitially(final List<Player> players) {
        for (Player player : players) {
            player.hit(this.deck.draw());
            player.hit(this.deck.draw());
        }
        this.cards.add(deck.draw());
        this.cards.add(deck.draw());
    }

    @Override
    public void hit(Card card) {
        this.cards.add(card);
    }

    @Override
    public List<Card> getCardsBeforeGameStart() {
        return Collections.singletonList(this.cards.get(0));
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public List<Card> getCards() {
        return this.cards;
    }

    @Override
    public int getResult() {
        int score = 0;
        boolean hasAce = false;
        for (Card card : cards) {
            int cardNumber = card.getDenomination().getNumber();
            score += cardNumber;
            if (cardNumber == 1) {
                hasAce = true;
            }
        }
        if (hasAce && score + 10 <= 21) {
            score += 10;
        }
        return score;
    }

    public void deal(final Player player) {
        player.hit(this.deck.draw());
    }

    public int dealHimself() {
        int dealCount = 0;
        while (getResult() < CRITERIA) {
            this.cards.add(deck.draw());
            dealCount++;
        }
        return dealCount;
    }
}
