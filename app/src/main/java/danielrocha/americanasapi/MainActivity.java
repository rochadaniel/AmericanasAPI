package danielrocha.americanasapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import danielrocha.americanasapi.models.ProductModel;
import danielrocha.americanasapi.services.ADSProductsAPI;
import danielrocha.americanasapi.services.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAGLOG = getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAmericanasADSProducts();
    }

    private void getAmericanasADSProducts() {

        Call<ArrayList<ProductModel>> call;

        ADSProductsAPI adsProductsAPI = ServiceGenerator.createService(ADSProductsAPI.class);

        call = adsProductsAPI.getAmericanasADSProducts(getString(R.string.api), getResources().getBoolean(R.bool.home),
                getString(R.string.referer), getString(R.string.userId));

        call.enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                if(!response.isSuccessful()) {
                    Log.e(TAGLOG, "error");
                } else {
                    ArrayList<ProductModel> result = response.body();
                    Log.i(TAGLOG, "encontrados: " + result.size());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                Log.e(TAGLOG, "failure");
            }
        });

    }
}
