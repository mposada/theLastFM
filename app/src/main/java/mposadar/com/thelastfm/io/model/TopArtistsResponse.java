package mposadar.com.thelastfm.io.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import mposadar.com.thelastfm.domain.Artists;

/**
 * Created by mposadar on 24/07/16.
 */
public class TopArtistsResponse {

    @SerializedName(JsonKeys.TOP_ARTISTS)
    @Expose
    private MainResponse mainResponse;

    /**
     * get the artists
     * @return
     */
    public ArrayList<Artists> getArtists(){
        return  mainResponse.artists;
    }

    /**
     * set the artists
     * @param artists
     */
    public void setArtists(ArrayList<Artists> artists) {
        mainResponse.artists = artists;
    }

    /**
     * Class MainResponse will have a artists Array
     */
    private class MainResponse {

        private ArrayList<Artists> artists;

    }
}
