package fr.gerdevstudio.zoomobile.datalayer;

import android.provider.BaseColumns;

/**
 * Created by human booster on 11/04/2016.
 */
public class AnimalContract {

    private AnimalContract() {

    }

    public static abstract class Animals implements BaseColumns {
        public static final String TABLE_NAME = "animals";
        public static final String COLUMN_NAME_USER_ID = "animalid";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_SPECIES = "species";

    }

    public static final String SQL_CREATE_ANIMALS =
            "CREATE TABLE " + Animals.TABLE_NAME + " (" +
                    Animals._ID + " INTEGER PRIMARY KEY," +
                    Animals.COLUMN_NAME_USER_ID + " TEXT," +
                    Animals.COLUMN_NAME_NAME + " TEXT," +
                    Animals.COLUMN_NAME_SPECIES + " TEXT )";

    public static final String SQL_POPULATE_ANIMALS =
            "INSERT INTO " + Animals.TABLE_NAME
                    + "(" + Animals.COLUMN_NAME_USER_ID
                    + "," + Animals.COLUMN_NAME_NAME
                    + "," + Animals.COLUMN_NAME_SPECIES
                    + ") VALUES "
                    + "('1', 'Médor', 'Chien')"
                    + ",('2', 'Ralf', 'Chien')"
                    + ",('3', 'Miaou', 'Chat')"
                    + ",('4', 'Chiffon', 'Chien')"
                    + ",('5', 'Sonic', 'Hérisson')"
                    + ",('6', 'Fasty', 'Escargot');";

    public static final String SQL_DELETE_ANIMALS =
            "DROP TABLE IF EXISTS "+Animals.TABLE_NAME;

}
