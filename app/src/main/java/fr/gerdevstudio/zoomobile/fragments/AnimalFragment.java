package fr.gerdevstudio.zoomobile.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.gerdevstudio.zoomobile.R;
import fr.gerdevstudio.zoomobile.adapters.AnimalRecyclerViewAdapter;
import fr.gerdevstudio.zoomobile.datalayer.AnimalContent;
import fr.gerdevstudio.zoomobile.models.Animal;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnAnimalFragmentInteractionListener}
 * interface.
 */
public class AnimalFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnAnimalFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AnimalFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AnimalFragment newInstance(int columnCount) {
        AnimalFragment fragment = new AnimalFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animal, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            final RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            final List<Animal> animaux = new ArrayList<>();

            AnimalContent content = new AnimalContent();
            content.getAnimals(this, new AnimalContent.Callbacks() {
                @Override
                public void onGetAnimals(List<Animal> result) {
                    animaux.clear();
                    animaux.addAll(result);
                    if (recyclerView.getAdapter() != null) recyclerView.getAdapter().notifyDataSetChanged();
                }
            });
            recyclerView.setAdapter(new AnimalRecyclerViewAdapter(animaux, mListener));
            //recyclerView.setAdapter(new AnimalRecyclerViewAdapter( null,               mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAnimalFragmentInteractionListener) {
            mListener = (OnAnimalFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnEnclosInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnAnimalFragmentInteractionListener {
        // TODO: Update argument type and name
        void onAnimalSelected(Animal animal);
    }
}
