package blackjack.model.domain;

import blackjack.model.dto.PlayerRecordView;

import java.util.ArrayList;
import java.util.List;

// TODO: add test
public class PlayersRecord {
    private final Dealer dealer;
    private final List<Player> players;
    private final List<PlayerRecordView> playerRecordViews = new ArrayList<>();

    public PlayersRecord(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = players;
        createPlayerRecordViews();
    }

    private void createPlayerRecordViews() {
        int dealerScore = dealer.getResult();
        for (final Player player : players) {
            int playerScore = player.getResult();
            String result = winOrLose(dealerScore, playerScore);
            playerRecordViews.add(new PlayerRecordView(player.getName(), result));
        }
    }

    private String winOrLose(final int dealerScore, final int playerScore) {
        if ((playerScore < dealerScore && dealerScore <= 21) || playerScore > 21) {
            return "패";
        }
        if (playerScore == dealerScore) {
            return "무";
        }
        return "승";
    }

    public List<PlayerRecordView> getPlayerRecordViews() {
        return playerRecordViews;
    }
}
