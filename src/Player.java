import java.util.*;
import java.io.*;
import java.math.*;

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        SearchArea searchArea = new SearchArea(in.nextInt(), in.nextInt());
        int N = in.nextInt(); // maximum number of turns before game over.
        Position initialPosition = new Position(in.nextInt(), in.nextInt());

        Direction bombDirection;
        Position currentPosition = initialPosition;
        Position nextPosition;
        
        // game loop
        while (true) {
            bombDirection = Direction.valueOfLabel(in.next());
            searchArea = refineSearchArea(searchArea, currentPosition, bombDirection);
            nextPosition = findNextPosition(searchArea);
            System.out.println(nextPosition);
            currentPosition = nextPosition;
        }
    }

	private static Position findNextPosition(SearchArea searchArea) {
		return new Position(
            (searchArea.topRight.x - searchArea.bottomLeft.x) / 2 + searchArea.bottomLeft.x, 
            (searchArea.bottomLeft.y - searchArea.topRight.y) / 2 + searchArea.topRight.y 
        );
	}

	private static SearchArea refineSearchArea(SearchArea searchArea, Position currentPosition, Direction bombDirection) {
		SearchArea newSearchArea = new SearchArea(searchArea.bottomLeft, searchArea.topRight);

        switch(bombDirection) {
			case DOWN:
                newSearchArea.topRight.x = currentPosition.x;
                newSearchArea.bottomLeft.x = currentPosition.x;
                newSearchArea.topRight.y = currentPosition.y + 1;
				break;
			case DOWN_LEFT:
                newSearchArea.topRight.x = currentPosition.x - 1;
                newSearchArea.topRight.y = currentPosition.y + 1;
				break;
			case DOWN_RIGHT:
                newSearchArea.topRight.y = currentPosition.y + 1;
                newSearchArea.bottomLeft.x = currentPosition.x + 1;
				break;
			case LEFT:
                newSearchArea.topRight.y = currentPosition.y;
                newSearchArea.bottomLeft.y = currentPosition.y;
                newSearchArea.topRight.x = currentPosition.x - 1;
				break;
			case RIGHT:
                newSearchArea.topRight.y = currentPosition.y;
                newSearchArea.bottomLeft.y = currentPosition.y;
                newSearchArea.bottomLeft.x = currentPosition.x + 1;
				break;
            case UP:
                newSearchArea.topRight.x = currentPosition.x;
                newSearchArea.bottomLeft.x = currentPosition.x;
                newSearchArea.bottomLeft.y = currentPosition.y - 1;
                break;
			case UP_LEFT:
                newSearchArea.topRight.x = currentPosition.x - 1;
                newSearchArea.bottomLeft.y = currentPosition.y - 1;
				break;
			case UP_RIGHT:
                newSearchArea.bottomLeft.x = currentPosition.x + 1;
                newSearchArea.bottomLeft.y = currentPosition.y - 1;
				break;
			default:
				break;
        }

        return newSearchArea;
	}
}