package inventory.machtwatch.bamsppob.feature.listdenom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import inventory.machtwatch.bamsppob.R
import inventory.machtwatch.bamsppob.base.BaseActivity
import inventory.machtwatch.bamsppob.base.Status
import inventory.machtwatch.bamsppob.feature.checkout.CheckoutActivity
import inventory.machtwatch.bamsppob.feature.model.DataResponse
import inventory.machtwatch.bamsppob.feature.model.DenomList
import kotlinx.android.synthetic.main.activity_list_denom_pulsa.*
import kotlinx.android.synthetic.main.activity_list_paket_data.*

class ListDenomPaketData : BaseActivity<ListDenomPulsaViewModel>() {

    private var operatorData = ""
    private var number = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_paket_data)

        operatorData = intent.getStringExtra(OPERATOR_DATA).toString()
        number = intent.getStringExtra(NUMBER).toString()

        initObserver()
        if (operatorData.isNotEmpty()) {
            mViewModel?.triggerListPaketData?.value = operatorData
        } else {
            showMessage("Operator not define")
        }
    }

    private fun initObserver() {
        mViewModel?.getListPaketData?.observe(this, Observer { resources ->
            when (resources.status) {
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESS -> {
                    hideLoading()
                    resources?.data?.let {
                        Log.d("iniloh", it.data[0].pulsa_code)
                        initView(it.data)
                    }
                }
                Status.ERROR -> {
                    hideLoading()
                }
            }
        })
    }

    private fun initView(data: List<DataResponse>) {
        val adapter =
            ListDenomPaketDataAdapter(data, object : ListDenomPaketDataAdapter.OnPulsaClick {
                override fun onItemClick(data: DataResponse) {
                    val params = DenomList(
                        data.status,
                        data.icon_url,
                        data.pulsa_code,
                        data.pulsa_op,
                        data.pulsa_nominal,
                        data.pulsa_details,
                        data.pulsa_price,
                        data.pulsa_type,
                        data.masaaktif
                    )
                    CheckoutActivity.startToCheckout(this@ListDenomPaketData, params, number)
                }
            })
        rv_list_paket_data.adapter = adapter
    }

    override fun initToolbar() {

    }


    companion object {
        const val OPERATOR_DATA = "operator"
        const val NUMBER = "number"

        fun startToListPaketData(activity: Activity, operator: String, number: String) {
            val intent = Intent(activity, ListDenomPaketData::class.java)
            intent.putExtra(OPERATOR_DATA, operator)
            intent.putExtra(NUMBER, number)
            activity.startActivity(intent)
        }
    }

}