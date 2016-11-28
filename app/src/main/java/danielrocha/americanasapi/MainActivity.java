package danielrocha.americanasapi;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import danielrocha.americanasapi.adapters.ProductsListAdapter;
import danielrocha.americanasapi.databinding.MainBinding;
import danielrocha.americanasapi.models.ProductModel;
import danielrocha.americanasapi.services.ADSProductsAPI;
import danielrocha.americanasapi.services.ServiceGenerator;
import danielrocha.americanasapi.utils.ConnectivityHelper;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final String TAGLOG = getClass().getName();
    private ProductsListAdapter productsListAdapter;
    private MainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setPresenter(this);
        getAmericanasADSProducts();
    }

    public void getAmericanasADSProducts() {
        try {
            if(ConnectivityHelper.isConnected(MainActivity.this)) {
                mainBinding.setIsLoading(true);
                mainBinding.setIsConnected(true);
                ADSProductsAPI adsProductsAPI = ServiceGenerator.createService(ADSProductsAPI.class);

                Observable<ArrayList<ProductModel>> result = adsProductsAPI.getAmericanasADSProducts(getString(R.string.api), getResources().getBoolean(R.bool.home),
                        getString(R.string.referer), getString(R.string.userId));

                Subscription subscription = result
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                productModels -> {
                                    if (productModels != null && productModels.size() > 0) {
                                        updateList(productModels);
                                    } else {
                                        mainBinding.setIsLoading(false);
                                        Toast.makeText(MainActivity.this, "Nenhum produto encontrado", Toast.LENGTH_SHORT).show();
                                    }
                                },
                                Throwable::printStackTrace,
                                () -> Log.i(TAGLOG, "Completou!")
                        );
            } else {
                mainBinding.setIsLoading(false);
                mainBinding.setIsConnected(false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void updateList(ArrayList<ProductModel> productModels) {
        mainBinding.setIsLoading(false);
        productsListAdapter = new ProductsListAdapter(productModels, (View view, ProductModel productModel) -> {
            String url = getUrlFromModel(productModel);
            if(url != null) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });
        mainBinding.recyclerView.setAdapter(productsListAdapter);
    }

    private String getUrlFromModel(ProductModel productModel) {
        String result = null;

        try {
            String[] vetor = productModel.getUrl().split("redirect_url=");
            if (vetor.length == 2) {
                result = vetor[1].replaceAll("%3A", ":").replaceAll("%2F", "/").replaceAll("%3F", "?");

                if (!result.startsWith("http://") && !result.startsWith("https://"))
                    result = "http://" + result;
            }

        } catch (Exception e) {
            Log.e(TAGLOG, e.getLocalizedMessage());
        }

        return result;
    }

}
