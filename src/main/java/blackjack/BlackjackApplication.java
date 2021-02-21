package blackjack;

import blackjack.controller.BlackjackController;

public class BlackjackApplication {

    public static void main(String[] args) {
        BlackjackController blackjackController = BlackjackController.init();
        blackjackController.start();
        blackjackController.play();
        blackjackController.announceResult();
    }
}
