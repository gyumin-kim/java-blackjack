package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.DealerRecord;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.domain.PlayersRecord;
import blackjack.dto.PlayerRecordView;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {
    private final Dealer dealer;
    private final List<Player> players;

    private BlackjackController(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackjackController init() {
        Dealer dealer = new Dealer(Deck.withShuffledCards());
        List<Player> players = InputView.getPlayers();
        return new BlackjackController(dealer, players);
    }

    public void start() {
        dealer.dealsCardsInitially(players);
        printParticipantsCards();
    }

    private void printParticipantsCards() {
        OutputView.printNewLine();
        OutputView.printInitializeDealMessage(dealer, players);
        OutputView.printInitialCards(dealer);
        players.forEach(OutputView::printInitialCards);
        OutputView.printNewLine();
    }

    public void play() {
        this.players.forEach(this::dealUntilPlayerSaysNo);
        int dealCount = dealer.dealHimself();
        if (dealerTookAdditionalCards(dealCount)) {
            OutputView.printDealerAddCardMessage(dealCount);
        }
    }

    private void dealUntilPlayerSaysNo(final Player player) {
        while (player.canHaveMoreCards() && InputView.askMoreCard(player)) {
            dealer.deal(player);
            OutputView.printCards(player);
            OutputView.printNewLine();
        }
    }

    private boolean dealerTookAdditionalCards(final int dealCount) {
        return dealCount > 0;
    }

    public void announceResult() {
        OutputView.printParticipantResultMessage(dealer, players);
        DealerRecord dealerRecord = new DealerRecord(dealer, players);
        OutputView.printDealerGameResult(dealerRecord);
        PlayersRecord playersRecord = new PlayersRecord(dealer, players);
        List<PlayerRecordView> playerRecordViews = playersRecord.getPlayerRecordViews();
        for (PlayerRecordView playerRecordView : playerRecordViews) {
            OutputView.printPlayerGameResult(playerRecordView);
        }
    }
}
