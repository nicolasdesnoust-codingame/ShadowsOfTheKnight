package io.github.nicolasdesnoust.shadowsoftheknight;

import io.github.nicolasdesnoust.shadowsoftheknight.math.Line;
import io.github.nicolasdesnoust.shadowsoftheknight.math.Point;
import io.github.nicolasdesnoust.shadowsoftheknight.math.Segment;

import java.util.ArrayList;
import java.util.List;

public class SearchAreaCreator {

    public SearchArea refineSearchArea( //
                                        SearchArea searchArea, //
                                        Point previousPosition, //
                                        Point currentPosition, //
                                        DetectorHint hint, //
                                        Axis currentAxis //
    ) {
        SearchArea newSearchArea = searchArea;

        if (hint == DetectorHint.SAME) {
            newSearchArea = shrink(searchArea, previousPosition, currentPosition, currentAxis);
        } else if (hint == DetectorHint.COLDER || hint == DetectorHint.WARMER) {
            newSearchArea = cutASide(searchArea, previousPosition, currentPosition, hint);
        }

        newSearchArea = removeCurrentPosition(currentPosition, newSearchArea);

        return newSearchArea;
    }

    private SearchArea shrink( //
                               SearchArea searchArea, //
                               Point previousPosition, //
                               Point currentPosition, //
                               Axis currentAxis //
    ) {
        Point middle = Point.findMiddle(previousPosition, currentPosition);

        if (currentAxis == Axis.Y) {
            return searchArea.shrinkHorizontallyTo((int) middle.getY());
        } else {
            return searchArea.shrinkVerticallyTo((int) middle.getX());
        }
    }

    private SearchArea cutASide( //
                                 SearchArea searchArea, //
                                 Point previousPosition, //
                                 Point currentPosition, //
                                 DetectorHint hint) {

        Point middle = Point.findMiddle(previousPosition, currentPosition);
        Line line = Line.computeLineEquation(previousPosition, currentPosition);
        Line perpendicular = line.getPerpendicular(middle);

        List<Point> intersections = findIntersectionPoints(searchArea, perpendicular);
        Point intersection1 = intersections.get(0);
        Point intersection2 = intersections.get(1);

        if (intersectHorizontally(searchArea, perpendicular)) {
            if (bombIsAbove(previousPosition, currentPosition, hint)) {
                int y = (int) Math.max(intersection1.getY(), intersection2.getY());
                return searchArea.cutEverythingBelow(y);
            } else {
                int y = (int) Math.min(intersection1.getY(), intersection2.getY());
                return searchArea.cutEverythingAbove(y);
            }
        } else if (intersectVertically(searchArea, perpendicular)) {
            if (bombIsAtLeft(previousPosition, currentPosition, hint)) {
                int x = (int) Math.max(intersection1.getX(), intersection2.getX());
                return searchArea.cutEverythingAtRightOf(x);
            } else {
                int x = (int) Math.min(intersection1.getX(), intersection2.getX());
                return searchArea.cutEverythingAtLeftOf(x);
            }
        }

        return searchArea;
    }

    private boolean bombIsAtLeft(Point previousPosition, Point currentPosition, DetectorHint hint) {
        return DetectorHint.COLDER.equals(hint) && currentPosition.isAtRightOf(previousPosition) //
            || DetectorHint.WARMER.equals(hint) && currentPosition.isAtLeftOf(previousPosition);
    }

    private boolean bombIsAtRight(Point previousPosition, Point currentPosition, DetectorHint hint) {
        return DetectorHint.WARMER.equals(hint) && currentPosition.isAtRightOf(previousPosition) //
            || DetectorHint.COLDER.equals(hint) && currentPosition.isAtLeftOf(previousPosition);
    }

    private boolean bombIsAbove(Point previousPosition, Point currentPosition, DetectorHint hint) {
        return DetectorHint.COLDER.equals(hint) && currentPosition.isBelow(previousPosition) //
            || DetectorHint.WARMER.equals(hint) && currentPosition.isAbove(previousPosition);
    }

    private boolean bombIsBelow(Point previousPosition, Point currentPosition, DetectorHint hint) {
        return DetectorHint.WARMER.equals(hint) && currentPosition.isBelow(previousPosition) //
            || DetectorHint.COLDER.equals(hint) && currentPosition.isAbove(previousPosition);
    }

