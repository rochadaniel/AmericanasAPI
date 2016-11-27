package danielrocha.americanasapi.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import danielrocha.americanasapi.R;
import danielrocha.americanasapi.databinding.ProductsListItemBinding;
import danielrocha.americanasapi.models.ProductModel;
import danielrocha.americanasapi.utils.OnItemClickListener;
import danielrocha.americanasapi.viewHolders.ProductsListViewHolder;

/**
 * Created by danielrocha on 27/11/16.
 */

public class ProductsListAdapter extends
        RecyclerView.Adapter<ProductsListViewHolder> {

    List<ProductModel> mBooks;
    OnItemClickListener mListener;

    public ProductsListAdapter(List<ProductModel> books,
                               OnItemClickListener listener) {
        mBooks = books;
        mListener = listener;
    }

    @Override
    public ProductsListViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        ProductsListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.products_list_item,
                parent,
                false);

        final ProductsListViewHolder vh = new ProductsListViewHolder(binding);
        return vh;
    }

    @Override
    public void onBindViewHolder(ProductsListViewHolder holder, int pos) {
        final ProductModel book = mBooks.get(pos);
        holder.productsListItemBinding.setMymodel(book);
        holder.productsListItemBinding.executePendingBindings();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(view, book);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBooks != null ? mBooks.size() : 0;
    }

}
