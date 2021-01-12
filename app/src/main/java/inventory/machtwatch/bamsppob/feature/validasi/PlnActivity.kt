package inventory.machtwatch.bamsppob.feature.validasi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import inventory.machtwatch.bamsppob.R

class PlnActivity : AppCompatActivity() {

    companion object {
        fun startToPLNActivity(activity: Activity) {
            val intent = Intent(activity, PlnActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pln)
    }

    private fun initObserver() {

    }
}