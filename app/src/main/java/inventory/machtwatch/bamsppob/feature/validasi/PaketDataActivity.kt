package inventory.machtwatch.bamsppob.feature.validasi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import inventory.machtwatch.bamsppob.R
import inventory.machtwatch.bamsppob.base.BaseActivity
import inventory.machtwatch.bamsppob.base.Status
import inventory.machtwatch.bamsppob.feature.listdenom.ListDenomPaketData
import inventory.machtwatch.bamsppob.feature.listdenom.ListDenominationPulsa
import kotlinx.android.synthetic.main.activity_pulsa.*

class PaketDataActivity : BaseActivity<PulsaValidasiViewModel>() {

    private var number = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulsa)

        initObserver()

        btn_lanjut.setOnClickListener {
            number = et_number.text.toString()
            if (number.isEmpty()) {
                Toast.makeText(this, "Nomor Telepon Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            } else {
                mViewModel?.triggerValidation?.value = number
            }
        }

        tv_clear.setOnClickListener {
            et_number.setText("")
        }

    }

    private fun initObserver() {
        mViewModel?.getValidation?.observe(this, Observer { resources ->
            when (resources.status) {
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESS -> {
                    hideLoading()
                    resources.data?.let {
                        ListDenomPaketData.startToListPaketData(this, it.data.operator_data, number)
                    }
                }

                Status.ERROR -> {
                    hideLoading()
                    Toast.makeText(this, "Nomor yang anda masukan salah", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun initToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Paket Data"
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

    companion object {
        fun startToPaketDataActivity(activity: Activity) {
            val intent = Intent(activity, PaketDataActivity::class.java)
            activity.startActivity(intent)
        }
    }
}