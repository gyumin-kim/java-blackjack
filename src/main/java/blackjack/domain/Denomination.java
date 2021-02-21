package blackjack.domain;

public enum Denomination {
    // TODO: A, J, Q, K는 알파벳으로 리턴하도록 수정
    ACE(1), // TODO: 1 or 11
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ;

    private final int number;

    Denomination(final int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
