package vyacheslav.lukyanov.com.testtaskandroiddev.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import vyacheslav.lukyanov.com.testtaskandroiddev.BuildConfig;

public class ApiClient {

    private static final HttpLoggingInterceptor.Level LOG_LEVEL = BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE;
    private static String ENDPOINT = "https://udimi.com/api/";

    public static RestApiClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(LOG_LEVEL);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(logging);

        String header = "RtDzfLCFS70I1rNPQ2b94Bu1V5F4iq30";

        Interceptor headerAuthorizationInterceptor;
            headerAuthorizationInterceptor = chain -> {
                okhttp3.Request request = chain.request();
                Headers headers = request.headers().newBuilder().add("Auth", header).build();
                request = request.newBuilder().headers(headers).build();
                return chain.proceed(request);
            };

        builder.addInterceptor(headerAuthorizationInterceptor);

        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(ENDPOINT)
                .client(client)
                .build();

        return retrofit.create(RestApiClient.class);
    }

    public interface RestApiClient {
        @POST("profile")
        Call<ProfileResponse> getProfile(@Body HashMap<String, Object> body);

        @POST("profile/buyOptions")
        Call<BuyOptionsResponse> getProfileBuyOptions(@Body HashMap<String, Object> body);

    }
}