package danielrocha.americanasapi;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import danielrocha.americanasapi.adapters.ProductsListAdapter;
import danielrocha.americanasapi.databinding.MainBinding;
import danielrocha.americanasapi.models.ProductModel;
import danielrocha.americanasapi.services.ADSProductsAPI;
import danielrocha.americanasapi.services.ServiceGenerator;
import danielrocha.americanasapi.utils.OnItemClickListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAGLOG = getClass().getName();
    private ProductsListAdapter productsListAdapter;
    private MainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

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
                    productsListAdapter = new ProductsListAdapter(response.body(), new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, ProductModel productModel) {
                            String url = getUrlFromModel(productModel);
                            if (!url.startsWith("http://") && !url.startsWith("https://")) url = "http://" + url;
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(browserIntent);
                        }
                    });
                    mainBinding.recyclerView.setAdapter(productsListAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                Log.e(TAGLOG, "failure");
            }
        });

    }

    private String getUrlFromModel(ProductModel productModel) {
        String result = null;

        String[] vetor = productModel.getUrl().split("redirect_url=");
        if(vetor.length == 2) {
            result = vetor[1].replaceAll("%3A", ":").replaceAll("%2F", "/").replaceAll("%3F", "?");
        }

        return result;
    }
}
