package baseball;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class GuessNumberFromUserTest {

    GuessNumberFromUser guessNumberFromUser = new GuessNumberFromUser();

    @AfterEach
    void closeScanner() {
        Console.close();
    }

    @Test
    void 추측값_정상_로직_테스트() {
        // given
        String input = "123";
        settingForInputStream(input);

        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // when
        List<Integer> guessNumber = guessNumberFromUser.getGuessNumber();

        // then
        assertThat(out.toString()).contains("숫자를 입력해주세요 : ");
        assertThat(guessNumber).isEqualTo(List.of(1, 2, 3));
    }

    @Test
    void 추측값_타입_예외_테스트() {
        // given
        String input = "avx\nㅁㄱㄴ\n\"   \"";
        settingForInputStream(input);

        // when & then
        String[] inputList = input.split("\n");
        for (int i = 0; i < inputList.length; i++) {
            assertThatThrownBy(() -> guessNumberFromUser.getGuessNumber())
                .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void 추측값_자리수_예외_테스트() {
        // given
        String input = "12\n1256\n9246";
        settingForInputStream(input);

        // when & then
        String[] inputList = input.split("\n");
        for (int i = 0; i < inputList.length; i++) {
            assertThatThrownBy(() -> guessNumberFromUser.getGuessNumber())
                .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void 추측값_범위_예외_테스트() {
        // given
        String input = "102\n012\n9086";
        settingForInputStream(input);

        // when & then
        String[] inputList = input.split("\n");
        for (int i = 0; i < inputList.length; i++) {
            assertThatThrownBy(() -> guessNumberFromUser.getGuessNumber())
                .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void 추측값_중복_예외_테스트() {
        // given
        String input = "112\n999\n9774";
        settingForInputStream(input);

        // when & then
        String[] inputList = input.split("\n");
        for (int i = 0; i < inputList.length; i++) {
            assertThatThrownBy(() -> guessNumberFromUser.getGuessNumber())
                .isInstanceOf(IllegalArgumentException.class);
        }
    }

    private void settingForInputStream(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

}