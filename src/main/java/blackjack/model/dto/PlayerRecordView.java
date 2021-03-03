package blackjack.model.dto;

public class PlayerRecordView {
    private final String name;
    private final String record;

    public PlayerRecordView(final String name, final String record) {
        this.name = name;
        this.record = record;
    }

    public String getName() {
        return name;
    }

    public String getRecord() {
        return record;
    }
}
