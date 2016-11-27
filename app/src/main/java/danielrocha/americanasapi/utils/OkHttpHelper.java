package danielrocha.americanasapi.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by danielrocha on 27/11/16.
 */

public class OkHttpHelper {
    private static OkHttpClient.Builder httpBuilder;
    private static OkHttpClient httpClient;

    public static OkHttpClient getHttpClient(final String token) {
        httpBuilder = new OkHttpClient.Builder();
        httpBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Content-Type", "application/json")
                        .header("X-Auth-Token", token)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        return httpBuilder.build();
    }

    public static OkHttpClient getHttpClient() {
        httpBuilder = new OkHttpClient.Builder();
        httpBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Content-Type", "application/json")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        return httpBuilder.build();
    }
}
