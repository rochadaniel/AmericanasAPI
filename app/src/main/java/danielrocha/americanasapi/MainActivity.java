package danielrocha.americanasapi;

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
import danielrocha.americanasapi.models.ProductModel;
import danielrocha.americanasapi.services.ADSProductsAPI;
import danielrocha.americanasapi.services.ServiceGenerator;
import danielrocha.americanasapi.utils.OnItemClickListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAGLOG = getClass().getName();
    private LinearLayoutManager linearLayoutManager;
    private ProductsListAdapter productsListAdapter;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initRecyclerView();

        getAmericanasADSProducts();
    }

    private void initRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(
                MainActivity.this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
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
                            Toast.makeText(MainActivity.this, "teste", Toast.LENGTH_SHORT).show();
                        }
                    });
                    recyclerView.setAdapter(productsListAdapter);
                    //Log.i(TAGLOG, "encontrados: " + result.size());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                Log.e(TAGLOG, "failure");
            }
        });

    }
}
