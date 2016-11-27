package danielrocha.americanasapi.utils;

import android.view.View;

import danielrocha.americanasapi.models.ProductModel;

/**
 * Created by danielrocha on 27/11/16.
 */

public interface OnItemClickListener {
    void onItemClick(View view, ProductModel productModel);
}
