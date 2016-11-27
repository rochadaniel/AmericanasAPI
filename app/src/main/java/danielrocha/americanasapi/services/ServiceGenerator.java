package danielrocha.americanasapi.services;

import danielrocha.americanasapi.utils.OkHttpHelper;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by danielrocha on 27/11/16.
 */

public class ServiceGenerator {
    public static final String API_BASE_URL = "http://b2wadsapiadapter-env.sa-east-1.elasticbeanstalk.com/";

    private static Retrofit retrofit;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());


    public static <S> S createService(Class<S> serviceClass) {
        OkHttpClient client = OkHttpHelper.getHttpClient();
        retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

    public static Retrofit getRetrofit() {
        if(retrofit == null) {
            OkHttpClient client = httpClient.build();
            return builder.client(client).build();
        } else return retrofit;
    }
}
