package blackjack.domain;

import java.util.List;

public class BlackjackGame {
    private final List<Player> players;
    private final Dealer dealer = new Dealer();
    private final Deck deck = Deck.withShuffledCards();

    public BlackjackGame(List<Player> players) {
        this.players = players;
    }

    public void initializeDeal() {
        for (Player player : this.players) {
            giveCard(player);
            giveCard(player);
        }
        giveCard(this.dealer);
        giveCard(this.dealer);
    }

    public void giveCard(GameParticipant gameParticipant) {
        Card drawnCard = deck.draw();
        gameParticipant.hit(drawnCard);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