    private List<Point> findIntersectionPoints(SearchArea searchArea, Line line) {
        Point topRight = searchArea.getTopRight();
        Point bottomLeft = searchArea.getBottomLeft();
        Point topLeft =  new Point(bottomLeft.getX() + 0.001, topRight.getY());
        Point bottomRight = new Point(topRight.getX() + 0.001, bottomLeft.getY());

        Segment topEdge = new Segment(topRight, topLeft);
        Segment bottomEdge = new Segment(bottomLeft, bottomRight);
        Segment leftEdge = new Segment(topLeft, bottomLeft);
        Segment rightEdge = new Segment(bottomRight, topRight);

        List<Point> intersections = new ArrayList<>();

        Line.calculateIntersectionPoint(line, Line.computeLineEquation(topEdge))
            .ifPresent(intersection -> System.err.println("intersection Point with topEdge: " + intersection));
        Line.calculateIntersectionPoint(line, Line.computeLineEquation(bottomEdge))
            .ifPresent(intersection -> System.err.println("intersection Point with bottomEdge: " + intersection));
        Line.calculateIntersectionPoint(line, Line.computeLineEquation(leftEdge))
            .ifPresent(intersection -> System.err.println("intersection Point with leftEdge: " + intersection));
        Line.calculateIntersectionPoint(line, Line.computeLineEquation(rightEdge))
            .ifPresent(intersection -> System.err.println("intersection Point with rightEdge: " + intersection));

        Line.calculateIntersectionPoint(line, Line.computeLineEquation(topEdge))
            .map(intersection -> new Point(Math.round(intersection.getX()), Math.round(intersection.getY())))
            .filter(topEdge::contains)
            .ifPresent(intersections::add);
        Line.calculateIntersectionPoint(line, Line.computeLineEquation(bottomEdge))
            .map(intersection -> new Point(Math.round(intersection.getX()), Math.round(intersection.getY())))
            .filter(bottomEdge::contains)
            .ifPresent(intersections::add);
        Line.calculateIntersectionPoint(line, Line.computeLineEquation(leftEdge))
            .map(intersection -> new Point(Math.round(intersection.getX()), Math.round(intersection.getY())))
            .filter(leftEdge::contains)
            .ifPresent(intersections::add);
        Line.calculateIntersectionPoint(line, Line.computeLineEquation(rightEdge))
            .map(intersection -> new Point(Math.round(intersection.getX()), Math.round(intersection.getY())))
            .filter(rightEdge::contains)
            .ifPresent(intersections::add);

        return intersections;
    }

    private boolean intersectVertically(SearchArea searchArea, Line line) {
        Point topRight = searchArea.getTopRight();
        Point bottomLeft = searchArea.getBottomLeft();

        Line topEdge = Line.computeLineEquation( //
            topRight, new Point(bottomLeft.getX(), topRight.getY()) //
        );
        Line bottomEdge = Line.computeLineEquation( //
            bottomLeft, new Point(topRight.getX(), bottomLeft.getY()) //
        );

        List<Point> intersections = new ArrayList<>();
        Line.calculateIntersectionPoint(line, topEdge).ifPresent(intersections::add);
        Line.calculateIntersectionPoint(line, bottomEdge).ifPresent(intersections::add);

        return intersections.size() == 2;
    }

    private boolean intersectHorizontally(SearchArea searchArea, Line line) {
        Point topRight = searchArea.getTopRight();
        Point bottomLeft = searchArea.getBottomLeft();

        Line leftEdge = Line.computeLineEquation( //
            new Point(bottomLeft.getX(), topRight.getY()), bottomLeft //
        );
        Line rightEdge = Line.computeLineEquation( //
            new Point(topRight.getX(), bottomLeft.getY()), topRight //
        );

        List<Point> intersections = new ArrayList<>();
        Line.calculateIntersectionPoint(line, leftEdge).ifPresent(intersections::add);
        Line.calculateIntersectionPoint(line, rightEdge).ifPresent(intersections::add);

        return intersections.size() == 2;
    }

    private SearchArea removeCurrentPosition(Point currentPosition, SearchArea searchArea) {

        if (searchArea.isRestrictedTo(Axis.Y)) {
            if (currentPosition.equals(searchArea.bottomLeft)) {
                int threshold = (int) searchArea.bottomLeft.getY() - 1;
                searchArea = searchArea.cutEverythingBelow(threshold);
            } else if (currentPosition.equals(searchArea.topRight)) {
                int threshold = (int) searchArea.topRight.getY() + 1;
                searchArea = searchArea.cutEverythingAbove(threshold);
            }
        }

        if (searchArea.isRestrictedTo(Axis.X)) {
            if (currentPosition.equals(searchArea.bottomLeft)) {
                int threshold = (int) searchArea.bottomLeft.getX() + 1;
                searchArea = searchArea.cutEverythingAtLeftOf(threshold);
            } else if (currentPosition.equals(searchArea.topRight)) {
                int threshold = (int) searchArea.topRight.getX() - 1;
                searchArea = searchArea.cutEverythingAtRightOf(threshold);
            }
        }

        return searchArea;
    }


}
