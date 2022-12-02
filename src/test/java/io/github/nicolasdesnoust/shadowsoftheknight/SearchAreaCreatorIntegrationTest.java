package io.github.nicolasdesnoust.shadowsoftheknight;

import io.github.nicolasdesnoust.shadowsoftheknight.math.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SearchAreaCreatorIntegrationTest {

    SearchAreaCreator underTest = new SearchAreaCreator();

    @Test
    void givenUnknownHint_whenRefineSearchArea_thenReturnSameSearchArea() {
        SearchArea searchArea = new SearchArea( //
            new Point(0, 9), //
            new Point(19, 0) //
        );
        Point currentPoint = new Point(1, 1);

        SearchArea actualSearchArea = underTest.refineSearchArea( //
            searchArea, //
            null, //
            currentPoint, //
            DetectorHint.UNKNOWN, //
            Axis.X //
        );

        assertThat(actualSearchArea).isEqualTo(searchArea);
    }

    @Test
    void givenSameHint_whenRefineSearchArea_thenShrinkHorizontally() {
        SearchArea searchArea = new SearchArea( //
            new Point(0, 9), //
            new Point(19, 0) //
        );
        Point previousPoint = new Point(4, 5);
        Point currentPoint = new Point(4, 2);

        SearchArea actualSearchArea = underTest.refineSearchArea( //
            searchArea, //
            previousPoint, //
            currentPoint, //
            DetectorHint.SAME, //
            Axis.X //
        );

        assertThat(actualSearchArea.bottomLeft) //
            .extracting("x", "y") //
            .containsExactly(0, 4);

        assertThat(actualSearchArea.topRight) //
            .extracting("x", "y") //
            .containsExactly(19, 4);
    }

    @Test
    void givenSameHint_whenRefineSearchArea_thenShrinkVertically() {
        SearchArea searchArea = new SearchArea( //
            new Point(0, 9), //
            new Point(19, 0) //
        );
        Point previousPoint = new Point(0, 5);
        Point currentPoint = new Point(4, 5);

        SearchArea actualSearchArea = underTest.refineSearchArea( //
            searchArea, //
            previousPoint, //
            currentPoint, //
            DetectorHint.SAME, //
            Axis.X //
        );

        assertThat(actualSearchArea.bottomLeft) //
            .extracting("x", "y") //
            .containsExactly(2, 9);

        assertThat(actualSearchArea.topRight) //
            .extracting("x", "y") //
            .containsExactly(2, 0);
    }

}
