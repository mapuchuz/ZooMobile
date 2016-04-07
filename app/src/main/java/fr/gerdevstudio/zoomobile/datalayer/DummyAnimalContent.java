package fr.gerdevstudio.zoomobile.datalayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.gerdevstudio.zoomobile.models.Animal;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyAnimalContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Animal> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<Integer, Animal> ITEM_MAP = new HashMap<Integer, Animal>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(new Animal("animal" + i, "chien"));
        }
    }

    private static void addItem(Animal animal) {
        ITEMS.add(animal);
        ITEM_MAP.put(ITEM_MAP.size(), animal);
    }
}
