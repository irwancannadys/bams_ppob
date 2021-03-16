package inventory.machtwatch.bamsppob.feature.history

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import inventory.machtwatch.bamsppob.R
import inventory.machtwatch.bamsppob.base.BaseActivity
import inventory.machtwatch.bamsppob.base.Status
import inventory.machtwatch.bamsppob.feature.model.DataTransaction
import inventory.machtwatch.bamsppob.utils.UtilsApp
import kotlinx.android.synthetic.main.activity_history_transaction.*
import kotlinx.android.synthetic.main.dialog_details_transaction_history.view.*
import kotlinx.android.synthetic.main.dialog_status_transaction.view.btn_see_transaction

class HistoryActivity : BaseActivity<HistoryViewModel>() {

    companion object {
        fun startToHistory(activity: Activity) {
            val intent = Intent(activity, HistoryActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_transaction)

        initObserver()
        mViewModel?.triggerHistoryList?.value = true

        rv_list_transaction.layoutManager = LinearLayoutManager(this)
    }

    private fun initObserver() {
        mViewModel?.getListHistory?.observe(this, Observer { resource ->
            when (resource.status) {
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESS -> {
                    hideLoading()
                    resource.data?.let {
                        val adapter = HistoryAdapter(
                            applicationContext,
                            it.data,
                            object : HistoryAdapter.OnDetailsClick {
                                override fun onItemClick(historyTransaction: DataTransaction) {
                                    showDialogDetails(historyTransaction)
                                }
                            })
                        rv_list_transaction.adapter = adapter
                    }
                }

                Status.ERROR -> {
                    hideLoading()
                }
            }
        })
    }

    private fun showDialogDetails(historyTransaction: DataTransaction) {
        val mDialogView = LayoutInflater.from(this)
            .inflate(R.layout.dialog_details_transaction_history, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
        val mAlertDialog = mBuilder.show()
        if (historyTransaction.status == "failed"){
            mDialogView.tv_status_detail.background = ContextCompat.getDrawable(this, R.drawable.background_rounded_status_failed)
            mDialogView.tv_status_detail.setTextColor(getColor(R.color.black))
        }
        mDialogView.tv_status_detail.text = historyTransaction.status
        mDialogView.tv_ref_id.text = historyTransaction.body.ref_id
        mDialogView.tv_operator.text = historyTransaction.body.code
        mDialogView.tv_date_detail_transaction.text =
            UtilsApp.getFormattedDateSimple(historyTransaction.created_at)
        mDialogView.tv_hp_transaction.text = historyTransaction.body.hp
        mDialogView.tv_price.text = historyTransaction.body.price
        mDialogView.btn_see_transaction.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }
    }

    override fun initToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "History"
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