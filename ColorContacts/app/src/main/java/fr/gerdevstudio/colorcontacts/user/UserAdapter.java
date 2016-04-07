package fr.gerdevstudio.colorcontacts.user;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

import fr.gerdevstudio.colorcontacts.R;
import fr.gerdevstudio.colorcontacts.user.UserModel;

/**
 * Created by ger on 04/04/2016.
 */
public class UserAdapter extends ArrayAdapter<UserModel> {

    private static class ViewHolder {
        TextView nomTv;
        TextView prenomTv;
    }

    private ArrayList<UserModel> users = new ArrayList<>();

    public UserAdapter(Context context, int resource, ArrayList<UserModel> users) {
        super(context, resource, users);
        this.users.addAll(users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        UserModel user = getItem(position);
        ViewHolder viewHolder;

        //ViewHolder Pattern is used to avoid findViewById, every time getView is called
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.user_listview_element, parent, false);
            viewHolder.prenomTv = (TextView) convertView.findViewById(R.id.prenom);
            viewHolder.nomTv = (TextView) convertView.findViewById(R.id.nom);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
         // Populate the data into the template view using the data object
        viewHolder.prenomTv.setText(user.getPrenom());
        viewHolder.nomTv.setText(user.getNom());

        //color cell in grey if user not active, green if active
        if (user.isActive()) convertView.setBackgroundColor(Color.GREEN); else convertView.setBackgroundColor(Color.GRAY);
        // Return the completed view to render on screen
        return convertView;
    }
}
