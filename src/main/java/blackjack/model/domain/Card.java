package blackjack.model.domain;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;

public class Card {
    private static final int TOTAL_NUMBER = 52;
    private static final Card[] CARDS = new Card[TOTAL_NUMBER];

    static {
        int i = 0;
        for (final Denomination denomination : Denomination.values()) {
            i = assignCard(i, denomination);
        }
    }

    private static int assignCard(int index, final Denomination denomination) {
        for (final Suit suit : Suit.values()) {
            CARDS[index++] = new Card(denomination, suit);
        }
        return index;
    }

    private final Denomination denomination;
    private final Suit suit;

    private Card(final Denomination denomination, final Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public static Card of(final Denomination denomination, final Suit suit) {
        Optional<Card> cardOptional = Arrays.stream(CARDS)
                .filter(card -> card.denomination == denomination)
                .filter(card -> card.suit == suit)
                .findAny();
        return cardOptional.orElseThrow(() -> new IllegalArgumentException("유효한 조합이 아닙니다."));
    }

    public static LinkedList<Card> getAllCards() {
        return new LinkedList<>(Arrays.asList(CARDS));
    }

    public Denomination getDenomination() {
        return this.denomination;
    }

    public Suit getSuit() {
        return this.suit;
    }
}
