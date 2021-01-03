package inventory.machtwatch.bamsppob.feature

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import inventory.machtwatch.bamsppob.R

class PulsaActivity : AppCompatActivity() {

    companion object {
        fun startToPulsaActivity(activity: Activity) {
            val intent = Intent(activity, PulsaActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulsa)
    }
}