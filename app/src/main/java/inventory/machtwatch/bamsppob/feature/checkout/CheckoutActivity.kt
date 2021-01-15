package inventory.machtwatch.bamsppob.feature.checkout

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import inventory.machtwatch.bamsppob.MainActivity
import inventory.machtwatch.bamsppob.R
import inventory.machtwatch.bamsppob.base.BaseActivity
import inventory.machtwatch.bamsppob.base.Status
import inventory.machtwatch.bamsppob.feature.listdenom.ListDenominationPulsa
import inventory.machtwatch.bamsppob.feature.model.CheckoutResponse
import inventory.machtwatch.bamsppob.feature.model.DataCheckout
import inventory.machtwatch.bamsppob.feature.model.DenomList
import inventory.machtwatch.bamsppob.utils.UtilsApp
import inventory.machtwatch.bamsppob.utils.loadImage
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.dialog_status_transaction.view.*
import java.io.Serializable

class CheckoutActivity : BaseActivity<CheckoutViewModel>() {

    var number = ""

    companion object {
        const val DATA_CHECKOUT = "data"
        const val NUMBER_USER = "number"

        fun startToCheckout(activity: Activity, data: DenomList, number: String) {
            val intent = Intent(activity, CheckoutActivity::class.java)
            intent.putExtra(DATA_CHECKOUT, data as Serializable)
            intent.putExtra(NUMBER_USER, number)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val data = intent.getSerializableExtra(DATA_CHECKOUT) as DenomList
        number = intent.getStringExtra(NUMBER_USER).toString()
        Log.d("numbernya", number)
        initView(data)

        initObserver(data.pulsa_code, number)
    }

    @SuppressLint("SetTextI18n")
    private fun initView(data: DenomList) {
        iv_operator_checkout.loadImage(data.icon_url)
        pulsa_nominal.text = data.pulsa_nominal
        jumlah_aktif.text = "${data.masaaktif} hari"
        pulsa_total.text = UtilsApp.formatPrice(data.pulsa_price)

        btn_lanjut_bayar.setOnClickListener {
            mViewModel?.triggerCheckout?.value = true
        }
    }

    private fun initObserver(pulsacode: String, phoneNumber: String) {
        mViewModel?.getCheckout(pulsacode, phoneNumber)?.observe(this, Observer { resource ->
            when (resource.status) {
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESS -> {
                    hideLoading()
                    resource.data?.let {
                        Log.d("logbre", it.data.message)
                        showDialogStatus(it.data.message)
                    }
                }

                Status.ERROR -> {
                    hideLoading()
                    resource?.data?.message?.let { showDialogStatus(it) }
                }
            }
        })
    }

    private fun showDialogStatus(status: String) {
        val mDialogView = LayoutInflater.from(this)
            .inflate(R.layout.dialog_status_transaction, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
        val  mAlertDialog = mBuilder.show()
        mDialogView.message_transaction.text = status
        mDialogView.btn_see_transaction.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
            MainActivity.startToCheckout(this)

        }
    }

    override fun initToolbar() {
    }
}