package fr.gerdevstudio.zoomobile.datalayer;

import android.content.ContentProvider;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by human booster on 11/04/2016.
 */
public class AnimalProvider extends ContentProvider {

    private static final String AUTHORITY = "fr.gerdevstudio.zoomobile";
    private static final String BASE_PATH = "animals";
    private static final int ANIMAL_LIST = 0;
    private static final int ANIMAL_DETAILS = 1;

    private static final UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);
    static {
        matcher.addURI(AUTHORITY, BASE_PATH, ANIMAL_LIST);
        matcher.addURI(AUTHORITY, BASE_PATH+"/#", ANIMAL_DETAILS);
    }

    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor result;
        int uriType = matcher.match(uri);

        switch (uriType) {
            case ANIMAL_LIST: {
                result = dbHelper.getReadableDatabase().query("SELECT * FROM " + AnimalContract.Animals.TABLE_NAME);
            }
            case ANIMAL_DETAILS: {
                result = dbHelper.getReadableDatabase().query("SELECT * FROM " + AnimalContract.Animals.TABLE_NAME
                        + " WHERE " + AnimalContract.Animals._ID == id);
            }
        }

        result.setNotificationUri(getContext().getContentResolver(), uri);
        return result;
    }


    public int insert() {
        getContext().getContentResolver().notifyChange(uri, null);
        return 0;
    }
    public int update() {
        getContext().getContentResolver().notifyChange(uri, null);
        return 0;
    }

    public int delete() {
        getContext().getContentResolver().notifyChange(uri, null);
        return 0;
    }



}
