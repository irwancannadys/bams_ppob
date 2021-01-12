package inventory.machtwatch.bamsppob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import inventory.machtwatch.bamsppob.feature.validasi.PlnActivity
import inventory.machtwatch.bamsppob.feature.validasi.PulsaActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
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