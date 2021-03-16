package inventory.machtwatch.bamsppob.feature.validasi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import inventory.machtwatch.bamsppob.R
import inventory.machtwatch.bamsppob.base.BaseActivity
import inventory.machtwatch.bamsppob.base.Status
import inventory.machtwatch.bamsppob.feature.checkout.CheckoutActivity
import inventory.machtwatch.bamsppob.feature.model.DenomList
import kotlinx.android.synthetic.main.activity_pln.*
import kotlinx.android.synthetic.main.activity_pln.btn_lanjut
import kotlinx.android.synthetic.main.activity_pln.et_number
import kotlinx.android.synthetic.main.activity_pulsa.*

class PlnPostPaidActivity : BaseActivity<PlnValidasiViewModel>() {

    companion object {
        fun startToPlnAbodemenActivity(activity: Activity) {
            val intent = Intent(activity, PlnPostPaidActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pln_abudemen)

        initObserver()
        btn_lanjut.setOnClickListener {
            if (et_number.text.toString().isEmpty()) {
                Toast.makeText(this, "Please fill", Toast.LENGTH_SHORT).show()
            } else {
                mViewModel?.triggerValidationPlnAbudemen?.value = et_number.text.toString()

            }
        }
    }

    private fun initObserver() {
        mViewModel?.getInquiryAbodemen?.observe(this, Observer { resource ->
            when (resource.status) {
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESS -> {
                    hideLoading()
                    resource.data?.let {
                        Log.d("iniloh", it.data.code)
                        PlnPostPaidInquiryActivity.startToPlnInquiryActivity(
                            this,
                            it.data.ref_id,
                            it.data.tr_id,
                            it.data.hp,
                            it.data.desc.tagihan.detail[0].nilai_tagihan,
                            it.data.desc.tagihan.detail[0].admin,
                            it.data.desc.tagihan.detail[0].denda,
                            it.data.desc.tagihan.detail[0].total
                        )
                    }
                }

                Status.ERROR -> {
                    hideLoading()
//                    Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun initToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "PLN Postpaid"
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