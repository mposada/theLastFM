package mposadar.com.thelastfm.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mposadar.com.thelastfm.R;
import mposadar.com.thelastfm.domain.Artists;

/**
 * Created by mposadar on 22/07/16.
 */
public class HypedArtistsAdapter extends RecyclerView.Adapter<HypedArtistsAdapter.HypedArtistsViewHolder> {

    // establish a touch with context
    Context context;
    // ArrayList of items that the RecyclerView will contain
    ArrayList<Artists> artists;

    public HypedArtistsAdapter(Context context) {
        this.context = context;
        // initialize ArrayList
        artists = new ArrayList<>();
    }

    /**
     * inflate the view
     *
     * @param parent
     * @param viewType
     * @return HypedArtistsViewHolder
     */
    @Override
    public HypedArtistsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*
         * inflate the view
         * last parameter (attachToRoot): if true, click events will notify parent class!
         */
        View view = LayoutInflater.from(context).inflate(R.layout.item_hyped_artists, parent, false);
        // return an instance of the ViewHolder nested class
        return new HypedArtistsViewHolder(view);
    }

    /**
     * bind the data to the RecyclerView
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(HypedArtistsViewHolder holder, int position) {
        // get the current itemView and arrayList value
        Artists currentArtists = artists.get(position);

        // set values...
        holder.setArtistName(currentArtists.getName());
        Picasso.with(context)
                .load(currentArtists.getImage())
                .placeholder(R.drawable.artist_placeholder)
                .into(holder.getArtistImage());
    }

    /**
     * get the size of items that will have the RecyclerView
     * @return int
     */
    @Override
    public int getItemCount() {
        return artists.size();
    }

    /**
     * add new items to RecyclerView
     * @param artists
     */
    public void addAll(@NonNull ArrayList<Artists> artists) {
        // if array is null throw an exception
        if (artists == null) {
            throw new NullPointerException("Array must not be null - mp");
        }
        // insert the incoming array to the class array
        this.artists.addAll(artists);
        /*
         * notify the content has change
         * first parameter: positionStart (int)
         * second parameter: itemCount (int)
         */
        notifyItemRangeInserted(getItemCount() - 1, artists.size());
        // optional
        // notifyDataSetChanged();
    }

    /**
     * define the ViewHolder nested Class
     */
    public class HypedArtistsViewHolder extends RecyclerView.ViewHolder {

        TextView artistName;
        ImageView artistImage;

        public HypedArtistsViewHolder(View itemView) {
            super(itemView);

            // get reference itemViews
            artistName  = (TextView) itemView.findViewById(R.id.txt_name);
            artistImage = (ImageView) itemView.findViewById(R.id.img_artists);
        }

        // region getters
        public TextView getArtistName() {
            return artistName;
        }

        public ImageView getArtistImage() {
            return artistImage;
        }
        // endregion getters

        /**
         * set the value to artistName itemView
         * @param name
         */
        public void setArtistName(String name) {
            artistName.setText(name);
        }
    }
}
