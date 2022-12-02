package io.github.nicolasdesnoust.shadowsoftheknight.math;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PointUnitTest {

    @Test
    void givenTwoDifferentPoints_whenFindMiddle_thenSuccess() {
        Point a = new Point(17, 0);
        Point b = new Point(17, 1);

        Point middle = Point.findMiddle(a, b);

        assertThat(middle.getX()).isEqualTo(17);
        assertThat(middle.getY()).isEqualTo(1);
    }

    @Test
    void givenTwoIdenticalPoints_whenFindMiddle_thenSuccess() {
        Point a = new Point(0, 5);
        Point b = new Point(0, 5);

        Point middle = Point.findMiddle(a, b);

        assertThat(middle.getX()).isZero();
        assertThat(middle.getY()).isEqualTo(5);
    }

    @ParameterizedTest
    @CsvSource({ //
            "0, 0, 6, 10, true", //
            "0, 10, 6, 0, false", //
            "0, 0, 0, 0, false" //
    })
    void givenTwoPoints_checkIfFirstOneIsAboveSecondOne( //
            int aX, int aY, //
            int bX, int bY, //
            boolean expectedIsAbove //
    ) {
        Point a = new Point(aX, aY);
        Point b = new Point(bX, bY);

        boolean isAbove = a.isAbove(b);

        assertThat(isAbove).isEqualTo(expectedIsAbove);
    }

    @ParameterizedTest
    @CsvSource({ //
            "0, 10, 6, 0, true", //
            "0, 0, 6, 10, false", //
            "0, 0, 0, 0, false" //
    })
    void givenTwoPoints_checkIfFirstOneIsBelowSecondOne( //
            int aX, int aY, //
            int bX, int bY, //
            boolean expectedIsBelow //
    ) {
        Point a = new Point(aX, aY);
        Point b = new Point(bX, bY);

        boolean isBelow = a.isBelow(b);

        assertThat(isBelow).isEqualTo(expectedIsBelow);
    }

    @ParameterizedTest
    @CsvSource({ //
            "0, 10, 6, 0, true", //
            "6, 0, 3, 10, false", //
            "0, 0, 0, 0, false" //
    })
    void givenTwoPoints_checkIfFirstOneIsAtLeftOfSecondOne( //
            int aX, int aY, //
            int bX, int bY, //
            boolean expectedIsAtLeft //
    ) {
        Point a = new Point(aX, aY);
        Point b = new Point(bX, bY);

        boolean isAtLeft = a.isAtLeftOf(b);

        assertThat(isAtLeft).isEqualTo(expectedIsAtLeft);
    }

    @ParameterizedTest
    @CsvSource({ //
            "6, 0, 3, 10, true", //
            "0, 10, 6, 0, false", //
            "0, 0, 0, 0, false" //
    })
    void givenTwoPoints_checkIfFirstOneIsAtRightOfSecondOne( //
            int aX, int aY, //
            int bX, int bY, //
            boolean expectedIsAtRight //
    ) {
        Point a = new Point(aX, aY);
        Point b = new Point(bX, bY);

        boolean isAtRight = a.isAtRightOf(b);

        assertThat(isAtRight).isEqualTo(expectedIsAtRight);
    }

}
