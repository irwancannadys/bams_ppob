package inventory.machtwatch.bamsppob.feature.validasi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import inventory.machtwatch.bamsppob.R
import inventory.machtwatch.bamsppob.base.BaseActivity
import inventory.machtwatch.bamsppob.base.Status
import inventory.machtwatch.bamsppob.feature.checkout.CheckoutActivity
import inventory.machtwatch.bamsppob.feature.listdenom.ListDenominationPulsa
import inventory.machtwatch.bamsppob.feature.model.DenomList
import kotlinx.android.synthetic.main.activity_pln.*
import kotlinx.android.synthetic.main.activity_pulsa.*
import kotlinx.android.synthetic.main.activity_pulsa.btn_lanjut
import kotlinx.android.synthetic.main.activity_pulsa.et_number
import kotlinx.android.synthetic.main.activity_pulsa.tv_clear

class PlnActivity : BaseActivity<PlnValidasiViewModel>() {

    private var number = ""


    companion object {
        fun startToPLNActivity(activity: Activity) {
            val intent = Intent(activity, PlnActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pln)

        rv_list_pln.layoutManager = LinearLayoutManager(this)

        initObserver()
        btn_lanjut.setOnClickListener {
            number = et_number.text.toString()
            if (number.isEmpty()) {
                Toast.makeText(this, "Please fill", Toast.LENGTH_SHORT).show()
            } else {
                mViewModel?.triggerPLnList?.value = true
            }
        }

        tv_clear.setOnClickListener {
            et_number.setText("")
        }
    }

    override fun initToolbar() {
    }

    private fun initObserver() {
        mViewModel?.getListPln?.observe(this, Observer { resource ->
            when (resource.status) {
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESS -> {
                    hideLoading()
                    resource.data?.let {
                        val adapter = PlnListAdapter(it.data, object : PlnListAdapter.PlnItemClickListener {
                            override fun onItemclick(data: DenomList) {
                                CheckoutActivity.startToCheckout(this@PlnActivity, data, number)
                            }

                        })
                        rv_list_pln.adapter = adapter
                    }
                }

                Status.ERROR -> {
                    hideLoading()
//                    Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}