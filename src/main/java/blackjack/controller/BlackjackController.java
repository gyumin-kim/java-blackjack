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
        printGameParticipantsCards();
    }

    private void printGameParticipantsCards() {
        OutputView.printNewLine();
        OutputView.printInitializeDealMessage(dealer, players);
        OutputView.printInitialCards(dealer);
        players.forEach(OutputView::printInitialCards);
        OutputView.printNewLine();
    }

    public void play() {
        this.players.forEach(this::hitOrStay);
        int dealCount = dealer.dealHimself();
        if (dealerTookAdditionalCards(dealCount)) {
            OutputView.printDealerAddCardMessage(dealCount);
        }
    }

    // TODO: controller보다는 도메인 객체가 수행하는 것이 적절해 보임. (출력이 관건)
    private void hitOrStay(final Player player) {
        while (player.canHaveMoreCards() && InputView.askMoreCard(player)) {
            dealer.deal(player);
            OutputView.printCards(player);
        }
    }

    private boolean dealerTookAdditionalCards(final int dealCount) {
        return dealCount > 0;
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
