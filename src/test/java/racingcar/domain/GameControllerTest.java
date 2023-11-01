package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

class GameControllerTest {

    @ParameterizedTest
    @CsvSource(value = {"4:-", "3:''", "9:-"}, delimiter = ':')
    @DisplayName("랜덤값이 4이상이면 전진하는 기능")
    void moveOrNot_랜덤값이_4이상이면_전진(int randomNumber, StringBuffer expected) {
        GameController gameController = new GameController();
        Car car = new Car("test");

        gameController.moveOrNot(car, randomNumber);

        assertThat(car.getForwardDistance().toString()).isEqualTo(expected.toString());
    }

    @ParameterizedTest
    @CsvSource(value = {"4,3,1,car1 : -,car2 : -", "4,6,9,car1 : ---,car2 : ---"})
    @DisplayName("시도 횟수만큼 전진 혹은 멈춤을 반복수행")
    void play_시도_횟수만큼_전진_혹은_멈춤을_반복수행(
            int number1, int number2, int number3, String expected1, String expected2
    ) {
        GameController gameController = new GameController();
        List<Car> carList = new ArrayList<>();
        Car car1 = new Car("car1");
        Car car2 = new Car("car2");
        carList.add(car1);
        carList.add(car2);
        int attemptCount = 3;
        int[] randomNumberList = {number1, number2, number3};

        gameController.play(carList, attemptCount, randomNumberList);

        assertThat(car1.status()).isEqualTo(expected1);
        assertThat(car2.status()).isEqualTo(expected2);
    }

}