package blackjack.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @DisplayName("게임 시작: 딜러 자신과 모든 플레이어에게 카드를 2장씩 deal")
    @Test
    void dealsCardsInitially() {
        // given
        Dealer dealer = new Dealer(Deck.withShuffledCards());
        List<Player> players = List.of(
                new Player("Pobi"),
                new Player("Jason"),
                new Player("Gyumin")
        );

        // when
        dealer.dealsCardsInitially(players);

        // then
        final int INITIAL_CARDS_COUNT = 2;
        assertThat(players.get(0).getCards()).hasSize(INITIAL_CARDS_COUNT);
        assertThat(players.get(1).getCards()).hasSize(INITIAL_CARDS_COUNT);
        assertThat(players.get(2).getCards()).hasSize(INITIAL_CARDS_COUNT);
        assertThat(dealer.getCards()).hasSize(INITIAL_CARDS_COUNT);
    }

    @DisplayName("딜러가 플레이어에게 카드 1장을 deal")
    @Test
    void deal() {
        // given
        Dealer dealer = new Dealer(Deck.withShuffledCards());
        Player player = new Player("Jason");
        int initialSize = player.getCards().size();

        // when
        dealer.deal(player);

        // then
        assertThat(player.getCards()).hasSize(initialSize + 1);
    }

    @DisplayName("딜러는 합이 17 이상이 될 때까지 카드를 뽑는다")
    @Test
    void dealHimself() {
        // given
        Deck deck = new Deck(createCards());
        Dealer dealer = new Dealer(deck);

        // when
        int dealCount = dealer.dealHimself();

        // then
        assertThat(dealCount).isEqualTo(4);
    }

    private List<Card> createCards() {
        return List.of(
                Card.of(Denomination.NINE, Suit.SPADE),
                Card.of(Denomination.THREE, Suit.DIAMOND),
                Card.of(Denomination.FOUR, Suit.HEART),
                Card.of(Denomination.ACE, Suit.CLOVER),
                Card.of(Denomination.KING, Suit.CLOVER),
                Card.of(Denomination.SIX, Suit.SPADE)
        );
    }
}