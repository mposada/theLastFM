package mposadar.com.thelastfm.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mposadar.com.thelastfm.io.deserializer.TopArtistDeserializer;
import mposadar.com.thelastfm.io.model.TopArtistsResponse;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The ServiceGenerator class uses Retrofit’s Retrofit-Builder to create
 * a new REST client with the given API base url
 *
 * Created by mposadar on 22/07/16.
 */
public class ServiceGenerator {

    private static final String TAG = ServiceGenerator.class.getSimpleName();

    // base url of the api
    private static final String BASE_URL = ApiConstants.URL_BASE;

    // rest client with the api base url
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(factory());

    // create a http client
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass) {
        builder.client(httpClient.build());
        Retrofit retrofit = builder.build();
        return retrofit.create(serviceClass);
    }

    /**
     * custom GsonConverterFactory
     * @return
     */
    private static GsonConverterFactory factory() {
        /*
        * register the deserializer
        * @param type the type definition for the type adapter being registered
        * @param typeAdapter This object must implement at least one of the {@link TypeAdapter}
        */
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(TopArtistsResponse.class, new TopArtistDeserializer());
        Gson gson = gsonBuilder.create();
        return GsonConverterFactory.create(gson);
    };
}
