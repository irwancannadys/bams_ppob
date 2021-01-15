package inventory.machtwatch.bamsppob.feature.listdenom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import inventory.machtwatch.bamsppob.R
import inventory.machtwatch.bamsppob.base.BaseActivity
import inventory.machtwatch.bamsppob.base.Status
import inventory.machtwatch.bamsppob.feature.checkout.CheckoutActivity
import inventory.machtwatch.bamsppob.feature.model.DenomList
import kotlinx.android.synthetic.main.activity_list_denom_pulsa.*

class ListDenominationPulsa : BaseActivity<ListDenomPulsaViewModel>() {

    private var operator = ""
    private var number = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_denom_pulsa)

        operator = intent.getStringExtra(OPERATOR).toString()
        number = intent.getStringExtra(NUMBER).toString()

        initObserver()
        if (operator.isNotEmpty()) {
            mViewModel?.triggerListPulsa?.value = operator
        } else {
            showMessage("Operator not define")
        }
    }

    override fun initToolbar() {
    }

    private fun initObserver() {
        mViewModel?.getListPulsa?.observe(this, Observer { resources ->
            when(resources.status) {
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESS -> {
                    hideLoading()
                    resources?.data?.let {
                        initView(it.data)
                    }
                }
                Status.ERROR -> {
                    hideLoading()
                }
            }
        })
    }

    private fun initView(data: List<DenomList>) {
        rv_list_denom.setHasFixedSize(true)
        val adapter = ListDenomPulsaAdapter(data, object : ListDenomPulsaAdapter.OnPulsaClick{
            override fun onItemClick(data: DenomList) {
                CheckoutActivity.startToCheckout(this@ListDenominationPulsa, data, number)
            }
        })
        rv_list_denom.adapter = adapter
    }

    companion object {
        const val OPERATOR = "operator"
        const val NUMBER = "number"

        fun startToListOperator(activity: Activity, operator: String, number: String) {
            val intent = Intent(activity, ListDenominationPulsa::class.java)
            intent.putExtra(OPERATOR, operator)
            intent.putExtra(NUMBER, number)
            activity.startActivity(intent)
        }
    }
}