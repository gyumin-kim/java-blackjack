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
    private final List<Player> players; // TODO: 일급 컬렉션 필요할지 고민

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
        OutputView.printNewLine();
        printGameParticipantsCards();
    }

    private void printGameParticipantsCards() {
        OutputView.printInitializeDealMessage(dealer, players);
        OutputView.beforePrintCards(dealer);
        players.forEach(OutputView::beforePrintCards);
        OutputView.printNewLine();
    }

    public void play() {
        for (final Player player : players) {
            hitOrStay(player);
        }
        OutputView.printNewLine();
        int dealCount = dealer.dealHimself();
        if (dealCount > 0) {
            OutputView.printDealerAddCardMessage(dealCount);
        }
    }

    private void hitOrStay(final Player player) {
        final int BLACKJACK_CONDITION = 21;
        while (player.getResult() < BLACKJACK_CONDITION && InputView.askMoreCard(player)) {
            dealer.deal(player);
            OutputView.printCards(player);
            OutputView.printNewLine();
        }
    }

    // TODO: 복잡도 낮추기
    public void announceResult() {
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
