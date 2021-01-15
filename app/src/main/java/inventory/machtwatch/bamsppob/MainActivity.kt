package inventory.machtwatch.bamsppob

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import inventory.machtwatch.bamsppob.feature.checkout.CheckoutActivity
import inventory.machtwatch.bamsppob.feature.model.DenomList
import inventory.machtwatch.bamsppob.feature.validasi.PlnActivity
import inventory.machtwatch.bamsppob.feature.validasi.PulsaActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    companion object {
        const val DATA_CHECKOUT = "data"
        const val NUMBER_USER = "number"

        fun startToCheckout(activity: Activity) {
            val intent = Intent(activity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRouterFeature()
    }

    private fun initRouterFeature() {
        rl_pulsa.setOnClickListener {
            PulsaActivity.startToPulsaActivity(this)
        }

        rl_pln.setOnClickListener {
            PlnActivity.startToPLNActivity(this)
        }
    }
}