package io.github.nicolasdesnoust.shadowsoftheknight;

import io.github.nicolasdesnoust.shadowsoftheknight.math.Point;
import io.github.nicolasdesnoust.shadowsoftheknight.math.Rectangle;

public class SearchArea extends Rectangle {

    public SearchArea(int width, int height) {
        super(width, height);
    }

    public SearchArea(Point bottomLeft, Point topRight) {
        super(bottomLeft, topRight);
    }

    public SearchArea shrinkHorizontallyTo(int y) {
        return new SearchArea( //
                new Point(bottomLeft.getX(), y), //
                new Point(topRight.getX(), y) //
        );
    }

    public SearchArea shrinkVerticallyTo(int x) {
        return new SearchArea( //
                new Point(x, bottomLeft.getY()), //
                new Point(x, topRight.getY()) //
        );
    }

    public SearchArea cutEverythingAbove(int y) {
        return new SearchArea( //
                new Point(bottomLeft.getX(), bottomLeft.getY()), //
                new Point(topRight.getX(), Math.max(y, topRight.getY())) //
        );
    }

    public SearchArea cutEverythingBelow(int y) {
        return new SearchArea( //
                new Point(bottomLeft.getX(), Math.min(y, bottomLeft.getY())), //
                new Point(topRight.getX(), topRight.getY()) //
        );
    }

    public SearchArea cutEverythingAtLeftOf(int x) {
        return new SearchArea( //
                new Point(Math.max(x, bottomLeft.getX()), bottomLeft.getY()), //
                new Point(topRight.getX(), topRight.getY()) //
        );
    }

    public SearchArea cutEverythingAtRightOf(int x) {
        return new SearchArea( //
                new Point(bottomLeft.getX(), bottomLeft.getY()), //
                new Point(Math.min(x, topRight.getX()), topRight.getY()) //
        );
    }

    public boolean contains(Point position) {
        return position.getX() >= bottomLeft.getX() //
                && position.getX() <= topRight.getX() //
                && position.getY() >= topRight.getY() //
                && position.getY() <= bottomLeft.getY();
    }

    public boolean isRestrictedTo(Axis axis) {
        return (axis == Axis.Y && bottomLeft.getX() == topRight.getX()) //
                || (axis == Axis.X && bottomLeft.getY() == topRight.getY());
    }

}
