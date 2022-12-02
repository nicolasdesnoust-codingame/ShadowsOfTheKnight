package io.github.nicolasdesnoust.shadowsoftheknight;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.nicolasdesnoust.shadowsoftheknight.math.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SearchAreaUnitTest {

    @Test
    void givenSearchArea_whenShrinkHorizontally_thenSuccess() {
        SearchArea searchArea = new SearchArea( //
                new Point(0, 9), //
                new Point(19, 0) //
        );

        SearchArea actualSearchArea = searchArea.shrinkHorizontallyTo(5);

        assertThat(actualSearchArea.bottomLeft) //
                .extracting("x", "y") //
                .containsExactly(0, 5);

        assertThat(actualSearchArea.topRight) //
                .extracting("x", "y") //
                .containsExactly(19, 5);
    }

    @Test
    void givenSearchArea_whenShrinkVertically_thenSuccess() {
        SearchArea searchArea = new SearchArea( //
                new Point(0, 9), //
                new Point(19, 0) //
        );

        SearchArea actualSearchArea = searchArea.shrinkVerticallyTo(5);

        assertThat(actualSearchArea.bottomLeft) //
                .extracting("x", "y") //
                .containsExactly(5, 9);

        assertThat(actualSearchArea.topRight) //
                .extracting("x", "y") //
                .containsExactly(5, 0);
    }

    @Test
    void givenSearchArea_cutEverythingAboveThreshold() {
        SearchArea searchArea = new SearchArea( //
                new Point(0, 9), //
                new Point(19, 0) //
        );

        SearchArea actualSearchArea = searchArea.cutEverythingAbove(5);

        assertThat(actualSearchArea.bottomLeft) //
                .extracting("x", "y") //
                .containsExactly(0, 9);

        assertThat(actualSearchArea.topRight) //
                .extracting("x", "y") //
                .containsExactly(19, 5);
    }

    @Test
    void givenSearchArea_cutEverythingBelowThreshold() {
        SearchArea searchArea = new SearchArea( //
                new Point(0, 9), //
                new Point(19, 0) //
        );

        SearchArea actualSearchArea = searchArea.cutEverythingBelow(5);

        assertThat(actualSearchArea.bottomLeft) //
                .extracting("x", "y") //
                .containsExactly(0, 5);

        assertThat(actualSearchArea.topRight) //
                .extracting("x", "y") //
                .containsExactly(19, 0);
    }

    @Test
    void givenSearchArea_cutEverythingAtLeftOfThreshold() {
        SearchArea searchArea = new SearchArea( //
                new Point(0, 9), //
                new Point(19, 0) //
        );

        SearchArea actualSearchArea = searchArea.cutEverythingAtLeftOf(5);

        assertThat(actualSearchArea.bottomLeft) //
                .extracting("x", "y") //
                .containsExactly(5, 9);

        assertThat(actualSearchArea.topRight) //
                .extracting("x", "y") //
                .containsExactly(19, 0);
    }

    @Test
    void givenSearchArea_cutEverythingAtRightOfThreshold() {
        SearchArea searchArea = new SearchArea( //
                new Point(0, 9), //
                new Point(19, 0) //
        );

        SearchArea actualSearchArea = searchArea.cutEverythingAtRightOf(5);

        assertThat(actualSearchArea.bottomLeft) //
                .extracting("x", "y") //
                .containsExactly(0, 9);

        assertThat(actualSearchArea.topRight) //
                .extracting("x", "y") //
                .containsExactly(5, 0);
    }

    @ParameterizedTest
    @CsvSource({ //
            "14, 6", //
            "19, 0", //
            "0, 9" //
    })
    void givenPoint_whenCheckIfSearchAreaContainsPoint_thenReturnTrue(int x, int y) {
        SearchArea searchArea = new SearchArea( //
                new Point(0, 9), //
                new Point(19, 0) //
        );
        Point insideSearchArea = new Point(x, y);

        boolean searchAreaContainsPoint = searchArea.contains(insideSearchArea);

        assertThat(searchAreaContainsPoint).isTrue();
    }

    @ParameterizedTest
    @CsvSource({ //
            "1, 6", //
            "20, 6", //
            "10, 1", //
            "10, 10" //
    })
    void givenPoint_whenCheckIfSearchAreaContainsPoint_thenReturnFalse(int x, int y) {
        SearchArea searchArea = new SearchArea( //
                new Point(2, 9), //
                new Point(19, 2) //
        );
        Point outsideSearchArea = new Point(x, y);

        boolean searchAreaContainsPoint = searchArea.contains(outsideSearchArea);

        assertThat(searchAreaContainsPoint).isFalse();
    }
}
