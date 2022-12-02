package io.github.nicolasdesnoust.shadowsoftheknight.jump;

import io.github.nicolasdesnoust.shadowsoftheknight.DetectorHint;
import io.github.nicolasdesnoust.shadowsoftheknight.SearchArea;
import io.github.nicolasdesnoust.shadowsoftheknight.math.Point;

public class HorizontalJumpStrategy implements JumpStrategy {

    @Override
    public Point findNextPosition( //
            Point currentPosition, //
            SearchArea searchArea, //
            DetectorHint hint //
    ) {
        Point onLeftEdge = new Point(searchArea.bottomLeft.getX(), currentPosition.getY());
        Point onRightEdge = new Point(searchArea.topRight.getX(), currentPosition.getY());

        Point nextPosition;
        // Sauter en priorité sur le bord opposé de la zone de recherche
        if (calculateAbsoluteDistance(currentPosition.getX(),
                onLeftEdge.getX()) >= calculateAbsoluteDistance(currentPosition.getX(), onRightEdge.getX())) {
            nextPosition = onLeftEdge;
        } else {
            nextPosition = onRightEdge;
        }

        Point middle = Point.findMiddle(currentPosition, nextPosition);
        if (!searchArea.contains(middle)) {
            if (calculateAbsoluteDistance(currentPosition.getX(),
                    onLeftEdge.getX()) >= calculateAbsoluteDistance(currentPosition.getX(), onRightEdge.getX())) {
                nextPosition = onRightEdge;
            } else {
                nextPosition = onLeftEdge;
            }
        }

        return nextPosition;
    }

    private double calculateAbsoluteDistance(double a, double b) {
        return Math.abs(a - b);
    }

}
