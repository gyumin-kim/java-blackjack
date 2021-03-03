package blackjack.model.domain;

import java.util.List;

public class DealerRecord {
    private final Dealer dealer;
    private final List<Player> players;
    private int win;
    private int draw;
    private int lose;

    public DealerRecord(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = players;
        createRecord();
    }

    public void createRecord() {
        int dealerScore = dealer.getScore();
        players.stream()
                .mapToInt(Player::getScore)
                .forEach(playerScore -> winOrLose(dealerScore, playerScore));
    }

    // TODO: PlayersScore의 winOrLose()와 비슷하므로 각각 따로 둘 필요 없을 듯
    private void winOrLose(int dealerScore, int playerScore) {
        if (playerScore > 21 || (dealerScore > playerScore && dealerScore <= 21)) {
            this.win++;
            return;
        }
        if (dealerScore == playerScore && playerScore != 21) {
            this.draw++;
            return;
        }
        this.lose++;
    }

    public int getWin() {
        return win;
    }

    public int getDraw() {
        return draw;
    }

    public int getLose() {
        return lose;
    }
}
