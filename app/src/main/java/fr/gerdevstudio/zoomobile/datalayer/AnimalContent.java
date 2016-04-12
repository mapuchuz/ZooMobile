package fr.gerdevstudio.zoomobile.datalayer;

import android.app.LoaderManager;
import android.content.Context;
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
public class AnimalContent implements  LoaderManager.LoaderCallbacks<Cursor> {

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

    public interface Callbacks{
        void onGetAnimals(List<Animal> result);
    }

    public void getAnimals(Fragment context, Callbacks callbacks)
    {
       this.context=context;
        this.cb=callbacks;
        context.getActivity().getLoaderManager().initLoader(0,null,this);

    }

    private static void addItem(Animal animal) {
        ITEMS.add(animal);
        ITEM_MAP.put(ITEM_MAP.size(), animal);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {

        Uri uri= Uri.parse("content://" + AnimalProvider.AUTHORITY + "/"
                + AnimalContract.Animals.TABLE_NAME);

        CursorLoader cursorLoader=  new CursorLoader(
                context.getActivity(),
                uri,
                null, null, null, null
        );
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader loader, Cursor cursor) {
        for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            Animal animal= new Animal(cursor.getString(cursor.getColumnIndex((AnimalContract.Animals.COLUMN_NAME_NAME))),
                    cursor.getString(cursor.getColumnIndex((AnimalContract.Animals.COLUMN_NAME_SPECIES))));
            ITEMS.add( animal);
            ITEM_MAP.put(cursor.getInt(cursor.getColumnIndex(AnimalContract.Animals._ID)), animal);

            if (cb!=null) cb.onGetAnimals(ITEMS);

        }


    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

}
