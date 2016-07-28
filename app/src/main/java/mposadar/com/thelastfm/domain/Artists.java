package mposadar.com.thelastfm.domain;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import mposadar.com.thelastfm.io.model.JsonKeys;

/**
 * Created by mposadar on 29/06/16.
 */
public class Artists {

    @SerializedName(JsonKeys.ARTIST_NAME)
    private String name;
    private String imageLarge;
    private String imageMedium;

    public Artists(String name, String imageLarge, String imageMedium) {
        this.name = name;
        this.imageLarge = imageLarge;
        this.imageMedium = imageMedium;
    }

    public Artists() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public static Artists buildArtistFromJson(JsonObject artistData) {
        Gson gson = new Gson();
        return gson.fromJson(artistData, Artists.class);
    }


    public Artists extractUrlsFromImagesArray(JsonArray imagesJson){
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
}
