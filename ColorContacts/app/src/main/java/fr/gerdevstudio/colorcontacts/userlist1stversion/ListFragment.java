package fr.gerdevstudio.colorcontacts.userlist1stversion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import fr.gerdevstudio.colorcontacts.R;
import fr.gerdevstudio.colorcontacts.user.UserAdapter;
import fr.gerdevstudio.colorcontacts.user.UserModel;

public class ListFragment extends Fragment {

    public final static int REQUEST_CODE_USER_DETAILS = 453;

    //list of users we are working with
    private ArrayList<UserModel> users;
    private UserAdapter userAdapter;

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // Check which request we're responding to
//        if (requestCode == ListFragment.REQUEST_CODE_USER_DETAILS) {
//            // Make sure the request was successful
//            if (resultCode == RESULT_OK) {
//                if (data != null) {
//                    int index = data.getIntExtra(EXTRA_USER_INDEX,-1);
//                    if (index!=-1)
//                    {
//                        users.get(index).setActive(data.getBooleanExtra(EXTRA_IS_ACTIVE,false));
//                        userAdapter.notifyDataSetChanged();
//                    }
//                }
//            }
//        }
//    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.list_fragment, container, false);


        super.onCreate(savedInstanceState);
        ListView userList = (ListView) layout.findViewById(R.id.user_listview);
        users = new ArrayList<>();
        //users generation
        for (int i = 0; i < 1000; i++) {
            users.add(new UserModel("utilisateur" + (i + 1), "autre"));
        }
        userAdapter = new UserAdapter(getActivity(), R.layout.user_listview_element, users);
        userList.setAdapter(userAdapter);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), DetailsFragment.class);
                i.putExtra(DetailsFragment.EXTRA_USER_NOM, userAdapter.getItem(position).getNom());
                i.putExtra(DetailsFragment.EXTRA_USER_PRENOM, userAdapter.getItem(position).getPrenom());
                i.putExtra(DetailsFragment.EXTRA_IS_ACTIVE, userAdapter.getItem(position).isActive());
                i.putExtra(DetailsFragment.EXTRA_USER_INDEX, position);
                startActivityForResult(i, REQUEST_CODE_USER_DETAILS);
            }
        });

        return layout;
    }

    public void refreshData()
    {

    }
}

