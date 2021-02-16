package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.Dealer;
import blackjack.domain.DealerRecord;
import blackjack.domain.Player;
import blackjack.domain.PlayersRecord;
import blackjack.dto.PlayerRecordView;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {
    private BlackjackGame blackjackGame;

    public void start() {
        List<Player> players = InputView.getPlayers();
        blackjackGame = new BlackjackGame(players);
        blackjackGame.initializeDeal();
        OutputView.printNewLine();
        printGameParticipantsCards();
    }

    private void printGameParticipantsCards() {
        Dealer dealer = blackjackGame.getDealer();
        List<Player> players = blackjackGame.getPlayers();
        OutputView.printInitializeDealMessage(dealer, players);
        OutputView.beforePrintCards(dealer);
        players.forEach(OutputView::beforePrintCards);
        OutputView.printNewLine();
    }

    public void askHitOrStayToPlayers() {
        List<Player> players = blackjackGame.getPlayers();
        for (final Player player : players) {
            hitOrStay(player);
        }
        OutputView.printNewLine();
    }

    private void hitOrStay(final Player player) {
        final int BLACKJACK_CONDITION = 21;
        while (player.getResult() < BLACKJACK_CONDITION && InputView.askMoreCard(player)) {
            blackjackGame.giveCard(player);
            OutputView.printCards(player);
            OutputView.printNewLine();
        }
    }

    public void dealerTurn() {
        Dealer dealer = blackjackGame.getDealer();
        if (dealer.isActive()) {
            OutputView.printDealerAddCardMessage();
            blackjackGame.giveCard(dealer);
        }
    }

    // TODO: 복잡도 낮추기
    public void releaseResult() {
        Dealer dealer = blackjackGame.getDealer();
        List<Player> players = blackjackGame.getPlayers();

        OutputView.printGameParticipantResultMessage(dealer);
        for (Player player : players) {
            OutputView.printGameParticipantResultMessage(player);
        }

        DealerRecord dealerRecord = new DealerRecord(dealer, players);
        OutputView.printDealerGameResult(dealerRecord);
        PlayersRecord playersRecord = new PlayersRecord(dealer, players);
        List<PlayerRecordView> playerRecordViews = playersRecord.getPlayerRecordViews();
        for (PlayerRecordView playerRecordView : playerRecordViews) {
            OutputView.printPlayerGameResult(playerRecordView);
        }
    }
}
