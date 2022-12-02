package io.github.nicolasdesnoust.shadowsoftheknight.math;

public abstract class Rectangle {
    public final Point bottomLeft;
    public final Point topRight;

    protected Rectangle(int width, int height) {
        this.bottomLeft = new Point(0, height - 1.0);
        this.topRight = new Point(width - 1.0, 0);
    }

    protected Rectangle(Point bottomLeft, Point topRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    public Point getBottomLeft() {
        return bottomLeft;
    }

    public Point getTopRight() {
        return topRight;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bottomLeft == null) ? 0 : bottomLeft.hashCode());
        result = prime * result + ((topRight == null) ? 0 : topRight.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Rectangle other = (Rectangle) obj;
        if (bottomLeft == null) {
            if (other.bottomLeft != null)
                return false;
        } else if (!bottomLeft.equals(other.bottomLeft))
            return false;
        if (topRight == null) {
            if (other.topRight != null)
                return false;
        } else if (!topRight.equals(other.topRight))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
            "bottomLeft=" + bottomLeft +
            ", topRight=" + topRight +
            '}';
    }
}
