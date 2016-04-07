package fr.gerdevstudio.zoomobile.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fr.gerdevstudio.zoomobile.R;
import fr.gerdevstudio.zoomobile.fragments.EnclosFragment;;
import fr.gerdevstudio.zoomobile.models.Enclos;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Enclos} and makes a call to the
 * specified {@link EnclosFragment.OnEnclosInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class EnclosRecyclerViewAdapter extends RecyclerView.Adapter<EnclosRecyclerViewAdapter.ViewHolder> {

    private final List<Enclos> mListEnclos;
    private final EnclosFragment.OnEnclosInteractionListener mListener;

    public EnclosRecyclerViewAdapter(List<Enclos> items, EnclosFragment.OnEnclosInteractionListener listener) {
        mListEnclos = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.enclos_recyclerview_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mEnclos = mListEnclos.get(position);
        holder.mIdView.setText(Integer.toString(position));
        holder.mContentView.setText(mListEnclos.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onEnclosSelected(holder.mEnclos);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListEnclos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Enclos mEnclos;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.animal_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
