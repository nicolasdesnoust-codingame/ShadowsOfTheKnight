
public class SearchArea {
    Position bottomLeft;
    Position topRight;

    public SearchArea(int width, int height) {
        this.bottomLeft = new Position(0, height - 1);
        this.topRight = new Position(width - 1, 0);
    }

    public SearchArea(Position bottomLeft, Position topRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }
}