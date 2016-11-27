package danielrocha.americanasapi.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import danielrocha.americanasapi.databinding.ProductsListItemBinding;

/**
 * Created by danielrocha on 27/11/16.
 */

public class ProductsListViewHolder extends RecyclerView.ViewHolder {

    public ProductsListItemBinding productsListItemBinding;

    public ProductsListViewHolder(ProductsListItemBinding productsListItemBinding) {
        super(productsListItemBinding.getRoot());
        this.productsListItemBinding = productsListItemBinding;
    }
}
