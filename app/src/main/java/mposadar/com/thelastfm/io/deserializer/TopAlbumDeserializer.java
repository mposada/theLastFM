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

import mposadar.com.thelastfm.domain.Albums;
import mposadar.com.thelastfm.io.model.JsonKeys;
import mposadar.com.thelastfm.io.model.TopAlbumsResponse;

/**
 * Created by mposadar on 1/08/16.
 */
public class TopAlbumDeserializer implements JsonDeserializer<TopAlbumsResponse> {
    @Override
    public TopAlbumsResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        // let deserialize the json response and pass the data to TopArtistsResponse class
        TopAlbumsResponse response = gson.fromJson(json, TopAlbumsResponse.class);
        // get the complete json object
        JsonObject albumsResponseData = json.getAsJsonObject().getAsJsonObject(JsonKeys.TOP_ALBUMS);
        // get the json array of artists
        JsonArray albumsArray = albumsResponseData.getAsJsonArray(JsonKeys.ALBUM);
        // convert the jsonArray into objects of TopAlbumsResponse Class
        response.setAlbums(extractAlbumsFromJsonArray(albumsArray));

        return response;
    }

    /**
     * extract the album data from json artist array
     * @param jsonArray
     * @return
     */
    private ArrayList<Albums> extractAlbumsFromJsonArray(JsonArray jsonArray) {
        ArrayList<Albums> albums = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject albumData = jsonArray.get(i).getAsJsonObject();

            Albums currentAlbum = Albums.buildAlbumFromJson(albumData)
                    .extractUrlsFromImagesArray(albumData.getAsJsonArray(JsonKeys.ARTIST_IMAGES))
                    .extractArtistName(albumData.getAsJsonObject(JsonKeys.ARTIST));

            albums.add(currentAlbum);
        }

        return albums;
    }
}
