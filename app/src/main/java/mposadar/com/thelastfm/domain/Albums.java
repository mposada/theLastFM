package mposadar.com.thelastfm.domain;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import mposadar.com.thelastfm.io.model.JsonKeys;

/**
 * Created by mposadar on 1/08/16.
 */
public class Albums {
    @SerializedName(JsonKeys.PLAY_COUNT)
    private String playcount;
    @SerializedName(JsonKeys.ALBUM_NAME)
    private String albumName;
    // get this in custom methods...
    private String artistName;
    private String imageLarge;
    private String imageMedium;

    public Albums() {
    }

    public Albums(String playcount, String albumName, String imageLarge, String imageMedium) {
        this.playcount = playcount;
        this.albumName = albumName;
        this.imageLarge = imageLarge;
        this.imageMedium = imageMedium;
    }

    public String getPlaycount() {
        return playcount;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getImageLarge() {
        return imageLarge;
    }

    public void setImageLarge(String imageLarge) {
        this.imageLarge = imageLarge;
    }

    public String getImageMedium() {
        return imageMedium;
    }

    public void setImageMedium(String imageMedium) {
        this.imageMedium = imageMedium;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * TODO: explain this...
     * @param albumData
     * @return
     */
    public static Albums buildAlbumFromJson(JsonObject albumData) {
        Gson gson = new Gson();
        return gson.fromJson(albumData, Albums.class);
    }

    /**
     * for the custom json parse lets extract the images from the imagesJson
     * and return this context with the images already set :)
     * @param imagesJson
     * @return
     */
    public Albums extractUrlsFromImagesArray(JsonArray imagesJson){
        String [] images = new String[2];

        for (int i = 0; i < imagesJson.size(); i++) {
            JsonObject currentImage = imagesJson.get(i).getAsJsonObject();

            String size = currentImage.get(JsonKeys.IMAGE_SIZE).getAsString();
            String url = currentImage.get(JsonKeys.IMAGE_URL).getAsString();

            if (url.isEmpty())
                url = null;

            if (size.matches(JsonKeys.IMAGE_MEDIUM) )
                images[0] = url;

            else if (size.matches(JsonKeys.IMAGE_LARGE))
                images[1] = url;

        }

        //Set the images
        setImageMedium(images[0]);
        setImageLarge(images[1]);

        return this;
    }

    /**
     * extract the artist name from the given artist json object
     * @param artistJson
     * @return
     */
    public Albums extractArtistName(JsonObject artistJson) {

        String artistName = artistJson.get(JsonKeys.ARTIST_NAME).getAsString();
        setArtistName(artistName);

        return this;
    }
}
