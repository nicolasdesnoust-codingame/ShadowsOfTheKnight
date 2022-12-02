package io.github.nicolasdesnoust.shadowsoftheknight.jump;

import io.github.nicolasdesnoust.shadowsoftheknight.DetectorHint;
import io.github.nicolasdesnoust.shadowsoftheknight.SearchArea;
import io.github.nicolasdesnoust.shadowsoftheknight.math.Point;

public class InitialJumpStrategy implements JumpStrategy {

    @Override
    public Point findNextPosition( //
            Point currentPosition, //
            SearchArea searchArea, //
            DetectorHint hint //
    ) {
        int distanceFromTop = calculateAbsoluteDistance(currentPosition.getY(), searchArea.topRight.getY());
        int distanceFromBottom = calculateAbsoluteDistance(currentPosition.getY(), searchArea.bottomLeft.getY());

        if (distanceFromTop >= distanceFromBottom) {
            return new Point(currentPosition.getX(), searchArea.topRight.getY());
        } else {
            return new Point(currentPosition.getX(), searchArea.bottomLeft.getY());
        }
    }

    private int calculateAbsoluteDistance(double a, double b) {
        return (int) Math.abs(a - b);
    }

}
