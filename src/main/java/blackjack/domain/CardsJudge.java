package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class CardsJudge {

    private static final int TEN = 10;
    private static final int BLACKJACK = 21;
    private final List<Card> cards;

    public CardsJudge(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int sumAllNumbers() {
        int sum = this.cards.stream()
                .map(Card::getDenomination)
                .mapToInt(Denomination::getNumber)
                .sum();
        if (hasAce() && ableToAddAceOfEleven(sum)) {
            sum += TEN;
        }
        return sum;
    }

    private boolean ableToAddAceOfEleven(final int sum) {
        return sum + TEN <= BLACKJACK;
    }

    public boolean hasAce() {
        return this.cards.stream()
                .map(Card::getDenomination)
                .anyMatch(d -> d == Denomination.ACE);
    }
}
