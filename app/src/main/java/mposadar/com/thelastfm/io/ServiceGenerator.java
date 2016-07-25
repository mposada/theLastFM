package mposadar.com.thelastfm.io;

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

    // base url of the api
    private static final String BASE_URL = ApiConstants.URL_BASE;

    // rest client with the api base url
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    // create a http client
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass) {
        builder.client(httpClient.build());
        Retrofit retrofit = builder.build();
        return retrofit.create(serviceClass);
    }
}
