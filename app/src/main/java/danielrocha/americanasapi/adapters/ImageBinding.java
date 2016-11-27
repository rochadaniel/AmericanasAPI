package danielrocha.americanasapi.adapters;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by danielrocha on 27/11/16.
 */

public class ImageBinding {
    @BindingAdapter({"android:src"})
    public static void loadImage(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
