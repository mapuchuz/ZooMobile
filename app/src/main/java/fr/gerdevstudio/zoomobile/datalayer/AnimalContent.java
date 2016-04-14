package fr.gerdevstudio.zoomobile.datalayer;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

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
public class AnimalContent implements LoaderManager.LoaderCallbacks<Cursor> {

    Fragment context;
    Callbacks cb;

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Animal> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<Integer, Animal> ITEM_MAP = new HashMap<Integer, Animal>();

    public interface Callbacks {
        void onGetAnimals(List<Animal> result);
    }

    public void getAnimals(Fragment context, Callbacks callbacks) {
        this.context = context;
        this.cb = callbacks;
        context.getActivity().getLoaderManager().initLoader(0, null, this);
    }

    //Todo coder la méthode addAnimal pour enregistrer un nouvel animal dans la base de données
    private void addAnimal(Fragment fragment, Animal animal) {
        context = fragment;

        // Defines a new Uri object that receives the result of the insertion
        Uri mNewUri;


        // Defines an object to contain the new values to insert
        ContentValues animalContentValues = new ContentValues();

        /*
        * Sets the values of each column and inserts the word. The arguments to the "put"
        * method are "column name" and "value"
        */
        animalContentValues.put(AnimalContract.Animals.COLUMN_NAME_NAME, animal.getNom());
        animalContentValues.put(AnimalContract.Animals.COLUMN_NAME_SPECIES, animal.getEspece());
        animalContentValues.put(AnimalContract.Animals.COLUMN_NAME_ANIMAL_ID, animal.getId());

        mNewUri = context.getActivity().getContentResolver().insert(
                AnimalProvider.URI,   // the animal provider content URI
                animalContentValues            // the values to insert
        );
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {

        Uri uri = AnimalProvider.URI;

        CursorLoader cursorLoader = new CursorLoader(
                context.getActivity(),
                uri,
                null, null, null, null
        );
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader loader, Cursor cursor) {
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Animal animal = new Animal(cursor.getString(cursor.getColumnIndex((AnimalContract.Animals.COLUMN_NAME_NAME))),
                    cursor.getString(cursor.getColumnIndex((AnimalContract.Animals.COLUMN_NAME_SPECIES))),
                    cursor.getString(cursor.getColumnIndex((AnimalContract.Animals.COLUMN_NAME_PHOTO))));
            ITEMS.add(animal);
            ITEM_MAP.put(cursor.getInt(cursor.getColumnIndex(AnimalContract.Animals._ID)), animal);
        }

        if (cb != null) cb.onGetAnimals(ITEMS);


    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

}
