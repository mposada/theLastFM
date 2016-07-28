package mposadar.com.thelastfm.io.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import mposadar.com.thelastfm.domain.Artists;
import mposadar.com.thelastfm.io.model.JsonKeys;
import mposadar.com.thelastfm.io.model.TopArtistsResponse;

/**
 * Created by mposadar on 28/07/16.
 */
public class TopArtistDeserializer implements JsonDeserializer<TopArtistsResponse> {
    @Override
    public TopArtistsResponse deserialize(JsonElement json, Type typeOfT,
                                          JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        // let deserialize the json response and pass the data to TopArtistsResponse class
        TopArtistsResponse response = gson.fromJson(json, TopArtistsResponse.class);
        // get the complete json object
        JsonObject artistsResponseData = json.getAsJsonObject().getAsJsonObject(JsonKeys.TOP_ARTISTS);
        // get the json array of artists
        JsonArray artistsArray = artistsResponseData.getAsJsonArray(JsonKeys.ARTIST);
        // convert the jsonArray into objects of TopArtistsResponse Class
        response.setArtists(extractArtistsFromJsonArray(artistsArray));

        return response;
    }

    /**
     * extract the artist data from json artist array
     * @param artistsArray
     * @return
     */
    private ArrayList<Artists> extractArtistsFromJsonArray(JsonArray artistsArray) {
        ArrayList<Artists> artists = new ArrayList<>();

        for (int i = 0; i < artistsArray.size(); i++) {
            JsonObject artistData = artistsArray.get(i).getAsJsonObject();

            Artists currentArtist = Artists.buildArtistFromJson(artistData)
                    .extractUrlsFromImagesArray(artistData.getAsJsonArray(JsonKeys.ARTIST_IMAGES));

            artists.add(currentArtist);
        }

        return artists;
    }
}
