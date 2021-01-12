package inventory.machtwatch.bamsppob.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import inventory.machtwatch.bamsppob.R
import java.io.File
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class UtilsApp {

    companion object {

        private val LOCALE = Locale("in", "ID")

        fun formatPrice(price: Double): String {
            val pattern = "###,###.###"
            val nf = NumberFormat.getCurrencyInstance(LOCALE)
            val df = nf as DecimalFormat
            df.applyPattern(pattern)
            return "Rp${df.format(price)}"
        }

        fun formatPriceNotRp(price: Double): String {
            val pattern = "###,###.###"
            val nf = NumberFormat.getCurrencyInstance(LOCALE)
            val df = nf as DecimalFormat
            df.applyPattern(pattern)
            return "Pulsa ${df.format(price)}"
        }
    }
}