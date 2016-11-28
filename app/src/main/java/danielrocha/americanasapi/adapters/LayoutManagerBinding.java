package danielrocha.americanasapi.adapters;

import android.databinding.BindingAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by danielrocha on 28/11/16.
 */

public class LayoutManagerBinding {
    @BindingAdapter({"bind:layout_manager"})
    public static void setLayoutManager(
            RecyclerView recyclerView, String layout){
        setLayoutManager(recyclerView, layout, 1);
    }

    @BindingAdapter({"bind:layout_manager", "bind:columns"})
    public static void setLayoutManager(
            RecyclerView recyclerView,
            String layout, int columns){

        if ("linear".equalsIgnoreCase(layout)){
            recyclerView.setLayoutManager(
                    new LinearLayoutManager(
                            recyclerView.getContext(),
                            LinearLayoutManager.VERTICAL, false));
        } else if ("grid".equalsIgnoreCase(layout)){
            recyclerView.setLayoutManager(
                    new GridLayoutManager(
                            recyclerView.getContext(), columns));
        }
    }
}
