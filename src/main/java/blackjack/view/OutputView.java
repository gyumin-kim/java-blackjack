package blackjack.view;

import blackjack.model.domain.Card;
import blackjack.model.domain.Dealer;
import blackjack.model.domain.DealerRecord;
import blackjack.model.domain.Participant;
import blackjack.model.domain.Player;
import blackjack.model.dto.PlayerRecordView;

import java.util.ArrayList;
import java.util.List;

public class OutputView {

    public static void printNewLine() {
        System.out.println();
    }

    public static void printInitializeDealMessage(final Dealer dealer, final List<Player> players) {
        StringBuilder output = new StringBuilder();
        String dealerName = dealer.getName();
        output.append(dealerName)
                .append("와 ");
        for (final Player player : players) {
            output.append(player.getName());
            output.append(", ");
        }
        output.delete(output.lastIndexOf(", "), output.length());
        output.append("에게 각각 2장의 카드를 나누었습니다.");
        System.out.println(output.toString());
    }

    public static void printInitialCards(final Participant participant) {
        String name = participant.getName();
        System.out.print(name + " 카드: ");

        StringBuilder cardsContent = new StringBuilder();
        List<Card> cards = participant.getCardsBeforeGameStart();
        for (final Card card : cards) {
            cardsContent.append(card.getDenomination().getSymbol())
                    .append(card.getSuit().getName())
                    .append(", ");
        }
        cardsContent.delete(cardsContent.lastIndexOf(", "), cardsContent.length() - 1);
        System.out.println(cardsContent.toString());
    }

    public static void printDealerAddCardMessage(final int dealCount) {
        System.out.println();
        System.out.println("딜러는 16 이하라 " + dealCount + "장의 카드를 더 받았습니다.");
    }

    public static void printCards(final Participant participant) {
        String name = participant.getName();
        System.out.print(name + " 카드: ");

        StringBuilder cardsContent = new StringBuilder();
        List<Card> cards = participant.getCards();
        for (final Card card : cards) {
            cardsContent.append(card.getDenomination().getSymbol())
                    .append(card.getSuit().getName())
                    .append(", ");
        }
        cardsContent.delete(cardsContent.lastIndexOf(", "), cardsContent.length() - 1);
        System.out.print(cardsContent.toString());
    }

    public static void printParticipantResultMessage(final Dealer dealer, final List<Player> players) {
        System.out.println();
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);
        for (final Participant participant : participants) {
            printCards(participant);
            System.out.print("- 결과: " + participant.getResult());
            System.out.println();
        }
        System.out.println();
    }

    public static void printDealerGameResult(final DealerRecord dealerRecord) {
        System.out.println("딜러: " + dealerRecord.getWin() + "승 " + dealerRecord.getDraw() + "무 " + dealerRecord.getLose() + "패");
    }

    public static void printPlayerGameResult(final PlayerRecordView playerRecordView) {
        System.out.println(playerRecordView.getName() + ": " + playerRecordView.getRecord());
    }
}
