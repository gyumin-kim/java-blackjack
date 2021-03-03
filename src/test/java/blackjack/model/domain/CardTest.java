package blackjack.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @DisplayName("of()로 생성")
    @Test
    void createCard() {
        // given
        Denomination denomination = Denomination.ACE;
        Suit suit = Suit.CLOVER;

        // when
        Card card = Card.of(denomination, suit);

        // then
        assertThat(card.getDenomination()).isEqualTo(denomination);
        assertThat(card.getSuit()).isEqualTo(suit);
    }

    @DisplayName("Denomination & Suit 조합이 같으면 동일 객체")
    @Test
    void identity() {
        // given & when
        Card card1 = Card.of(Denomination.ACE, Suit.HEART);
        Card card2 = Card.of(Denomination.ACE, Suit.HEART);

        // then
        assertThat(card1).isEqualTo(card2);
    }
}
