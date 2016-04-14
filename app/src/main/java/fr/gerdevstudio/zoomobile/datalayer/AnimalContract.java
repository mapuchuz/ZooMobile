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
        public static final String COLUMN_NAME_ANIMAL_ID = "animalid";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_SPECIES = "species";
        public static final String COLUMN_NAME_PHOTO = "photo";

    }

    public static final String SQL_CREATE_ANIMALS =
            "CREATE TABLE " + Animals.TABLE_NAME + " (" +
                    Animals._ID + " INTEGER PRIMARY KEY," +
                    Animals.COLUMN_NAME_ANIMAL_ID + " TEXT," +
                    Animals.COLUMN_NAME_NAME + " TEXT," +
                    Animals.COLUMN_NAME_SPECIES + " TEXT," +
                    Animals.COLUMN_NAME_PHOTO + " TEXT )";

    public static final String SQL_POPULATE_ANIMALS =
            "INSERT INTO " + Animals.TABLE_NAME
                    + "(" + Animals.COLUMN_NAME_ANIMAL_ID
                    + "," + Animals.COLUMN_NAME_NAME
                    + "," + Animals.COLUMN_NAME_SPECIES
                    + "," + Animals.COLUMN_NAME_PHOTO
                    + ") VALUES "
                    + "('1', 'Médor', 'Chien', 'chien_photo')"
                    + ",('2', 'Ralf', 'Chien', 'chien_photo')"
                    + ",('3', 'Miaou', 'Chat', 'chat_photo')"
                    + ",('4', 'Chiffon', 'Chien', 'chien_photo')"
                    + ",('5', 'Sonic', 'Hérisson', 'oiseau_photo')"
                    + ",('6', 'Fasty', 'Vipère', 'reptile_photo');";

    public static final String SQL_DELETE_ANIMALS =
            "DROP TABLE IF EXISTS "+Animals.TABLE_NAME;

}
