package blackjack.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerTest {

    @DisplayName("name으로 Player 생성")
    @Test
    void createPlayerTest() {
        // given
        final String name = "jason";

        // when
        Player player = new Player(name);

        // then
        assertThat(player.getName()).isEqualTo(name);
    }

    @DisplayName("빈 문자열이 들어오면 exception 발생")
    @NullAndEmptySource
    @ValueSource(strings = " ")
    @ParameterizedTest
    void blankNameThrowExceptionTest(final String name) {
        // given & when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Player(name));

        // then
        assertThat(exception.getMessage()).isEqualTo("이름으로 사용할 수 없습니다.");
    }
}
