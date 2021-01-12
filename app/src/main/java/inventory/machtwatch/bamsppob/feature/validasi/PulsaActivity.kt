package inventory.machtwatch.bamsppob.feature.validasi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import inventory.machtwatch.bamsppob.R
import inventory.machtwatch.bamsppob.base.BaseActivity
import inventory.machtwatch.bamsppob.base.Status
import inventory.machtwatch.bamsppob.feature.listdenom.ListDenominationPulsa
import kotlinx.android.synthetic.main.activity_pulsa.*

class PulsaActivity : BaseActivity<PulsaValidasiViewModel>() {

    private var number = ""

    companion object {
        fun startToPulsaActivity(activity: Activity) {
            val intent = Intent(activity, PulsaActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulsa)
        initObserver()

        btn_lanjut.setOnClickListener {
             number = et_number.text.toString()
            if (number.isEmpty()) {
                Toast.makeText(this, "Please fill", Toast.LENGTH_SHORT).show()
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
                        Log.d("datantya", it.data.operator)
                        ListDenominationPulsa.startToListOperator(this, it.data.operator, number)
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

    }
}