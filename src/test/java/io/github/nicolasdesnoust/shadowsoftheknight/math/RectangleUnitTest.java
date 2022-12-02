package io.github.nicolasdesnoust.shadowsoftheknight.math;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RectangleUnitTest {

    @Test
    void givenWidthAndHeight_whenConstructRectangle_thenCreateCorrectPositions() {
        Rectangle rectangle = Mockito.mock(Rectangle.class, Mockito.withSettings() //
                .useConstructor(10, 20) //
                .defaultAnswer(Mockito.CALLS_REAL_METHODS) //
        );

        assertThat(rectangle.bottomLeft) //
                .extracting("x", "y") //
                .containsExactly(0, 19);

        assertThat(rectangle.topRight) //
                .extracting("x", "y") //
                .containsExactly(9, 0);
    }
}
