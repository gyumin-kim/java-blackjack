package blackjack.model.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

// TODO: Participant 인터페이스 필요성에 대해 재검토
public class Dealer implements Participant {
    private static final String NAME = "딜러";
    private static final int CRITERIA = 17;

    private final Deck deck;
    private final List<Card> cards = new ArrayList<>();

    public Dealer(final Deck deck) {
        this.deck = deck;
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
    public int getScore() {
        Cards cards = new Cards(this.cards);
        return cards.sumAllNumbers();
    }

    /**
     * 플레이어와 자신에게 deal(카드를 분배)한다.
     *
     * @param players 1명 이상의 플레이어
     */
    public void dealsCardsInitially(final List<Player> players) {
        IntStream.range(0, 2).forEach(i -> {
            players.forEach(player -> player.hit(this.deck.draw()));
            this.cards.add(deck.draw());
        });
    }

    public int dealHimself() {
        int dealCount = 0;
        while (getScore() < CRITERIA) {
            this.cards.add(deck.draw());
            dealCount++;
        }
        return dealCount;
    }

    public void deal(final Player player) {
        player.hit(this.deck.draw());
    }
}
