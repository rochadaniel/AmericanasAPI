package danielrocha.americanasapi.services;

import java.util.ArrayList;

import danielrocha.americanasapi.models.ProductModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by danielrocha on 27/11/16.
 */

public interface ADSProductsAPI {
    @GET("ads/americanas")
    Call<ArrayList<ProductModel>> getAmericanasADSProducts(@Query("api") String api, @Query("home") boolean home,
                                                           @Query("referer") String referer, @Query("userId") String userId);
}
