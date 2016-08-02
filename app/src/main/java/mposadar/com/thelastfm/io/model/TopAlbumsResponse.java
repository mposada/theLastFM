package mposadar.com.thelastfm.io.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import mposadar.com.thelastfm.domain.Albums;

/**
 * Created by mposadar on 1/08/16.
 */
public class TopAlbumsResponse {

    @SerializedName(JsonKeys.TOP_ALBUMS)
    @Expose
    private MainResponse mainResponse;

    public ArrayList<Albums> getAlbums() {
        return mainResponse.albums;
    }

    /**
     *
     * @param albums
     */
    public void setAlbums(ArrayList<Albums> albums) {
        mainResponse.albums = albums;
    }

    /**
     * Class MainResponse will have a albums Array
     */
    private class MainResponse {
        private ArrayList<Albums> albums;
    }
}
