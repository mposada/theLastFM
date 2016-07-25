package mposadar.com.thelastfm.io;

import java.util.Map;

import mposadar.com.thelastfm.io.model.TopArtistsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * here we'll find all endpoints
 * Created by mposadar on 22/07/16.
 */
public interface ApiService {
    /*
     * define the end-point structure
     */
    @GET(ApiConstants.PATH_VERSION)
    Call<TopArtistsResponse> getTopArtists(@QueryMap Map<String, String> params);
}
