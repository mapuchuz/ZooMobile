package fr.gerdevstudio.colorcontacts.userlist1stversion;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.gerdevstudio.colorcontacts.R;
import fr.gerdevstudio.colorcontacts.user.UserContent;
import fr.gerdevstudio.colorcontacts.user.UserModel;

public class DetailsFragment extends Fragment {
    public final static String EXTRA_USER_NOM = "user_nom";
    public final static String EXTRA_USER_PRENOM = "user_prenom";
    public final static String EXTRA_USER_INDEX = "user_index";
    public final static String EXTRA_IS_ACTIVE = "user_is_active";
    private boolean isActive = false;
    private int user_position = -1;


    private CallBacks cb = new CallBacks() {
        @Override
        public void onUserModified(int position) {

        }
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Activity activity = super.getActivity();
        if (activity instanceof CallBacks) {
            cb = (CallBacks) activity;
        } else {
            throw new ClassCastException(getActivity().getLocalClassName() + " must implement " + CallBacks.class.getCanonicalName());
        }
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.details_fragment, container, false);
        TextView nomTv = (TextView) layout.findViewById(R.id.nom);
        TextView prenomTv = (TextView) layout.findViewById(R.id.prenom);
        CheckBox isActiveCb = (CheckBox) layout.findViewById(R.id.is_active_checkbox);

        isActiveCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isActive = isChecked;

            }
        });

        Button buttonValidate = (Button) layout.findViewById(R.id.button_validate);

        // on renvoit par callback qu'on a finit de modifier l'useur
        buttonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb.onUserModified(user_position);
                UserModel user = UserContent.ITEMS.get(user_position);
                user.setActive(isActive);
            }
        });

        Bundle params = getArguments();
        if (params != null) {
            nomTv.setText(params.getString(EXTRA_USER_NOM));
            prenomTv.setText(params.getString(EXTRA_USER_PRENOM));
            if (params.getBoolean(EXTRA_IS_ACTIVE, false)) isActiveCb.setChecked(true);
            user_position = params.getInt(EXTRA_USER_INDEX, 0);
        }
        return layout;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        cb = new CallBacks() {
            @Override
            public void onUserModified(int position) {
            }
        };
    }

    public interface CallBacks {
        void onUserModified(int position);
    }
}
