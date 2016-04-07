package fr.gerdevstudio.colorcontacts.user;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class UserContent {

    /**
     * An array of Users. In the future this could lead to database access
     */
    public static final List<UserModel> ITEMS = generateUsers();


    private static List<UserModel> generateUsers(){
        // Add some sample users.
        List<UserModel> users = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                users.add(new UserModel("nom"+ " "+c,"prenom"));
            }
        }
        return users;
    }
}
