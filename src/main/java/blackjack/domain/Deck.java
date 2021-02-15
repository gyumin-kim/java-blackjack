package blackjack.domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {
    private final LinkedList<Card> cards;

    public Deck(final List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public static Deck withShuffledCards() {
        LinkedList<Card> cards = Card.getAllCards();
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    public Card draw() {
        return this.cards.pop();
    }
}
