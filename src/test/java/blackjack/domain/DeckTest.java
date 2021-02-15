package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    @DisplayName("draw()를 호출하면 가장 앞의 Card를 꺼내온다")
    @Test
    void draw() {
        // given
        List<Card> cards = List.of(
                Card.of(Denomination.ACE, Suit.CLOVER),
                Card.of(Denomination.NINE, Suit.HEART),
                Card.of(Denomination.FIVE, Suit.DIAMOND),
                Card.of(Denomination.FOUR, Suit.SPADE),
                Card.of(Denomination.KING, Suit.HEART)
        );
        Deck deck = new Deck(cards);

        // when
        Card card1 = deck.draw();
        Card card2 = deck.draw();

        // then
        assertThat(card1).isEqualTo(Card.of(Denomination.ACE, Suit.CLOVER));
        assertThat(card2).isEqualTo(Card.of(Denomination.NINE, Suit.HEART));
    }
}
