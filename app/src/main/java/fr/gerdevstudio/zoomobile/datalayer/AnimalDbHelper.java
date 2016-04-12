package fr.gerdevstudio.zoomobile.datalayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fr.gerdevstudio.zoomobile.models.Animal;

/**
 * Created by human booster on 11/04/2016.
 */
public class AnimalDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "zoomobile.db";

    public AnimalDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AnimalContract.SQL_CREATE_ANIMALS);
        db.execSQL(AnimalContract.SQL_POPULATE_ANIMALS);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(AnimalContract.SQL_DELETE_ANIMALS);
        onCreate(db);
    }
}
