package fr.gerdevstudio.zoomobile.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.gerdevstudio.zoomobile.R;
import fr.gerdevstudio.zoomobile.fragments.AnimalFragment;
import fr.gerdevstudio.zoomobile.models.Animal;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Animal} and makes a call to the
 * specified {@link AnimalFragment.OnAnimalFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class AnimalRecyclerViewAdapter extends RecyclerView.Adapter<AnimalRecyclerViewAdapter.ViewHolder> {

    private final List<Animal> mAnimals;
    private final AnimalFragment.OnAnimalFragmentInteractionListener mListener;
    private Context context;

    public AnimalRecyclerViewAdapter(Context context,List<Animal> items, AnimalFragment.OnAnimalFragmentInteractionListener listener) {
        mAnimals = items;
        mListener = listener;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.animal_recyclerview_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Animal animal = mAnimals.get(position);
        holder.mAnimalNameView.setText(animal.getName());
        holder.mAnimalEspeceView.setText(animal.getEspece());
        holder.mAnimalDescriptionView.setText(animal.getDescription());
        if (animal.getPhoto()!=null) {
            holder.mAnimalPhotoImageView.setImageResource(context.getResources().getIdentifier("drawable/"+animal.getPhoto(),null,context.getPackageName()));
        }
        //todo add image support

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onAnimalSelected(animal);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAnimals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mAnimalNameView;
        public final TextView mAnimalEspeceView;
        public final TextView mAnimalDescriptionView;
        public final  ImageView mAnimalPhotoImageView;
        public Animal mAnimal;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mAnimalNameView = (TextView) view.findViewById(R.id.animal_name);
            mAnimalEspeceView = (TextView) view.findViewById(R.id.animal_espece);
            mAnimalDescriptionView = (TextView) view.findViewById(R.id.animal_description);
            mAnimalPhotoImageView = (ImageView) view.findViewById(R.id.animal_photo);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mAnimalEspeceView.getText() + "'";
        }
    }
}
