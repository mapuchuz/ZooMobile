package fr.gerdevstudio.colorcontacts.userlist2ndVersion;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import fr.gerdevstudio.colorcontacts.R;
import fr.gerdevstudio.colorcontacts.user.UserContent;
import fr.gerdevstudio.colorcontacts.user.UserModel;
import fr.gerdevstudio.colorcontacts.userlist1stversion.DetailsFragment;

import java.util.List;

/**
 * An activity representing a list of Users. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link UserDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MainActivity extends AppCompatActivity implements DetailsFragment.CallBacks{

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private RecyclerView recyclerView;

    @Override // update recyclerview data
    protected void onRestart() {
        super.onRestart();
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermodel_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.usermodel_list);
        assert recyclerView != null;
        setupRecyclerView(recyclerView);

        if (findViewById(R.id.usermodel_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(UserContent.ITEMS));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<UserModel> mValues;

        public SimpleItemRecyclerViewAdapter(List<UserModel> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.user_listview_element, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            UserModel user = mValues.get(position);
            holder.mPrenom.setText(user.getPrenom());
            holder.mNom.setText(user.getNom());
            if (user.isActive()) holder.mView.setBackgroundColor(Color.GREEN); else holder.mView.setBackgroundColor(Color.GRAY);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putInt(DetailsFragment.EXTRA_USER_INDEX, position);
                        arguments.putString(DetailsFragment.EXTRA_USER_NOM,mValues.get(position).getNom());
                        arguments.putString(DetailsFragment.EXTRA_USER_PRENOM,mValues.get(position).getPrenom());
                        DetailsFragment fragment = new DetailsFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.usermodel_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, UserDetailActivity.class);
                        intent.putExtra(DetailsFragment.EXTRA_USER_INDEX, position);
                        intent.putExtra(DetailsFragment.EXTRA_USER_NOM,mValues.get(position).getNom());
                        intent.putExtra(DetailsFragment.EXTRA_USER_PRENOM,mValues.get(position).getPrenom());

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mPrenom;
            public final TextView mNom;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mPrenom = (TextView) view.findViewById(R.id.prenom);
                mNom = (TextView) view.findViewById(R.id.nom);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mNom.getText() + "'";
            }
        }
    }

    @Override
    public void onUserModified(int position) {
        setupRecyclerView(recyclerView);
    }
}
