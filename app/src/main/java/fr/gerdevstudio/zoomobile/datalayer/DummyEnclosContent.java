package fr.gerdevstudio.zoomobile.datalayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.gerdevstudio.zoomobile.models.Enclos;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyEnclosContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Enclos> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<Integer, Enclos> ITEM_MAP = new HashMap<>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(new Enclos("enclos "+i));
        }
    }

    private static void addItem(Enclos enclos) {
        ITEMS.add(enclos);
        ITEM_MAP.put(ITEM_MAP.size(), enclos);
    }
}
