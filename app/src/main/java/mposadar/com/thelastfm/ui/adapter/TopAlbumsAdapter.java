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
import mposadar.com.thelastfm.domain.Albums;

/**
 * Created by mposadar on 1/08/16.
 */
public class TopAlbumsAdapter extends RecyclerView.Adapter<TopAlbumsAdapter.TopAlbumsAdapterViewHolder> {
    // establish a touch with context
    Context context;
    // ArrayList of items that the RecyclerView will contain
    ArrayList<Albums> albums;

    public TopAlbumsAdapter(Context context) {
        this.context = context;
        // initialize ArrayList
        albums = new ArrayList<>();
    }

    /**
     * inflate the view
     *
     * @param parent
     * @param viewType
     * @return HypedArtistsViewHolder
     */
    @Override
    public TopAlbumsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*
         * inflate the view
         * last parameter (attachToRoot): if true, click events will notify parent class!
         */
        View view = LayoutInflater.from(context).inflate(R.layout.item_top_artists, parent, false);
        // return an instance of the ViewHolder nested class
        return new TopAlbumsAdapterViewHolder(view);
    }

    /**
     * bind the data to the RecyclerView
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(TopAlbumsAdapterViewHolder holder, int position) {
        // get the current itemView and arrayList value
        Albums currentAlbum = albums.get(position);

        // set values...
        holder.setAlbumName(currentAlbum.getAlbumName());
        holder.setAlbumListeners(currentAlbum.getPlaycount());
        holder.setArtistName(currentAlbum.getArtistName());
        Picasso.with(context)
                .load(currentAlbum.getImageMedium())
                .placeholder(R.drawable.artist_placeholder)
                .into(holder.getArtistImage());
    }

    /**
     * get the size of items that will have the RecyclerView
     * @return int
     */
    @Override
    public int getItemCount() {
        return albums.size();
    }

    /**
     * add new items to RecyclerView
     * @param albums
     */
    public void addAll(@NonNull ArrayList<Albums> albums) {
        // if array is null throw an exception
        if (albums == null) {
            throw new NullPointerException("Array must not be null - mp");
        }
        // insert the incoming array to the class array
        this.albums.addAll(albums);
        /*
         * notify the content has change
         * first parameter: positionStart (int)
         * second parameter: itemCount (int)
         */
        notifyItemRangeInserted(getItemCount() - 1, albums.size());
        // optional
        // notifyDataSetChanged();
    }

    /**
     * define the ViewHolder nested Class
     */
    public class TopAlbumsAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView albumName;
        TextView artistName;
        TextView albumListeners;
        ImageView albumImage;

        public TopAlbumsAdapterViewHolder(View itemView) {
            super(itemView);

            // get reference itemViews
            albumName  = (TextView) itemView.findViewById(R.id.lb_album_name);
            artistName = (TextView) itemView.findViewById(R.id.lb_artist_name);
            albumListeners = (TextView) itemView.findViewById(R.id.lb_play_count);
            albumImage = (ImageView) itemView.findViewById(R.id.img_albums);
        }

        // region getters
        public TextView getArtistName() {
            return albumName;
        }

        public ImageView getArtistImage() {
            return albumImage;
        }

        public TextView getAlbumName() {
            return albumName;
        }

        public TextView getAlbumListeners() {
            return albumListeners;
        }
        // endregion getters

        /**
         * set the value to albumName itemView
         * @param name
         */
        public void setAlbumName(String name) {
            albumName.setText(name);
        }

        /**
         * set the value to artistName itemView
         * @param name
         */
        public void setArtistName(String name) {
            artistName.setText(name);
        }

        /**
         * set the value to albumListeners itemView
         * @param count
         */
        public void setAlbumListeners(String count) {
            albumListeners.setText(count);
        }
    }
}
