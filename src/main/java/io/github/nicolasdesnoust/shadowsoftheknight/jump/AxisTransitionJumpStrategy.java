package io.github.nicolasdesnoust.shadowsoftheknight.jump;

import io.github.nicolasdesnoust.shadowsoftheknight.DetectorHint;
import io.github.nicolasdesnoust.shadowsoftheknight.SearchArea;
import io.github.nicolasdesnoust.shadowsoftheknight.math.Point;

public class AxisTransitionJumpStrategy implements JumpStrategy {

    @Override
    public Point findNextPosition( //
            Point currentPosition, //
            SearchArea searchArea, //
            DetectorHint hint //
    ) {
        // Fix
        currentPosition = new Point(searchArea.bottomLeft.getX(), currentPosition.getY());

        int distanceFromLeft = calculateAbsoluteDistance(currentPosition.getX(), searchArea.bottomLeft.getX());
        int distanceFromRight = calculateAbsoluteDistance(currentPosition.getX(), searchArea.topRight.getX());

        if (distanceFromLeft >= distanceFromRight) {
            return new Point(searchArea.bottomLeft.getX(), searchArea.bottomLeft.getY());
        } else {
            return new Point(searchArea.topRight.getX(), searchArea.bottomLeft.getY());
        }
    }

    private int calculateAbsoluteDistance(double a, double b) {
        return (int) Math.abs(a - b);
    }

}
