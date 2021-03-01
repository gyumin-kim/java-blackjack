package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CardsJudgeTest {

    @DisplayName("Card들의 모든 수를 합산한 결과 계산")
    @Test
    void sumAllNumbers() {
        // given
        List<Card> cards = createCards();
        CardsJudge cardsJudge = new CardsJudge(cards);

        // when
        int score = cardsJudge.sumAllNumbers();

        // then
        assertThat(score).isEqualTo(21);
    }

    @DisplayName("Card들 중 ACE 카드가 있는지 판별")
    @Test
    void hasAce() {
        // given
        List<Card> cards = createCards();
        CardsJudge cardsJudge = new CardsJudge(cards);

        // when
        boolean hasAce = cardsJudge.hasAce();

        // then
        assertThat(hasAce).isTrue();
    }

    private List<Card> createCards() {
        return List.of(
                Card.of(Denomination.ACE, Suit.HEART),
                Card.of(Denomination.FIVE, Suit.CLOVER),
                Card.of(Denomination.THREE, Suit.DIAMOND),
                Card.of(Denomination.TWO, Suit.SPADE)
        );
    }
}