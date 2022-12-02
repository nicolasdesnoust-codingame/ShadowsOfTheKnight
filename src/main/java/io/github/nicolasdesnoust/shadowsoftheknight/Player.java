package io.github.nicolasdesnoust.shadowsoftheknight;

import java.util.Scanner;

import io.github.nicolasdesnoust.shadowsoftheknight.jump.JumpStrategy;
import io.github.nicolasdesnoust.shadowsoftheknight.jump.JumpStrategyFactory;
import io.github.nicolasdesnoust.shadowsoftheknight.math.Point;

class Player {

    private static final SearchAreaCreator searchAreaCreator = new SearchAreaCreator();
    private static final JumpStrategyFactory jumpStrategyFactory = new JumpStrategyFactory();

    @SuppressWarnings("squid:S2189")
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        SearchArea searchArea = new SearchArea(in.nextInt(), in.nextInt());
        in.nextInt(); // maximum number of turns before game over.
        Point currentPosition = new Point(in.nextInt(), in.nextInt());
        DetectorHint hint;
        JumpStrategy jumpStrategy = null;
        Point previousPosition = null;
        Point nextPosition;
        Axis currentAxis = Axis.Y;

        // game loop
        while (true) {
            hint = DetectorHint.valueOf(in.next());

            jumpStrategy = jumpStrategyFactory.getMostAppropriateJumpStrategy( //
                    jumpStrategy, //
                    hint, //
                    currentAxis //
            );

            System.err.println("using strategy " + jumpStrategy.getClass().getSimpleName());

            nextPosition = jumpStrategy.findNextPosition( //
                    currentPosition, //
                    searchArea, //
                    hint //
            );

            System.out.println(nextPosition);

            searchArea = searchAreaCreator.refineSearchArea( //
                    searchArea, //
                    previousPosition, //
                    currentPosition, //
                    hint, //
                    currentAxis //
            );

            currentAxis = getNewAxis(searchArea);

            previousPosition = currentPosition;
            currentPosition = nextPosition;
        }
    }

    private static Axis getNewAxis(SearchArea searchArea) {
        return searchArea.bottomLeft.getY() == searchArea.topRight.getY() //
                ? Axis.X
                : Axis.Y;
    }

}
