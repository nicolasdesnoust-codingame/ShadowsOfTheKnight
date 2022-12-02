package io.github.nicolasdesnoust.shadowsoftheknight.jump;

import io.github.nicolasdesnoust.shadowsoftheknight.DetectorHint;
import io.github.nicolasdesnoust.shadowsoftheknight.SearchArea;
import io.github.nicolasdesnoust.shadowsoftheknight.math.Point;

public interface JumpStrategy {

    Point findNextPosition( //
            Point currentPosition, //
            SearchArea searchArea, //
            DetectorHint hint);

}
