import java.util.HashMap;
import java.util.Map;

public enum Direction {
    UP("U"), UP_RIGHT("UR"), RIGHT("R"), 
    DOWN_RIGHT("DR"), DOWN("D"), DOWN_LEFT("DL"), 
    LEFT("L"), UP_LEFT("UL");

    private static final Map<String, Direction> BY_VALUE = new HashMap<>();
    
    static {
        for (Direction e: values()) {
            BY_VALUE.put(e.value, e);
        }
    }

    private final String value;

    private Direction(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Direction valueOfLabel(String value) {
        return BY_VALUE.get(value);
    }
}