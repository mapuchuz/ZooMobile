package fr.gerdevstudio.zoomobile.datalayer;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import fr.gerdevstudio.zoomobile.models.Animal;

/**
 * Created by human booster on 11/04/2016.
 */
public class AnimalProvider extends ContentProvider {

    public static final String AUTHORITY = "fr.gerdevstudio.zoomobile";
    public static final Uri URI = Uri.parse("content://" + AnimalProvider.AUTHORITY + "/"
            + AnimalContract.Animals.TABLE_NAME);

    private static final String BASE_PATH = AnimalContract.Animals.TABLE_NAME;
    private static final int ANIMAL_LIST = 0;
    private static final int ANIMAL_DETAILS = 1;

    private static final UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);
    static {
        matcher.addURI(AUTHORITY, BASE_PATH, ANIMAL_LIST);
        matcher.addURI(AUTHORITY, BASE_PATH+"/#", ANIMAL_DETAILS);
    }


    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        AnimalDbHelper dbHelper= new AnimalDbHelper(this.getContext());
        Cursor result=null;
        int uriType = matcher.match(uri);

        switch (uriType) {
            case ANIMAL_LIST: {
                result = dbHelper.getReadableDatabase().query(
                        AnimalContract.Animals.TABLE_NAME ,
                        projection,
                        selection,
                        selectionArgs, null, null, null
                //        "SELECT * FROM " + AnimalContract.Animals.TABLE_NAME
                );
            }
            case ANIMAL_DETAILS: {
              result = dbHelper.getReadableDatabase().query(
                        AnimalContract.Animals.TABLE_NAME ,
                      projection, selection, selectionArgs, null, null, null
                        //"SELECT * FROM " + AnimalContract.Animals.TABLE_NAME
                        //+ " WHERE " + AnimalContract.Animals._ID == id
                );
            }
        }

        result.setNotificationUri(getContext().getContentResolver(), uri);
        return result;
    }



    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        return null;
    }

    public void insert(Animal animal)
    {
        ContentValues values= new ContentValues();
        values.put(AnimalContract.Animals.COLUMN_NAME_ANIMAL_ID, 1);
        values.put(AnimalContract.Animals.COLUMN_NAME_NAME, animal.getName());
        values.put(AnimalContract.Animals.COLUMN_NAME_SPECIES, animal.getEspece());

        AnimalDbHelper dbHelper= new AnimalDbHelper(this.getContext());
        dbHelper.getWritableDatabase()
                .insert(AnimalContract.Animals.TABLE_NAME, null, values );
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

}
