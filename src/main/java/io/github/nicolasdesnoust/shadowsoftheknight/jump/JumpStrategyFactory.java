package io.github.nicolasdesnoust.shadowsoftheknight.jump;

import io.github.nicolasdesnoust.shadowsoftheknight.Axis;
import io.github.nicolasdesnoust.shadowsoftheknight.DetectorHint;

public class JumpStrategyFactory {

    public JumpStrategy getMostAppropriateJumpStrategy( //
            JumpStrategy previousJumpStrategy, //
            DetectorHint hint, //
            Axis currentAxis //
    ) {
        if (hint == DetectorHint.UNKNOWN) {
            return currentAxis == Axis.Y //
                    ? new InitialJumpStrategy()
                    : new AxisTransitionJumpStrategy();
        }

        if (currentAxis == Axis.X) {
            if (!(previousJumpStrategy instanceof AxisTransitionJumpStrategy) //
                    && !(previousJumpStrategy instanceof HorizontalJumpStrategy)) {
                return new AxisTransitionJumpStrategy();
            } else {
                return new HorizontalJumpStrategy();
            }
        }

        return new VerticalJumpStrategy();
    }
}
