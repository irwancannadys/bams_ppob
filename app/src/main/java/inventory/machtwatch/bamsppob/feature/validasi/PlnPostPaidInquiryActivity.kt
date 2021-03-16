package inventory.machtwatch.bamsppob.feature.validasi

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.lifecycle.Observer
import inventory.machtwatch.bamsppob.MainActivity
import inventory.machtwatch.bamsppob.R
import inventory.machtwatch.bamsppob.base.BaseActivity
import inventory.machtwatch.bamsppob.base.Status
import inventory.machtwatch.bamsppob.feature.checkout.CheckoutViewModel
import inventory.machtwatch.bamsppob.utils.UtilsApp
import kotlinx.android.synthetic.main.activity_inquiry_pln.*
import kotlinx.android.synthetic.main.dialog_status_transaction.view.*

class PlnPostPaidInquiryActivity : BaseActivity<CheckoutViewModel>() {

    var ref_id = ""
    var trId = 0
    var noAbudemen = ""
    var nilaiTagihan = ""
    var admin = ""
    var denda = ""
    var total = ""

    companion object {
        const val REF_ID = "ref_id"
        const val TR_ID = "tr_id"
        const val NO_ABD = "no_abd"
        const val NILAI_TGHN = "nl_tghn"
        const val ADMIN = "adm"
        const val DENDA = "denda"
        const val TOTAL = "total"

        fun startToPlnInquiryActivity(
            activity: Activity,
            refId: String,
            trId: Int,
            noAbudemen: String,
            nilaiTagihan: String,
            biayaAdmin: String,
            biayaDenda: String,
            total: Double
        ) {
            val intent = Intent(activity, PlnPostPaidInquiryActivity::class.java)
            intent.putExtra(REF_ID, refId)
            intent.putExtra(TR_ID, trId)
            intent.putExtra(NO_ABD, noAbudemen)
            intent.putExtra(NILAI_TGHN, nilaiTagihan)
            intent.putExtra(ADMIN, biayaAdmin)
            intent.putExtra(DENDA, biayaDenda)
            intent.putExtra(TOTAL, total)

            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inquiry_pln)

        ref_id = intent.getStringExtra(REF_ID).toString()
        trId = intent.getIntExtra(TR_ID, 0)
        noAbudemen = intent.getStringExtra(NO_ABD).toString()
        nilaiTagihan = intent.getStringExtra(NILAI_TGHN).toString()
        admin = intent.getStringExtra(ADMIN).toString()
        denda = intent.getStringExtra(DENDA).toString()
        total = intent.getDoubleExtra(TOTAL, 0.0).toString()

        initView()

        initObserver()

        btn_bayar.setOnClickListener {
            mViewModel?.triggerCheckoutInquiry?.value = true
        }

    }

    private fun initView(){
        trasaksi_id.text = trId.toString()
        no_abodemen.text = noAbudemen
        nominal_price.text = nilaiTagihan
        tv_admin.text = admin
        tv_denda.text = denda
        tv_total.text = UtilsApp.formatPrice(total.toDouble())

    }

    private fun initObserver() {
        mViewModel?.checkoutPostPaid(ref_id, trId.toString())?.observe(this, Observer { resource ->
            when (resource.status) {
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESS -> {
                    hideLoading()
                    resource.data?.let {
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "PLN Inquiry"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}