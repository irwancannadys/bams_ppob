package inventory.machtwatch.bamsppob.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import inventory.machtwatch.bamsppob.R
import java.io.File

fun ImageView.loadImage(image: String) {
    val requestOption = RequestOptions()
        .centerCrop()
        .error(R.drawable.ic_baseline_cloud_download_24)
    Glide.with(this)
        .load(image)
        .apply(requestOption)
        .into(this)
}