package inventory.machtwatch.bamsppob

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import inventory.machtwatch.bamsppob.base.BaseActivity
import inventory.machtwatch.bamsppob.base.Status
import inventory.machtwatch.bamsppob.feature.checkout.CheckoutActivity
import inventory.machtwatch.bamsppob.feature.history.HistoryActivity
import inventory.machtwatch.bamsppob.feature.history.HistoryAdapter
import inventory.machtwatch.bamsppob.feature.history.HistoryViewModel
import inventory.machtwatch.bamsppob.feature.model.DataTransaction
import inventory.machtwatch.bamsppob.feature.model.DenomList
import inventory.machtwatch.bamsppob.feature.validasi.*
import inventory.machtwatch.bamsppob.utils.UtilsApp
import kotlinx.android.synthetic.main.activity_history_transaction.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : BaseActivity<HistoryViewModel>() {

    override fun initToolbar() {

    }

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

        initObserver()
        mViewModel?.triggerSaldo?.value = true
        initRouterFeature()
    }

    private fun initRouterFeature() {

        rl_info.setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
        }

        rl_pulsa.setOnClickListener {
            PulsaActivity.startToPulsaActivity(this)
        }

        rl_paket_data.setOnClickListener {
            PaketDataActivity.startToPaketDataActivity(this)
        }

        rl_pln.setOnClickListener {
            PlnActivity.startToPLNActivity(this)
        }

        rl_pln_abudemen.setOnClickListener {
            PlnPostPaidActivity.startToPlnAbodemenActivity(this)
        }

        rl_history.setOnClickListener {
            HistoryActivity.startToHistory(this)
        }
    }

    private fun initObserver() {
        mViewModel?.getSaldo?.observe(this, Observer { resource ->
            when (resource.status) {
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESS -> {
                    hideLoading()
                    resource.data?.let {
                        tv_saldo.text =  UtilsApp.formatPrice(it.data.balance)
                    }
                }

                Status.ERROR -> {
                    hideLoading()
                }
            }
        })
    }
}