package blackjack.model.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player implements Participant {
    private static final int BLACKJACK_CONDITION = 21;

    private final String name;
    private final List<Card> cards = new ArrayList<>();

    public Player(final String name) {
        validateNonEmptyName(name);
        this.name = name;
    }

    private void validateNonEmptyName(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름으로 사용할 수 없습니다.");
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Card> getCards() {
        return this.cards;
    }

    @Override
    public List<Card> getCardsBeforeGameStart() {
        return Arrays.asList(cards.get(0), cards.get(1));
    }

    @Override
    public int getScore() {
        Cards cards = new Cards(this.cards);
        return cards.sumAllNumbers();
    }

    public void hit(Card card) {
        this.cards.add(card);
    }

    public boolean canHaveMoreCards() {
        return this.getScore() < BLACKJACK_CONDITION;
    }
}
