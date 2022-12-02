package io.github.nicolasdesnoust.shadowsoftheknight.jump;

import io.github.nicolasdesnoust.shadowsoftheknight.DetectorHint;
import io.github.nicolasdesnoust.shadowsoftheknight.SearchArea;
import io.github.nicolasdesnoust.shadowsoftheknight.math.Point;

public class VerticalJumpStrategy implements JumpStrategy {

    @Override
    public Point findNextPosition( //
            Point currentPosition, //
            SearchArea searchArea, //
            DetectorHint hint //
    ) {
        Point onTopEdge = new Point(currentPosition.getX(), searchArea.topRight.getY());
        Point onBottomEdge = new Point(currentPosition.getX(), searchArea.bottomLeft.getY());

        int distanceFromTopEdge = calculateAbsoluteDistance(currentPosition.getY(), onTopEdge.getY());
        int distanceFromBottomEdge = calculateAbsoluteDistance(currentPosition.getY(), onBottomEdge.getY());

        // Sauter en priorité sur le bord opposé de la zone de recherche
        Point nextPosition = distanceFromTopEdge >= distanceFromBottomEdge //
                ? onTopEdge
                : onBottomEdge;

        // Sauter sur le bord le plus proche si le saut sur l'autre bord
        // n'apporterait aucun gain
        if (jumpDoesntImproveSearchArea(searchArea, currentPosition, nextPosition)) {
            nextPosition = distanceFromTopEdge < distanceFromBottomEdge //
                    ? onTopEdge
                    : onBottomEdge;
        }

        return nextPosition;
    }

    private boolean jumpDoesntImproveSearchArea(//
            SearchArea searchArea, //
            Point currentPosition, //
            Point nextPosition //
    ) {
        Point middle = Point.findMiddle(currentPosition, nextPosition);
        return !searchArea.contains(middle);
    }

    private int calculateAbsoluteDistance(double a, double b) {
        return (int) Math.abs(a - b);
    }
}
