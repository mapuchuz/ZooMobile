package fr.gerdevstudio.zoomobile.datalayer;

import android.provider.BaseColumns;

/**
 * Created by human booster on 11/04/2016.
 */
public class AnimalContract {

    private AnimalContract() {

    }

    public static abstract class Animals implements BaseColumns {
        public static final String TABLE_NAME = "animal";
        public static final String COLUMN_NAME_USER_ID = "animalid";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_SPECIES = "species";

    }

    public static final String SQL_CREATE_ANIMALS =
            "CREATE TABLE "+Animals.TABLE_NAME+" ("+
                    Animals._ID+" INTEGER PRIMARY KEY,"+
                    Animals.COLUMN_NAME_USER_ID+" TEXT,"+
                    Animals.COLUMN_NAME_NAME+" TEXT,"+
                    Animals.COLUMN_NAME_SPECIES+" TEXT )";

    public static final String SQL_DELETE_ANIMALS =
            "DROP TABLE IF EXISTS "+Animals.TABLE_NAME;

}
