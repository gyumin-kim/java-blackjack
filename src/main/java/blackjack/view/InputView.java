package blackjack.view;

import blackjack.model.domain.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    public static List<Player> getPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] names = input.split(",");
        return Arrays.stream(names)
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public static boolean askMoreCard(Player player) {
        String name = player.getName();
        System.out.println(name + "은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("y");
    }
}
